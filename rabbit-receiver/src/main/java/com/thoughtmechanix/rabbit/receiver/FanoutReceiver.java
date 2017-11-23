package com.thoughtmechanix.rabbit.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FanoutReceiver {
	
	@RabbitListener(queues="fanout.A")
    public void processA(String str1) {
        System.out.println("ReceiveA:"+str1);
    }
    @RabbitListener(queues="fanout.B")
    public void processB(String str) {
        System.out.println("ReceiveB:"+str);
    }
    @RabbitListener(queues="fanout.C")
    public void processC(String str) {
        System.out.println("ReceiveC:"+str);
    }
	
}
