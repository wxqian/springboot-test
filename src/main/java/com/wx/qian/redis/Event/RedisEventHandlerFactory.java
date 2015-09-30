package com.wx.qian.redis.Event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

/**
 * <p>
 *
 * @author <a href="mailto:qwx890206@gmail.com">wxqian</a>
 * @version v1.0, 2015/9/30
 */
public class RedisEventHandlerFactory implements ApplicationContextAware {

    private Logger logger = LoggerFactory.getLogger(RedisEventHandlerFactory.class);

    private static ApplicationContext context;

    public static RedisEventHandler getRedisEventHandler(Class clazz) {
        Map<String, RedisEventHandler> beans = context.getBeansOfType(clazz);
        if (beans.size() > 0) {
            return (RedisEventHandler) beans.values().toArray()[0];
        }
        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
