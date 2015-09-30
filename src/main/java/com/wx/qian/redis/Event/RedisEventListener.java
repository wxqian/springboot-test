package com.wx.qian.redis.Event;

import org.springframework.context.ApplicationListener;

/**
 * <p>
 *
 * @author <a href="mailto:qwx890206@gmail.com">wxqian</a>
 * @version v1.0, 2015/9/30
 */
public class RedisEventListener implements ApplicationListener<RedisEvent> {


    @Override
    public void onApplicationEvent(RedisEvent redisEvent) {
        Class<?> clazz = redisEvent.getClazz();
        RedisEventHandler handler = RedisEventHandlerFactory.getRedisEventHandler(clazz);
        if(handler == null){
            return;
        }
        handler.handler(redisEvent.getKey());
    }
}
