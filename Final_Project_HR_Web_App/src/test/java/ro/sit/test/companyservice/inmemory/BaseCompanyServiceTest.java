package ro.sit.test.companyservice.inmemory;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import ro.sit.hrapp.domain.Company;
import ro.sit.hrapp.domain.JobDescription;
import ro.sit.hrapp.domain.JobDescription.CurrentJobTitle;
import ro.sit.hrapp.domain.JobDescription.Location;
import ro.sit.hrapp.domain.JobDescription.PersonalSkills;
import ro.sit.hrapp.domain.JobDescription.ProfessionalSkills;
import ro.sit.hrapp.domain.JobDescription.YearsOfExperience;
import ro.sit.hrapp.service.CandidateService;
import ro.sit.hrapp.service.CompanyService;
import ro.sit.hrapp.service.ValidationException;

public abstract class BaseCompanyServiceTest {

	protected abstract CompanyService getCompanyService();

	CompanyService compService = new CompanyService();
	CandidateService candService;
	JobDescription jd;
	List<JobDescription> jobList = new LinkedList<>();
    Company company ;
    
	// asta verifica daca lista de companii este goala la inceput ?
	@Test
	public void test_empty_get_all() {
		Collection<Company> company = getCompanyService().listAll();
		Assert.assertTrue(company.isEmpty());
	}

	@Test
	public void addCompany() throws ValidationException {

	   company = new Company();

		jobList.add(createJobDescriptionObject(JobDescription.CurrentJobTitle.BA,
				JobDescription.YearsOfExperience.ZERO_TO_ONE, JobDescription.Location.CLUJ_NAPOCA,
				JobDescription.ProfessionalSkills.JAVA, JobDescription.PersonalSkills.TEAM_PLAYER));
		// adding company

		company = createObjectFromCompany("nokia", "nokya", "phons", "nokia.emp@yahoo.com", "040-03457623", "phons",
				jobList, "Cluj-Napoca");
		getCompanyService().saveCompany(company);

		Collection<Company> comp = getCompanyService().getComp_dao().getAllCompanies();
		List<Company> list = new ArrayList<>(comp);
		assertEquals("nokia", list.get(0).getCompanyName());
		
	}
	@Test
	public void deleteCompany() throws ValidationException {

		 Company compToDelete = new Company();

		jobList.add(createJobDescriptionObject(JobDescription.CurrentJobTitle.BA,
				JobDescription.YearsOfExperience.ZERO_TO_ONE, JobDescription.Location.CLUJ_NAPOCA,
				JobDescription.ProfessionalSkills.JAVA, JobDescription.PersonalSkills.TEAM_PLAYER));
		// adding company

		compToDelete = createObjectFromCompany("endava", "nokya", "phons", "nokia.emp@yahoo.com", "040-03457623", "phons",
				jobList, "Cluj-Napoca");
		// saving company
		getCompanyService().saveCompany(compToDelete);
		
		// deleteing the company
        Long id = compToDelete.getId();
		getCompanyService().deleteCompany(id);
		Collection<Company> com = getCompanyService().getComp_dao().getAllCompanies();
		List<Company> list = new ArrayList<>(com);
		assertTrue(list.isEmpty());
		
	}

	 //@Test(expected = ValidationException.class)
	public void test_add_no_company_name() throws ValidationException {

		jobList.add(createJobDescriptionObject(JobDescription.CurrentJobTitle.BA,
				JobDescription.YearsOfExperience.ZERO_TO_ONE, JobDescription.Location.CLUJ_NAPOCA,
				JobDescription.ProfessionalSkills.JAVA, JobDescription.PersonalSkills.TEAM_PLAYER));

		company = createObjectFromCompany("", "nokya", "phons", "nokia.emp@yahoo.com", "040-03457623", "phons", jobList,
				"Cluj-Napoca");
		getCompanyService().saveCompany(company);

	}

	// @Test(expected = ValidationException.class)
	public void test_add_no_username() throws ValidationException {

		jobList.add(createJobDescriptionObject(JobDescription.CurrentJobTitle.BA,
				JobDescription.YearsOfExperience.ZERO_TO_ONE, JobDescription.Location.CLUJ_NAPOCA,
				JobDescription.ProfessionalSkills.JAVA, JobDescription.PersonalSkills.TEAM_PLAYER));

		company = createObjectFromCompany("nokia", "", "phons", "nokia.emp@yahoo.com", "040-03457623", "phons", jobList,
				"Cluj-Napoca");
		getCompanyService().saveCompany(company);

	}

	private JobDescription createJobDescriptionObject(CurrentJobTitle ba, YearsOfExperience zeroToOne,
			Location clujNapoca, ProfessionalSkills java, PersonalSkills teamPlayer) {
		JobDescription jd = new JobDescription();
		jd.setCurrentJob(ba);
		jd.setYearsOfExperience(zeroToOne);
		jd.setPrefferedLocation(clujNapoca);
		jd.setProfessionalSkills(java);
		jd.setPersonalSkills(teamPlayer);
		return jd;
	}

	private Company createObjectFromCompany(String companyName, String userName, String password, String email,
			String phone, String confirmedPassword, List jobList, String jobLocation) {
		Company company = new Company();
		company.setCompanyName(companyName);
		company.setUserName(userName);
		company.setPassword(password);
		company.setEmail(email);
		company.setPhone(phone);
		company.setPasswordConfirmed(confirmedPassword);
		company.setJobDescription(jobList);
		company.setJobLocation(jobLocation);
		return company;
	}

	@After
	public void tearDown() {
		Collection<Company> companys = new LinkedList<Company>();

		for (Company company : companys) {
			getCompanyService().deleteCompany(company.getId());
		}
	}
}
