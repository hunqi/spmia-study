package com.thoughtmechanix.organization;

import javax.servlet.Filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.sleuth.Sampler;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import com.thoughtmechanix.organization.utils.UserContextFilter;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
//@EnableResourceServer
public class Application {
	
//	@Bean
//	public Sampler defaultSampler() { return new AlwaysSampler();}
	
	@Bean
    public Filter userContextFilter() {
        UserContextFilter userContextFilter = new UserContextFilter();
        return userContextFilter;
    }
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
