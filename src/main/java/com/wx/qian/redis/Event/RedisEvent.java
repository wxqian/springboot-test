package com.wx.qian.redis.Event;

import org.springframework.context.ApplicationEvent;

/**
 * <p>
 *
 * @author <a href="mailto:qwx890206@gmail.com">wxqian</a>
 * @version v1.0, 2015/9/30
 */
public class RedisEvent extends ApplicationEvent {

    private final String key;

    private Class<?> clazz;

    public RedisEvent(Object source, String key, Class<?> clazz) {
        super(source);
        this.key = key;
        this.clazz = clazz;
    }

    public String getKey() {
        return key;
    }

    public Class<?> getClazz() {
        return clazz;
    }
}
