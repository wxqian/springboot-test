package com.wx.qian.redis.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

/**
 * <p>
 *
 * @author <a href="mailto:qwx890206@gmail.com">wxqian</a>
 * @version v1.0, 2015/9/30
 */
public class RedisReceiver {

    private static final Logger logger = LoggerFactory.getLogger(RedisReceiver.class);

    private CountDownLatch countDownLatch;

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }

    public void receiveMessage(String message) {
        logger.info("message:" + message);
        countDownLatch.countDown();
    }
}
