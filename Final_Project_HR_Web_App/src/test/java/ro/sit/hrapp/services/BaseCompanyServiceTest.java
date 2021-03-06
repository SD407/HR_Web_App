/*
 * Final_Project_HR_Web_App
 * ro.sit.hrapp.services  
 * BaseCompanyServiceTest.java
 * 
 *
 * MADE FOR TRAINING PURPOSES.
 * 
 * MADE SOME REFACTORING @SorinDRAGAN
 * 
 */

package ro.sit.hrapp.services;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import ro.sit.hrapp.domain.Company;
import ro.sit.hrapp.domain.JobDescription;
import ro.sit.hrapp.service.CompanyService;
import ro.sit.hrapp.service.validator.CompanyRegistrationValidator;

/**
 * 
 * @author stefan
 * 
 */
public abstract class BaseCompanyServiceTest {

	protected abstract CompanyService getCompanyService();

	protected abstract CompanyRegistrationValidator getValidator();

	CompanyService companyService = new CompanyService();
	List<JobDescription> jobList = new LinkedList<>();

	@After
	public void tearDown() {
		Collection<Company> companies = new LinkedList<Company>();

		for (Company company : companies) {
			getCompanyService().deleteCompany(company.getId());
		}
	}

	// tests that the company list is empty from the beginning
	@Test
	public void test_empty_get_all() {
		Collection<Company> company = getCompanyService().listAll();
		Assert.assertTrue(company.isEmpty());
	}

	@Test
	public void companyListNotEmpty() {
		// given
		jobList.add(createJobDescriptionObject());
		Company comp1 = createObjectFromCompany("endava", "nokya", "phones", "nokia.emp@yahoo.com", "1234567890",
				"phones", jobList, "Cluj-Napoca");
		Company comp2 = createObjectFromCompany("bosh", "nokya", "phones", "nokia.emp@yahoo.com", "1234567890",
				"phones", jobList, "Cluj-Napoca");
		Errors errors1 = new BeanPropertyBindingResult(comp1, "comp1");
		Errors errors2 = new BeanPropertyBindingResult(comp2, "comp2");
		// when
		getValidator().validate(comp1, errors1); // validate fields from company
		getValidator().validate(comp2, errors2); // validate fields from company

		// adding two companies
		getCompanyService().saveCompany(comp1);
		getCompanyService().saveCompany(comp2);

		// then
		assertFalse(getCompanyService().getCompanyDAO().getAll().isEmpty());
	}

	@Test
	public void addCompany() {
		// given
		jobList.add(createJobDescriptionObject());
		Company company = createObjectFromCompany("nokia", "nokya", "phones", "nokia.emp@yahoo.com", "1234567890",
				"phones", jobList, "Cluj-Napoca");
		Errors errors = new BeanPropertyBindingResult(company, "company");
		// when
		getValidator().validate(company, errors);
		getCompanyService().saveCompany(company);
		Collection<Company> comp = getCompanyService().listAll();
		List<Company> companyList = new ArrayList<>(comp);
		// then
		System.out.println(errors.toString());
		assertFalse(errors.hasErrors()); // validate fields from company
		assertEquals("nokia", companyList.get(0).getCompanyName());
		getCompanyService().getCompanyDAO().delete(company);

	}

	@Test
	public void deleteCompany() {
		// given
		jobList.add(createJobDescriptionObject());
		Company companyToDelete = createObjectFromCompany("endava", "nokya", "phones", "nokia.emp@yahoo.com",
				"1234567890", "phones", jobList, "Cluj-Napoca");
		Errors errors = new BeanPropertyBindingResult(companyToDelete, "company");
		// when
		getValidator().validate(companyToDelete, errors); // validate fields
															// from company
		getCompanyService().saveCompany(companyToDelete);
		Collection<Company> companies = getCompanyService().listAll();
		Long id = companyToDelete.getId();
		getCompanyService().deleteCompany(id);
		// then
		System.out.println(errors.toString());
		assertFalse(errors.hasErrors());
		assertTrue(companies.isEmpty());

	}

	@Test
	public void findCompanyById() {
		// given
		jobList.add(createJobDescriptionObject());
		Company company = createObjectFromCompany("nokia", "nokya", "phones", "nokia.emp@yahoo.com", "1234567890",
				"phones", jobList, "Cluj-Napoca");
		getCompanyService().saveCompany(company);
		// when
		Long companyId = company.getId();
		assertEquals(company, getCompanyService().getCompanyDAO().findById(companyId));

	}

	@Test
	public void getCompany() {
		// given
		jobList.add(createJobDescriptionObject());
		Company comp1 = createObjectFromCompany("endava", "nokya", "phones", "nokia.emp@yahoo.com", "1234567890",
				"phones", jobList, "Cluj-Napoca");
		Company comp2 = createObjectFromCompany("bosh", "nokya", "phones", "nokia.emp@yahoo.com", "1234567890",
				"phones", jobList, "Cluj-Napoca");
		Errors errors1 = new BeanPropertyBindingResult(comp1, "comp1");
		Errors errors2 = new BeanPropertyBindingResult(comp2, "comp2");
		// when
		getValidator().validate(comp1, errors1); // validate fields from company
		getValidator().validate(comp2, errors2); // validate fields from company

		getCompanyService().saveCompany(comp1);
		getCompanyService().saveCompany(comp2);

		Collection<Company> com = getCompanyService().getCompanyDAO().getAll();
		List<Company> list = new ArrayList<>(com);

		// then
		assertFalse(errors1.hasErrors());
		System.out.println(errors1.toString());
		assertFalse(errors2.hasErrors());
		System.out.println(errors2.toString());

		assertEquals(comp1.getClass(), list.get(0).getClass());

	}

	@Test
	public void test_add_no_company_name() {
		// given
		jobList.add(createJobDescriptionObject());
		Company company = createObjectFromCompany("", "nokya", "phones", "nokia.emp@yahoo.com", "1234567890", "phones",
				jobList, "Cluj-Napoca");
		Errors errors = new BeanPropertyBindingResult(company, "company");
		// when
		getValidator().validate(company, errors); // validate fields from
													// company
		getCompanyService().saveCompany(company);
		// then
		assertEquals("Please enter your company name.", errors.getFieldError().getDefaultMessage());

		getCompanyService().getCompanyDAO().delete(company);

	}

	@Test
	public void test_add_no_username() {

		jobList.add(createJobDescriptionObject());
		Company company = createObjectFromCompany("nokia", "", "phones", "nokia.emp@yahoo.com", "1234567890", "phones",
				jobList, "Cluj-Napoca");
		Errors errors = new BeanPropertyBindingResult(company, "company");
		// when
		getValidator().validate(company, errors); // validate fields from
													// company
		getCompanyService().saveCompany(company);
		// then
		assertEquals("Please enter your username.", errors.getFieldError().getDefaultMessage());

		getCompanyService().getCompanyDAO().delete(company);
	}

	private JobDescription createJobDescriptionObject() {
		JobDescription jd = new JobDescription();
		jd.setCurrentJobTitle("JAVA");
		jd.setYearOfExperience("0-1");
		jd.setLocation("CLUJ-NAPOCA");
		jd.setProfessionalSkills("JAVA,SPRING,JDBC");
		jd.setPersonalSkills("TEAM PLAYER,HARD WORKING");
		return jd;
	}

	private Company createObjectFromCompany(String companyName, String userName, String password, String email,
			String phone, String confirmedPassword, List<JobDescription> jobList, String jobLocation) {

		Company company = new Company();
		company.setCompanyName(companyName);
		company.setUserName(userName);
		company.setPassword(password);
		company.setEmail(email);
		company.setPhoneNumber(phone);
		company.setPasswordConfirmed(confirmedPassword);
		company.setJobDescription(jobList);
		company.setJobLocation(jobLocation);
		return company;
	}

//	no longer needed
//	private String listProfessionalSkills() {
//		List<ProfessionalSkills> proSkils = new LinkedList<ProfessionalSkills>();
//		proSkils.add(JobDescription.ProfessionalSkills.JAVA);
//		proSkils.add(JobDescription.ProfessionalSkills.SPRING);
//		proSkils.add(JobDescription.ProfessionalSkills.JDBC);
//
//		return proSkils;
//	}
//
//	private List<PersonalSkills> listPersonalSkills() {
//		List<PersonalSkills> persSkils = new LinkedList<PersonalSkills>();
//		persSkils.add(JobDescription.PersonalSkills.GOOD_COMMNUNICATOR);
//		persSkils.add(JobDescription.PersonalSkills.TEAM_PLAYER);
//		persSkils.add(JobDescription.PersonalSkills.GOOD_LISTENER);
//		return persSkils;
//	}

}
