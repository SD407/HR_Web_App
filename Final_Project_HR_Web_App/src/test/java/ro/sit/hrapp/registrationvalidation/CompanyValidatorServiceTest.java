/*
 * Final_Project_HR_Web_App
 * ro.sit.hrapp  
 * CompanyValidatorServiceTest.java
 * 
 *
 * MADE FOR TRAINING PURPOSES.
 *
 */
package ro.sit.hrapp.registrationvalidation;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.LinkedList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import ro.sit.hrapp.domain.Company;
import ro.sit.hrapp.service.CompanyService;
import ro.sit.hrapp.service.validator.CompanyRegistrationValidator;

/**
 * @author Sorin_Dragan
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class CompanyValidatorServiceTest {
	
	protected abstract CompanyService getCompanyService();
	
	protected abstract CompanyRegistrationValidator getValidator();

	@After
	public void tearDown() {
		Collection<Company> companies = new LinkedList<Company>(getCompanyService().listAll());

		for (Company company : companies) {
			getCompanyService().deleteCompany(company.getId());
		}
	}

	@Test
	public void testEmptyGetAll() {
		Collection<Company> companies = getCompanyService().listAll();
		Assert.assertTrue(companies.isEmpty());
	}
	
	@Test
	public void test_Company_Entered_Correct_Details() {
		//given
		Company company = new Company();
		company.setCompanyName("TestCompany");
		company.setUserName("tc");
		company.setEmail("tc@tc.com");
		company.setPhoneNumber("1234567890");
		company.setPassword("pass123");
		company.setPasswordConfirmed("pass123");
		Errors errors = new BeanPropertyBindingResult(company, "company");
		//when
		getValidator().validate(company, errors);
		//then
		System.out.println(errors.toString());
		assertFalse(errors.hasErrors());
	}
	
	@Test
	public void test_Company_Entered_No_Company_Name() {
		//given
		Company company = new Company();
		company.setCompanyName("");
		company.setUserName("tc");
		company.setEmail("tc@tc.com");
		company.setPhoneNumber("1234567890");
		company.setPassword("pass123");
		company.setPasswordConfirmed("pass123");
		Errors errors = new BeanPropertyBindingResult(company, "company");
		//when
		getValidator().validate(company, errors);
		//then
		System.out.println(errors.toString());
		assertTrue(errors.hasErrors());
		assertTrue(company.getCompanyName().isEmpty());
	}
	
	@Test
	public void test_Company_Entered_No_User_Name() {
		//given
		Company company = new Company();
		company.setCompanyName("TestCompany");
		company.setUserName("");
		company.setEmail("tc@tc.com");
		company.setPhoneNumber("1234567890");
		company.setPassword("pass123");
		company.setPasswordConfirmed("pass123");
		Errors errors = new BeanPropertyBindingResult(company, "company");
		//when
		getValidator().validate(company, errors);
		//then
		System.out.println(errors.toString());
		assertTrue(errors.hasErrors());
		assertTrue(company.getUserName().isEmpty());
	}
	
	@Test
	public void test_Company_Entered_No_Email() {
		//given
		Company company = new Company();
		company.setCompanyName("TestCompany");
		company.setUserName("tc");
		company.setEmail("");
		company.setPhoneNumber("1234567890");
		company.setPassword("pass123");
		company.setPasswordConfirmed("pass123");
		Errors errors = new BeanPropertyBindingResult(company, "company");
		//when
		getValidator().validate(company, errors);
		//then
		System.out.println(errors.toString());
		assertTrue(errors.hasErrors());
		assertTrue(company.getEmail().isEmpty());
	}
	
	@Test
	public void test_Company_Entered_Invalid_Email() {
		//given
		Company company = new Company();
		company.setCompanyName("TestCompany");
		company.setUserName("tc");
		company.setEmail("tc@tc");
		company.setPhoneNumber("1234567890");
		company.setPassword("pass123");
		company.setPasswordConfirmed("pass123");
		Errors errors = new BeanPropertyBindingResult(company, "company");
		//when
		getValidator().validate(company, errors);
		//then
		System.out.println(errors.toString());
		assertTrue(errors.hasErrors());
	}
	
	@Test
	public void test_Company_Entered_No_Phone_Number() {
		//given
		Company company = new Company();
		company.setCompanyName("TestCompany");
		company.setUserName("tc");
		company.setEmail("tc@tc.com");
		company.setPhoneNumber("");
		company.setPassword("pass123");
		company.setPasswordConfirmed("pass123");
		Errors errors = new BeanPropertyBindingResult(company, "company");
		//when
		getValidator().validate(company, errors);
		//then
		System.out.println(errors.toString());
		assertTrue(errors.hasErrors());
		assertTrue(company.getPhoneNumber().isEmpty());
	}
	
	@Test
	public void test_Company_Entered_Incorrect_Phone_Number() {
		//given
		Company company = new Company();
		company.setCompanyName("TestCompany");
		company.setUserName("tc");
		company.setEmail("tc@tc.com");
		company.setPhoneNumber("123456789"); // should be 10 digits long
		company.setPassword("pass123");
		company.setPasswordConfirmed("pass123");
		Errors errors = new BeanPropertyBindingResult(company, "company");
		//when
		getValidator().validate(company, errors);
		//then
		System.out.println(errors.toString());
		assertTrue(errors.hasErrors());
	}
	
	@Test
	public void test_Company_Entered_No_Password() {
		//given
		Company company = new Company();
		company.setCompanyName("TestCompany");
		company.setUserName("tc");
		company.setEmail("tc@tc.com");
		company.setPhoneNumber("1234567890");
		company.setPassword("");
		company.setPasswordConfirmed("pass123");
		Errors errors = new BeanPropertyBindingResult(company, "company");
		//when
		getValidator().validate(company, errors);
		//then
		System.out.println(errors.toString());
		assertTrue(errors.hasErrors());
		assertTrue(company.getPassword().isEmpty());
	}
	
	@Test
	public void test_Company_Entered_No_Confirmation_Password() {
		//given
		Company company = new Company();
		company.setCompanyName("TestCompany");
		company.setUserName("tc");
		company.setEmail("tc@tc.com");
		company.setPhoneNumber("1234567890");
		company.setPassword("pass123");
		company.setPasswordConfirmed("");
		Errors errors = new BeanPropertyBindingResult(company, "company");
		//when
		getValidator().validate(company, errors);
		//then
		System.out.println(errors.toString());
		assertTrue(errors.hasErrors());
		assertTrue(company.getPasswordConfirmed().isEmpty());
	}
	
	@Test
	public void test_Company_Entered_Different_Confirmation_Password() {
		//given
		Company company = new Company();
		company.setCompanyName("TestCompany");
		company.setUserName("tc");
		company.setEmail("tc@tc.com");
		company.setPhoneNumber("1234567890");
		company.setPassword("pass123");
		company.setPasswordConfirmed("pass456");
		Errors errors = new BeanPropertyBindingResult(company, "company");
		//when
		getValidator().validate(company, errors);
		//then
		System.out.println(errors.toString());
		assertTrue(errors.hasErrors());
		assertFalse(company.getPassword().equals(company.getPasswordConfirmed()));
	}
	
}
