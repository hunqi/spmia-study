package com.thoughtmechanix.rabbit.sender.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.thoughtmechanix.rabbit.sender.App;
import com.thoughtmechanix.rabbit.sender.impl.DirectSender;
import com.thoughtmechanix.rabbit.sender.impl.FanoutSender;
import com.thoughtmechanix.rabbit.sender.impl.TopicSender;

@SpringBootTest(classes=App.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TestRabbitMQ {
	
	@Autowired
	private DirectSender directSender;
    
    @Autowired
    private TopicSender topicSender;
    
    @Autowired
    private FanoutSender fanoutSender;

    @Test
    public void testDirect(){
    	directSender.send();
    }
    
    @Test
    public void testTopic() {
        topicSender.send();
    }
    
    @Test
    public void testFanout(){
    	fanoutSender.send();
    }
    
}
