/*
 * Final_Project_HR_Web_App
 * ro.sit.hrapp.service.validator  
 * CompanyValidator.java
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

import ro.sit.hrapp.domain.Company;


/**
 * @author Sorin_Dragan
 *
 */

@Component
public class CompanyRegistrationValidator implements Validator {

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
		return Company.class.equals(clazz);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object target, Errors errors) {
		Company company = (Company) target;
		
		// company name validation
		if (company.getCompanyName().isEmpty() || company.getCompanyName() == null) {
			errors.rejectValue("companyName", "companyName.isEmpty", "Please enter your company name.");
		}

		// user name validation
		if (company.getUserName().isEmpty() || company.getUserName() == null) {
			errors.rejectValue("userName", "userName.isEmpty", "Please enter your username.");
		}
		
		// phone number validation in spring
		if (company.getPhoneNumber() == null || company.getPhoneNumber().isEmpty()) {
			errors.rejectValue("phoneNumber", "phoneNumber.isEmpty", "Please enter your phone number.");
		} else {
			Pattern pattern = Pattern.compile(PHONE_NUMBER_PATTERN);
			Matcher matcher = pattern.matcher(company.getPhoneNumber());
			if (!matcher.matches()) {
				errors.rejectValue("phoneNumber", "phoneNumber.incorrect", "Enter a correct phone number.");
			}
		}
		
		// email validation in spring
		if (company.getEmail() == null || company.getEmail().isEmpty()) {
			errors.rejectValue("email", "email.isEmpty", "Please enter your email.");
		} else {
			Pattern pattern = Pattern.compile(EMAIL_PATTERN);
			Matcher matcher = pattern.matcher(company.getEmail());
			if (!matcher.matches()) {
				errors.rejectValue("email", "email.incorrect", "Enter a correct email.");
			}
		}

		// password validation
		if (company.getPassword().isEmpty() || company.getPassword() == null) {
			errors.rejectValue("password", "password.isEmpty", "Please enter a password.");
		}
		
		if (company.getPasswordConfirmed().isEmpty() || company.getPasswordConfirmed() == null) {
			errors.rejectValue("passwordConfirmed", "passwordConfirmed.isEmpty",
					"Please enter your confirmation password.");
		}
		
		if (company.getPassword().length() < 6) {
			errors.rejectValue("password", "password.incorrectSize", 
					"Password should be at least 6 characters long");
		}
		
		if (company.getPasswordConfirmed().length() < 6) {
			errors.rejectValue("passwordConfirmed", "PasswordConfirmed.incorrectSize", 
					"Confirmed password should be at least 6 characters long");
		}
		
		if (!company.getPassword().equals(company.getPasswordConfirmed())) {
			errors.rejectValue("passwordConfirmed", "passwordConfirmed.notmatch", "Passwords don't match.");
		}

	}

}


