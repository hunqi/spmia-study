package com.thoughtmechanix.organization.events;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.thoughtmechanix.organization.Application;
import com.thoughtmechanix.organization.events.source.SimpleSource;

@SpringBootTest(classes=Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TestSimpleSource {
	
	@Autowired
	SimpleSource simpleSource;
	
	@Test
	public void publish(){
		simpleSource.publish("TEST", UUID.randomUUID().toString());
	}
	
}
