package com.wx.qian.rabbitmq.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * @author <a href="mailto:qwx890206@gmail.com">wxqian</a>
 * @version v1.0, 2015/9/30
 */
@Service
public class MqService {

    private static final String queueName = "spring-boot-test";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend(queueName, message);
    }
}
