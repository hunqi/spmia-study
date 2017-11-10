package com.thoughtmechanix.licenses.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.thoughtmechanix.licenses.model.License;

@Repository
public interface LicenseRepository extends CrudRepository<License,String>  {
    public List<License> findByOrganizationId(String organizationId);
    public License findByOrganizationIdAndLicenseId(String organizationId,String licenseId);
}
