package com.thoughtmechanix.rabbit.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DirectReceiver {
	
	@RabbitListener(queues="queue")    //监听器监听指定的Queue
    public void processC(String str) {
        System.out.println("Receive:"+str);
    }
	
}
