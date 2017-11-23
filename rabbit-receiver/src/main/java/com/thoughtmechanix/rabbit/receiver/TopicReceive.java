package com.thoughtmechanix.rabbit.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicReceive {
	
	@RabbitListener(queues="topic.message")    //监听器监听指定的Queue
	public void process1(String str) {    
        System.out.println("Receive:message:"+str);
    }
	
	@RabbitListener(queues="topic.messages")    //监听器监听指定的Queue
    public void process2(String str) {
        System.out.println("Receive:messages:"+str);
    }
	
}
