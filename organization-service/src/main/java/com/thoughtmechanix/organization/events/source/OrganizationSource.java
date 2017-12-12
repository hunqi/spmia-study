package com.thoughtmechanix.organization.events.source;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.thoughtmechanix.organization.events.models.OrganizationChangeModel;
import com.thoughtmechanix.organization.utils.JsonUtils;
import com.thoughtmechanix.organization.utils.UserContext;

@Component
public class OrganizationSource {
	private static final Logger logger = LoggerFactory.getLogger(OrganizationSource.class);
	
	@Autowired
    private RabbitTemplate template;

    @Autowired
    private DirectExchange direct;
	
    @Value("${rabbitmq.org.directexchange.routing}")
    private String orgExchangeRouting;
    
	public void publish(String action,String orgId){
		logger.debug("Sending rabbit message {} for Organization Id: {}", action, orgId);
		
        OrganizationChangeModel change =  new OrganizationChangeModel(
                OrganizationChangeModel.class.getTypeName(),
                action,
                orgId,
                UserContext.getCorrelationId());
        
        template.convertAndSend(direct.getName(), 
        		orgExchangeRouting, 
        		JsonUtils.format(change));
	}
}
