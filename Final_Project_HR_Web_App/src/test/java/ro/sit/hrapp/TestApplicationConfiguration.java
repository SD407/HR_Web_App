package ro.sit.hrapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ro.sit.hrapp.dao.CandidateDAO;
import ro.sit.hrapp.dao.CompanyDAO;
import ro.sit.hrapp.imdao.IMCandidateDAO;
import ro.sit.hrapp.imdao.IMCompanyDAO;
import ro.sit.hrapp.service.CandidateService;
import ro.sit.hrapp.service.CompanyService;

// cele doua servicii si doua dao
@Configuration
public class TestApplicationConfiguration {

	@Bean
	public CompanyService companyService() {
		return new CompanyService();
	}

	@Bean
	public CompanyDAO companyDAO() {
		return new IMCompanyDAO();

	}

	@Bean
	public CandidateService candidateService() {
		return new CandidateService();
	}

	@Bean
	public CandidateDAO candidateDAO() {
		return new IMCandidateDAO();
	}
}
