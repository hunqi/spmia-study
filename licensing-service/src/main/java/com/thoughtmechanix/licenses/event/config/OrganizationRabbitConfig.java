package com.thoughtmechanix.licenses.event.config;

import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.thoughtmechanix.licenses.event.handlers.OrganizationRabbitReceiver;

@Configuration
public class OrganizationRabbitConfig {
	
	@Bean
    public DirectExchange direct() {
        return new DirectExchange("${rabbitmq.org.directexchange.name}");
    }
	
	@Bean
    public Queue queue() {
        return new AnonymousQueue();
    }
	
	@Bean
    public Binding binding(DirectExchange direct, 
        Queue queue) {
        return BindingBuilder.bind(queue)
            .to(direct)
            .with("${rabbitmq.org.directexchange.routing}");
    }
	
	@Bean
    public OrganizationRabbitReceiver receiver() {
        return new OrganizationRabbitReceiver();
    }
	
}
