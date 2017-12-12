package com.thoughtmechanix.licenses.clients;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.thoughtmechanix.licenses.model.Organization;
import com.thoughtmechanix.licenses.repository.OrganizationRedisRepository;
import com.thoughtmechanix.licenses.utils.UserContextHolder;

@Component
public class OrganizationRestTemplateClient {

	@Autowired
    RestTemplate restTemplate;
	
	@Autowired
	OrganizationRedisRepository orgRedisRepo;
	
	private static final Logger logger = LoggerFactory.getLogger(OrganizationRestTemplateClient.class);
	
	public Organization getOrganization(String organizationId){
		logger.debug("In Licensing Service.getOrganization: {}", 
				UserContextHolder.getContext().getCorrelationId());
		
		Organization org = checkRedisCache(organizationId);
		
		if (org!=null){
			logger.debug("I have successfully retrieved an organization {} from the redis cache: {}", 
					organizationId, 
					org);
			return org;
		}
		
		logger.debug("Unable to locate organization from the redis cache: {}.", organizationId);
		
        ResponseEntity<Organization> restExchange =
                restTemplate.exchange(
                		"http://zuulservice/api/organization/v1/organizations/{organizationId}",
                        HttpMethod.GET,
                        null, 
                        Organization.class, 
                        organizationId);
        
        org = restExchange.getBody();
        
        if(org != null){
        	cacheOrganizationObject(org);
        }

        return org;
    }
	
	private Organization checkRedisCache(String organizationId){
		try {
			return orgRedisRepo.findOrganization(organizationId);
		} catch (Exception e) {
			logger.error("Error encountered while trying to retrieve organization {} check Redis Cache. "
					+ "Exception {}", 
					organizationId, 
					e);
			return null;
		}		
	}
	
	private void cacheOrganizationObject(Organization org) {
		try {
			orgRedisRepo.saveOrganization(org);
		}catch (Exception e){
			logger.error("Unable to cache organization {} in Redis. Exception {}", org.getId(), e);
		}
	}
	
	
	
}
