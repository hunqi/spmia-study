package com.thoughtmechanix.organization.events.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.thoughtmechanix.organization.events.source.OrganizationSource;

@Configuration
public class OrganizationRabbitConfig {
	
	@Value("${rabbitmq.org.directexchange.name}")
	private String orgExhange;
	
	@Bean
    public DirectExchange direct() {
        return new DirectExchange(orgExhange);
    }
	
    @Bean
    public OrganizationSource organizationSource() {
        return new OrganizationSource();
    }
	
}
