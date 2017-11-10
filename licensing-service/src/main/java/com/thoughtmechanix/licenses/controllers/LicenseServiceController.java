package com.thoughtmechanix.licenses.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.thoughtmechanix.licenses.config.ServiceConfig;
import com.thoughtmechanix.licenses.model.License;
import com.thoughtmechanix.licenses.services.LicenseService;
import com.thoughtmechanix.licenses.utils.UserContextHolder;

@RestController
@RequestMapping(value = "/v1/organizations/{organizationId}/licenses")
public class LicenseServiceController {
	
	private static final Logger logger = LoggerFactory.getLogger(LicenseServiceController.class);
	
	@Autowired
	private LicenseService licenseService;

	@Autowired
	private ServiceConfig serviceConfig;
	
	@Autowired
    private HttpServletRequest request;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<License> getLicenses(
			@PathVariable("organizationId") String organizationId) {
		logger.debug("LicenseServiceController Correlation id: {}", UserContextHolder.getContext().getCorrelationId());
		return licenseService.getLicensesByOrg(organizationId);
	}

	@RequestMapping(value = "/{licenseId}", method = RequestMethod.GET)
	public License getLicenses(
			@PathVariable("organizationId") String organizationId,
			@PathVariable("licenseId") String licenseId) {
		logger.debug("Found tmx-correlation-id in license-service-controller: {} ", request.getHeader("tmx-correlation-id"));
		return licenseService.getLicense(organizationId, licenseId);
	}
	
	@RequestMapping(value="/{licenseId}/{clientType}",method = RequestMethod.GET)
    public License getLicensesWithClient( @PathVariable("organizationId") String organizationId,
                                          @PathVariable("licenseId") String licenseId,
                                          @PathVariable("clientType") String clientType) {

        return licenseService.getLicense(organizationId,licenseId, clientType);
    }

	@RequestMapping(value = "{licenseId}", method = RequestMethod.PUT)
	public String updateLicenses(@PathVariable("licenseId") String licenseId) {
		return String.format("This is the put");
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public void saveLicenses(@RequestBody License license) {
		licenseService.saveLicense(license);
	}

	@RequestMapping(value = "{licenseId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public String deleteLicenses(@PathVariable("licenseId") String licenseId) {
		return String.format("This is the Delete");
	}
}
