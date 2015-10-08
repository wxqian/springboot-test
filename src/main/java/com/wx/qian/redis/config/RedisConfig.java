package com.wx.qian.redis.config;

import com.wx.qian.redis.Event.RedisEventMessageListener;
import com.wx.qian.redis.domain.RedisReceiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import java.util.Arrays;

/**
 * <p>
 *
 * @author <a href="mailto:qwx890206@gmail.com">wxqian</a>
 * @version v1.0, 2015/9/30
 */
@Configuration
public class RedisConfig {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, MessageListenerAdapter redisReceiveListenerAdapter,
                                                   MessageListener redisEventMessageListener) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(redisReceiveListenerAdapter, new PatternTopic("spring-boot-test"));
        container.addMessageListener(redisEventMessageListener, Arrays.asList(new PatternTopic("__keyevent@*:del"),
                new PatternTopic("__keyevent@*:expired*")));
        return container;
    }

    @Bean
    public MessageListenerAdapter redisReceiveListenerAdapter(RedisReceiver redisReceiver) {
        return new MessageListenerAdapter(redisReceiver, "receiveMessage");
    }

    @Bean
    public RedisReceiver redisReceiver() {
        return new RedisReceiver();
    }

    @Bean
    public StringRedisTemplate template(RedisConnectionFactory redisConnectionFactory) {
        return new StringRedisTemplate(redisConnectionFactory);
    }

    @Bean
    public RedisEventMessageListener redisEventMessageListener() {
        return new RedisEventMessageListener(eventPublisher);
    }
}
