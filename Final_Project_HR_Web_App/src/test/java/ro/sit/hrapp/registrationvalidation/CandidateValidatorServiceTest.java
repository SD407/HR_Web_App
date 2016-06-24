/*
 * SpringMVCTest
 * ro.sci.mvctest  
 * ApplicantServiceTest.java
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

import ro.sit.hrapp.domain.Candidate;
import ro.sit.hrapp.service.CandidateService;
import ro.sit.hrapp.service.validator.CandidateRegistrationValidator;

/**
 * @author Sorin_Dragan
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class CandidateValidatorServiceTest {
	
	protected abstract CandidateService getCandidateService();
	
	protected abstract CandidateRegistrationValidator getValidator();

	@After
	public void tearDown() {
		Collection<Candidate> candidates = new LinkedList<Candidate>(getCandidateService().listAll());

		for (Candidate candidate : candidates) {
			getCandidateService().deleteCandidate(candidate.getId());
		}
	}

	@Test
	public void testEmptyGetAll() {
		Collection<Candidate> applicants = getCandidateService().listAll();
		Assert.assertTrue(applicants.isEmpty());
	}
	
	@Test
	public void test_User_Entered_Correct_Details() {
		//given
		Candidate candidate = new Candidate();
		candidate.setFirstName("john");
		candidate.setLastName("smith");
		candidate.setUserName("johnny");
		candidate.setEmail("johnny@gmail.com");
		candidate.setPhoneNumber("1234567890");
		candidate.setPassword("pass123");
		candidate.setPasswordConfirmed("pass123");
		Errors errors = new BeanPropertyBindingResult(candidate, "candidate");
		//when
		getValidator().validate(candidate, errors);
		//then
		System.out.println(errors.toString());
		assertFalse(errors.hasErrors());
	}
	
	@Test 
	public void test_User_Entered_No_First_Name() {
		//given
		Candidate candidate = new Candidate();
		candidate.setFirstName("");
		candidate.setLastName("smith");
		candidate.setUserName("johnny");
		candidate.setEmail("johnny@gmail.com");
		candidate.setPhoneNumber("1234567890");
		candidate.setPassword("pass123");
		candidate.setPasswordConfirmed("pass123");
		Errors errors = new BeanPropertyBindingResult(candidate, "candidate");
		//when
		getValidator().validate(candidate, errors);
		//then
		System.out.println(errors.getFieldError().getDefaultMessage().toString());
		assertTrue(errors.hasErrors());
		assertTrue(candidate.getFirstName().isEmpty());
	}
	
	@Test 
	public void test_User_Entered_No_Last_Name() {
		//given
		Candidate candidate = new Candidate();
		candidate.setFirstName("john");
		candidate.setLastName("");
		candidate.setUserName("johnny");
		candidate.setEmail("johnny@gmail.com");
		candidate.setPhoneNumber("1234567890");
		candidate.setPassword("pass123");
		candidate.setPasswordConfirmed("pass123");
		Errors errors = new BeanPropertyBindingResult(candidate, "candidate");
		//when
		getValidator().validate(candidate, errors);
		//then
		System.out.println(errors.toString());
		assertTrue(errors.hasErrors());
		assertTrue(candidate.getLastName().isEmpty());
	}
	
	@Test 
	public void test_User_Entered_No_Username() {
		//given
		Candidate candidate = new Candidate();
		candidate.setFirstName("john");
		candidate.setLastName("smith");
		candidate.setUserName("");
		candidate.setEmail("johnny@gmail.com");
		candidate.setPhoneNumber("1234567890");
		candidate.setPassword("pass123");
		candidate.setPasswordConfirmed("pass123");
		Errors errors = new BeanPropertyBindingResult(candidate, "candidate");
		//when
		getValidator().validate(candidate, errors);
		//then
		System.out.println(errors.toString());
		assertTrue(errors.hasErrors());
		assertTrue(candidate.getUserName().isEmpty());
	}
	
	@Test 
	public void test_User_Entered_No_Email() {
		//given
		Candidate candidate = new Candidate();
		candidate.setFirstName("john");
		candidate.setLastName("smith");
		candidate.setUserName("johnny");
		candidate.setEmail("");
		candidate.setPhoneNumber("1234567890");
		candidate.setPassword("pass123");
		candidate.setPasswordConfirmed("pass123");
		Errors errors = new BeanPropertyBindingResult(candidate, "candidate");
		//when
		getValidator().validate(candidate, errors);
		//then
		System.out.println(errors.toString());
		assertTrue(errors.hasErrors());
		assertTrue(candidate.getEmail().isEmpty());
	}
	
	@Test 
	public void test_User_Entered_Incorrect_Email() {
		//given
		Candidate candidate = new Candidate();
		candidate.setFirstName("john");
		candidate.setLastName("smith");
		candidate.setUserName("johnny");
		candidate.setEmail("johnny@gmail");
		candidate.setPhoneNumber("1234567890");
		candidate.setPassword("pass123");
		candidate.setPasswordConfirmed("pass123");
		Errors errors = new BeanPropertyBindingResult(candidate, "candidate");
		//when
		getValidator().validate(candidate, errors);
		//then
		System.out.println(errors.toString());
		assertTrue(errors.hasErrors());
	}
	
	@Test 
	public void test_User_Entered_No_Phone_Number() {
		//given
		Candidate candidate = new Candidate();
		candidate.setFirstName("john");
		candidate.setLastName("smith");
		candidate.setUserName("johnny");
		candidate.setEmail("johnny@gmail.com");
		candidate.setPhoneNumber("");
		candidate.setPassword("pass123");
		candidate.setPasswordConfirmed("pass123");
		Errors errors = new BeanPropertyBindingResult(candidate, "candidate");
		//when
		getValidator().validate(candidate, errors);
		//then
		System.out.println(errors.toString());
		assertTrue(errors.hasErrors());
		assertTrue(candidate.getPhoneNumber().isEmpty());
	}
	
	@Test 
	public void test_User_Entered_Incorrect_Phone_Number() {
		//given
		Candidate candidate = new Candidate();
		candidate.setFirstName("john");
		candidate.setLastName("smith");
		candidate.setUserName("johnny");
		candidate.setEmail("johnny@gmail.com");
		candidate.setPhoneNumber("123456789"); // phone number should be 10 digits long
		candidate.setPassword("pass123");
		candidate.setPasswordConfirmed("pass123");
		Errors errors = new BeanPropertyBindingResult(candidate, "candidate");
		//when
		getValidator().validate(candidate, errors);
		//then
		System.out.println(errors.toString());
		assertTrue(errors.hasErrors());
	}
	
	@Test 
	public void test_User_Entered_No_Password() {
		//given
		Candidate candidate = new Candidate();
		candidate.setFirstName("john");
		candidate.setLastName("smith");
		candidate.setUserName("johnny");
		candidate.setEmail("johnny@gmail.com");
		candidate.setPhoneNumber("1234567890");
		candidate.setPassword("");
		candidate.setPasswordConfirmed("pass123");
		Errors errors = new BeanPropertyBindingResult(candidate, "candidate");
		//when
		getValidator().validate(candidate, errors);
		//then
		System.out.println(errors.toString());
		assertTrue(errors.hasErrors());
		assertTrue(candidate.getPassword().isEmpty());
	}
	
	@Test 
	public void test_User_Entered_No_Confirmation_Password() {
		//given
		Candidate candidate = new Candidate();
		candidate.setFirstName("john");
		candidate.setLastName("smith");
		candidate.setUserName("johnny");
		candidate.setEmail("johnny@gmail.com");
		candidate.setPhoneNumber("1234567890");
		candidate.setPassword("pass123");
		candidate.setPasswordConfirmed("");
		Errors errors = new BeanPropertyBindingResult(candidate, "candidate");
		//when
		getValidator().validate(candidate, errors);
		//then
		System.out.println(errors.toString());
		assertTrue(errors.hasErrors());
		assertTrue(candidate.getPasswordConfirmed().isEmpty());
	}
	
	@Test 
	public void test_User_Entered_Different_Confirmation_Password() {
		//given
		Candidate candidate = new Candidate();
		candidate.setFirstName("john");
		candidate.setLastName("smith");
		candidate.setUserName("johnny");
		candidate.setEmail("johnny@gmail.com");
		candidate.setPhoneNumber("1234567890");
		candidate.setPassword("pass123");
		candidate.setPasswordConfirmed("pass456");
		Errors errors = new BeanPropertyBindingResult(candidate, "candidate");
		//when
		getValidator().validate(candidate, errors);
		//then
		System.out.println(errors.toString());
		assertTrue(errors.hasErrors());
		assertFalse(candidate.getPassword().equals(candidate.getPasswordConfirmed()));
	}
	
}