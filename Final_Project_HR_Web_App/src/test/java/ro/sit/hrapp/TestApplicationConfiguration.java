/*
 * Final_Project_HR_Web_App
 * ro.sit.hrapp  
 * TestApplicationConfiguration.java
 * 
 *
 * MADE FOR TRAINING PURPOSES.
 *
 */
package ro.sit.hrapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ro.sit.hrapp.dao.CandidateDAO;
import ro.sit.hrapp.dao.CompanyDAO;
import ro.sit.hrapp.dao.inmemory.IMCandidateDAO;
import ro.sit.hrapp.dao.inmemory.IMCompanyDAO;
import ro.sit.hrapp.service.CandidateService;
import ro.sit.hrapp.service.CompanyService;
import ro.sit.hrapp.service.MatchService;
import ro.sit.hrapp.service.validator.CandidateRegistrationValidator;
import ro.sit.hrapp.service.validator.CompanyRegistrationValidator;

/**
 * @author Sorin_Dragan
 *
 */
@Configuration
public class TestApplicationConfiguration {
	
	//Candidate beans
	
	@Bean
	public CandidateDAO candidateDAO() {
		return new IMCandidateDAO();
	}
	
	@Bean
	public CandidateService candidateService() {
		return new CandidateService();
	}
	
	@Bean 
	public CandidateRegistrationValidator candidateValidator() {
		return new CandidateRegistrationValidator();
	}
	
	//Company  beans
	
	@Bean
	public CompanyDAO companyDAO() {
		return new IMCompanyDAO();
	}
	
	@Bean
	public CompanyService companyService() {
		return new CompanyService();
	}
	
	@Bean 
	public CompanyRegistrationValidator companyValidator() {
		return new CompanyRegistrationValidator();
	}
	
	// Match beans
	@Bean
	public MatchService matchService() {
		return new MatchService();
	}

}
