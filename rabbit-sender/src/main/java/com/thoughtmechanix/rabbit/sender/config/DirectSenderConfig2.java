package com.thoughtmechanix.rabbit.sender.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.thoughtmechanix.rabbit.sender.impl.DirectSender2;

@Configuration
public class DirectSenderConfig2 {
	@Bean
    public DirectExchange direct() {
        return new DirectExchange("orgChangeEx");
    }
	
//	@Profile("sender")
    @Bean
    public DirectSender2 sender() {
        return new DirectSender2();
    }
}
