package com.wx.qian.redis.Event;

/**
 * <p>
 *
 * @author <a href="mailto:qwx890206@gmail.com">wxqian</a>
 * @version v1.0, 2015/9/30
 */
public interface RedisEventHandler {
    String resolveKey(String key);

    void handler(String key);
}
