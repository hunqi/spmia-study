package com.thoughtmechanix.organization.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thoughtmechanix.organization.events.source.OrganizationSource;
import com.thoughtmechanix.organization.model.Organization;
import com.thoughtmechanix.organization.repository.OrganizationRepository;

@Service
public class OrganizationService {
	
	@Autowired
    private OrganizationRepository orgRepository;
	
//	@Autowired
//	private SimpleSource simpleSource;
	
	@Autowired
	private OrganizationSource organizationSource;

    public Organization getOrg(String organizationId) {
        return orgRepository.findById(organizationId);
    }

    public void saveOrg(Organization org){
        org.setId( UUID.randomUUID().toString());

        orgRepository.save(org);
        organizationSource.publish("SAVE", org.getId());
    }

    public void updateOrg(Organization org){
        orgRepository.save(org);
        organizationSource.publish("UPDATE", org.getId());
    }

    public void deleteOrg(Organization org){
        orgRepository.delete( org.getId());
    }
	
}
