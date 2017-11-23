package com.thoughtmechanix.rabbit.sender.impl;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicSender {
    @Autowired
    private AmqpTemplate template;
    
    public void send() {
    	template.convertAndSend("exchange", "topic.message", "hello,rabbit~topic");
    }
}
