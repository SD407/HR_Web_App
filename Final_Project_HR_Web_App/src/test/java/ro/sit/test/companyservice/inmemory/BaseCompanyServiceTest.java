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
	Company company;

	// asta verifica daca lista de companii este goala la inceput ?
	// @Test
	public void test_empty_get_all() {
		Collection<Company> company = getCompanyService().listAll();
		Assert.assertTrue(company.isEmpty());
	}

	// test negativ - dau o companie si caut alta !
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

		getCompanyService().getComp_dao().deleteCompany(company);

	}

	 @Test
	public void deleteCompany() throws ValidationException {

		Company compToDelete = new Company();

		jobList.add(createJobDescriptionObject(JobDescription.CurrentJobTitle.BA,
				JobDescription.YearsOfExperience.ZERO_TO_ONE, JobDescription.Location.CLUJ_NAPOCA,
				JobDescription.ProfessionalSkills.JAVA, JobDescription.PersonalSkills.TEAM_PLAYER));
		// adding company

		compToDelete = createObjectFromCompany("endava", "nokya", "phons", "nokia.emp@yahoo.com", "040-03457623",
				"phons", jobList, "Cluj-Napoca");
		// saving company
		getCompanyService().saveCompany(compToDelete);

		// deleteing the company
		Long id = compToDelete.getId();
		getCompanyService().deleteCompany(id);
		Collection<Company> com = getCompanyService().getComp_dao().getAllCompanies();
		List<Company> list = new ArrayList<>(com);
		assertTrue(list.isEmpty());

	}

	@Test
	public void getCompany() throws ValidationException {

		Company compToGet = new Company();
		Company comp1 = new Company();
		Company comp2 = new Company();

		jobList.add(createJobDescriptionObject(JobDescription.CurrentJobTitle.BA,
				JobDescription.YearsOfExperience.ZERO_TO_ONE, JobDescription.Location.CLUJ_NAPOCA,
				JobDescription.ProfessionalSkills.JAVA, JobDescription.PersonalSkills.TEAM_PLAYER));
		// adding companys

		comp1 = createObjectFromCompany("endava", "nokya", "phons", "nokia.emp@yahoo.com", "040-03457623", "phons",
				jobList, "Cluj-Napoca");
		getCompanyService().saveCompany(comp1);

		comp2 = createObjectFromCompany("bosh", "nokya", "phons", "nokia.emp@yahoo.com", "040-03457623", "phons",
				jobList, "Cluj-Napoca");
		getCompanyService().saveCompany(comp2);

		Collection<Company> com = getCompanyService().getComp_dao().getAllCompanies();
		List<Company> list = new ArrayList<>(com);
		assertEquals(comp1.getClass(), list.get(0).getClass());//?

	}

	@Test
	public void test_add_no_company_name() {

		jobList.add(createJobDescriptionObject(JobDescription.CurrentJobTitle.BA,
				JobDescription.YearsOfExperience.ZERO_TO_ONE, JobDescription.Location.CLUJ_NAPOCA,
				JobDescription.ProfessionalSkills.JAVA, JobDescription.PersonalSkills.TEAM_PLAYER));

		company = createObjectFromCompany("", "nokya", "phons", "nokia.emp@yahoo.com", "040-03457623", "phons",
				jobList, "Cluj-Napoca");
		try {
			getCompanyService().saveCompany(company);
		} catch (ValidationException e) {
			assertEquals("[Company name is Empty]", e.getMessage().toString());
		}

		// golire imdao
		getCompanyService().getComp_dao().deleteCompany(company);

	}

	 @Test
	public void test_add_no_username() {

		jobList.add(createJobDescriptionObject(JobDescription.CurrentJobTitle.BA,
				JobDescription.YearsOfExperience.ZERO_TO_ONE, JobDescription.Location.CLUJ_NAPOCA,
				JobDescription.ProfessionalSkills.JAVA, JobDescription.PersonalSkills.TEAM_PLAYER));

		company = createObjectFromCompany("nokia", null, "phons", "nokia.emp@yahoo.com", "040-03457623", "phons",
				jobList, "Cluj-Napoca");
		try {
			getCompanyService().saveCompany(company);
		} catch (ValidationException e) {
			assertEquals("[UserName is Empty]", e.getMessage().toString());
			e.printStackTrace();
		}

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
