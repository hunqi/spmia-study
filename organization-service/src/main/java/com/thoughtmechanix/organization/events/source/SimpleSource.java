package com.thoughtmechanix.organization.events.source;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thoughtmechanix.organization.events.models.OrganizationChangeModel;
import com.thoughtmechanix.organization.utils.UserContext;

@Component
public class SimpleSource {
	private static final Logger logger = LoggerFactory.getLogger(SimpleSource.class);
	
	@Autowired
	private AmqpTemplate template;
	
	public void publish(String action,String orgId){
		logger.debug("Sending rabbit message {} for Organization Id: {}", action, orgId);
		
        OrganizationChangeModel change =  new OrganizationChangeModel(
                OrganizationChangeModel.class.getTypeName(),
                action,
                orgId,
                UserContext.getCorrelationId());
        
        template.convertAndSend("org-queue", change.toString());
	}
	
}
