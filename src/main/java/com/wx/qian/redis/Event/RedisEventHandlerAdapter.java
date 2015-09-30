package com.wx.qian.redis.Event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 *
 * @author <a href="mailto:qwx890206@gmail.com">wxqian</a>
 * @version v1.0, 2015/9/30
 */
public abstract class RedisEventHandlerAdapter implements RedisEventHandler {

    @Autowired
    public RedisTemplate<String, Object> redisTemplate;

    @Override
    public String resolveKey(String key) {
        return key;
    }

    @Override
    public void handler(String key) {

    }

    protected void store(String key, Class<?> clazz, long timeToLive) {
        BoundValueOperations<String, Object> operations = redisTemplate.boundValueOps("redisEvent:" + key + ":" + clazz);
        operations.set(key, timeToLive, TimeUnit.MILLISECONDS);
    }
}
