package com.thoughtmechanix.organization.events.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SimpleSourceRabbitConfig {
	@Bean
    public Queue queue() {
         return new Queue("org-queue");
    }
}
