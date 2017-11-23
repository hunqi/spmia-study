package com.thoughtmechanix.rabbit.sender.impl;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FanoutSender {
    @Autowired
    private AmqpTemplate template;
    
    public void send() {
    	template.convertAndSend("fanoutExchange", "", "hello,rabbit~fanout");
    }
}
