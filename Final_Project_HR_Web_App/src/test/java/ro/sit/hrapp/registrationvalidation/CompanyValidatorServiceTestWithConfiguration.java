/*
 * Final_Project_HR_Web_App
 * ro.sit.hrapp  
 * CompanyValidatorServiceTestWithConfiguration.java
 * 
 *
 * MADE FOR TRAINING PURPOSES.
 *
 */
package ro.sit.hrapp.registrationvalidation;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ro.sit.hrapp.TestApplicationConfiguration;
import ro.sit.hrapp.service.CompanyService;
import ro.sit.hrapp.service.validator.CompanyRegistrationValidator;

/**
 * @author Sorin_Dragan
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestApplicationConfiguration.class})
public class CompanyValidatorServiceTestWithConfiguration extends CompanyValidatorServiceTest {

	@Autowired
	private CompanyService service;
	
	@Autowired
	protected CompanyRegistrationValidator companyValidator;
	
	@Override
	protected CompanyService getCompanyService() {
		return service;
	}

	@Override
	protected CompanyRegistrationValidator getValidator() {
		return companyValidator;
	}
	
}
