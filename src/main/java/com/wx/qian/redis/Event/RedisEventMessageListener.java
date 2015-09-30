package com.wx.qian.redis.Event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

/**
 * <p>
 *
 * @author <a href="mailto:qwx890206@gmail.com">wxqian</a>
 * @version v1.0, 2015/9/30
 */
public class RedisEventMessageListener implements MessageListener {

    private Logger logger = LoggerFactory.getLogger(RedisEventMessageListener.class);

    private final ApplicationEventPublisher eventPublisher;

    public RedisEventMessageListener(ApplicationEventPublisher eventPublisher) {
        Assert.notNull(eventPublisher, "EventPublisher cannot be null");
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void onMessage(Message message, byte[] bytes) {
        byte[] messageChannel = message.getChannel();
        byte[] messageBody = message.getBody();
        if(messageChannel == null || messageBody == null) {
            return;
        }
        String channel = new String(messageChannel);
        if(!(channel.endsWith(":del") || channel.endsWith(":expired"))) {
            return;
        }
        String body = new String(messageBody);
        if(!body.startsWith("redisEvent:")) {
            return;
        }

        int beginIndex = body.lastIndexOf(":") + 1;
        int endIndex = body.length();
        String temp = body.substring(beginIndex, endIndex);
        String[] temps = temp.split("&");
        if(logger.isDebugEnabled()) {
            logger.debug("" + temp);
        }

        try {
            publishEvent(new RedisEvent(this, temps[0], ClassUtils.forName(temps[1],this.getClass().getClassLoader())));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void publishEvent(ApplicationEvent event) {
        try {
            this.eventPublisher.publishEvent(event);
        }
        catch (Throwable ex) {
            logger.error("Error publishing " + event + ".", ex);
        }
    }

}
