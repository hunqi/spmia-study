package com.thoughtmechanix.licenses.event.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OrganizationRabbitReceiver {
	
	private static final Logger logger = LoggerFactory.getLogger(OrganizationRabbitReceiver.class);
	
	@RabbitListener(queues={"${rabbitmq.org.queue}"})
	public void listenOrgQueue(String orgChange) {
		logger.debug("Received an event of organization[{}]", orgChange);
	}
	
}
