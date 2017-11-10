package com.thoughtmechanix.organization.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.thoughtmechanix.organization.model.Organization;

@Repository
public interface OrganizationRepository extends CrudRepository<Organization, String>{
	Organization findById(String organizationId);
}
