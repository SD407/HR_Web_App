///*
// * Final_Project_HR_Web_App
// * ro.sit.hrapp.services  
// * CompanyServiceTestWithConfiguration.java
// * 
// *
// * MADE FOR TRAINING PURPOSES.
// *
// */
//package ro.sit.hrapp.services;
//
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import ro.sit.hrapp.TestApplicationConfiguration;
//import ro.sit.hrapp.service.CompanyService;
//import ro.sit.hrapp.service.validator.CompanyRegistrationValidator;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = { TestApplicationConfiguration.class })
//public class CompanyServiceTestWithConfiguration extends BaseCompanyServiceTest {
//
//	@Autowired
//	private CompanyService service;
//	
//	@Autowired
//	protected CompanyRegistrationValidator companyValidator;
//
//	@Override
//	protected CompanyService getCompanyService() {
//		return service;
//	}
//
//	/* (non-Javadoc)
//	 * @see ro.sit.hrapp.services.BaseCompanyServiceTest#getValidator()
//	 */
//	@Override
//	protected CompanyRegistrationValidator getValidator() {
//		return companyValidator;
//	}
//
//}
//
