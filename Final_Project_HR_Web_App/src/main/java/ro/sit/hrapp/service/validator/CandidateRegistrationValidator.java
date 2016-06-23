/*
 * Final_Project_HR_Web_App
 * ro.sit.hrapp.service.validator  
 * CandidateValidator.java
 * 
 *
 * MADE FOR TRAINING PURPOSES.
 *
 */
package ro.sit.hrapp.service.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ro.sit.hrapp.domain.Candidate;


/**
 * @author Sorin_Dragan
 *
 */

@Component
public class CandidateRegistrationValidator implements Validator {

	private static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	private static final String PHONE_NUMBER_PATTERN = 
			"^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$";

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return Candidate.class.equals(clazz);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object target, Errors errors) {
		Candidate candidate = (Candidate) target;
		
		// first name validation
		if (candidate.getFirstName().isEmpty() || candidate.getFirstName() == null) {
			errors.rejectValue("firstName", "firstName.isEmpty", "Please enter your first name.");
		}
		
		// last name validation
		if (candidate.getLastName().isEmpty() || candidate.getLastName() == null) {
			errors.rejectValue("lastName", "lastName.isEmpty", "Please enter your last name.");
		}

		// user name validation
		if (candidate.getUserName().isEmpty() || candidate.getUserName() == null) {
			errors.rejectValue("userName", "userName.isEmpty", "Please enter your username.");
		}
		
		// phone number validation in spring
		if (candidate.getPhoneNumber() == null || candidate.getPhoneNumber().isEmpty()) {
			errors.rejectValue("phoneNumber", "phoneNumber.isEmpty", "Please enter your phone number.");
		} else {
			Pattern pattern = Pattern.compile(PHONE_NUMBER_PATTERN);
			Matcher matcher = pattern.matcher(candidate.getPhoneNumber());
			if (!matcher.matches()) {
				errors.rejectValue("phoneNumber", "phoneNumber.incorrect", "Enter a correct phone number.");
			}
		}
		
		// email validation in spring
		if (candidate.getEmail() == null || candidate.getEmail().isEmpty()) {
			errors.rejectValue("email", "email.isEmpty", "Please enter your email.");
		} else {
			Pattern pattern = Pattern.compile(EMAIL_PATTERN);
			Matcher matcher = pattern.matcher(candidate.getEmail());
			if (!matcher.matches()) {
				errors.rejectValue("email", "email.incorrect", "Enter a correct email.");
			}
		}

		// password validation
		if (candidate.getPassword().isEmpty() || candidate.getPassword() == null) {
			errors.rejectValue("password", "password.isEmpty", "Please enter a password.");
		}
		
		if (candidate.getPasswordConfirmed().isEmpty() || candidate.getPasswordConfirmed() == null) {
			errors.rejectValue("passwordConfirmed", "passwordConfirmed.isEmpty",
					"Please enter your confirmation password.");
		}
		
		if (candidate.getPassword().length() < 6) {
			errors.rejectValue("password", "password.incorrectSize", 
					"Password should be at least 6 characters long.");
		}
		
		if (candidate.getPasswordConfirmed().length() < 6) {
			errors.rejectValue("passwordConfirmed", "PasswordConfirmed.incorrectSize", 
					"Confirmed password should be at least 6 characters long.");
		}
		
		if (!candidate.getPassword().equals(candidate.getPasswordConfirmed())) {
			errors.rejectValue("passwordConfirmed", "passwordConfirmed.notmatch", "Passwords don't match.");
		}

	}

}

