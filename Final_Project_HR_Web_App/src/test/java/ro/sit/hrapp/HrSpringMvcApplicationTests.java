package ro.sit.hrapp;

import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import ro.sit.hrapp.domain.Candidate;
import ro.sit.hrapp.domain.Company;
import ro.sit.hrapp.domain.JobDescription;
import ro.sit.hrapp.service.CandidateService;
import ro.sit.hrapp.service.CompanyService;
import ro.sit.hrapp.domain.JobDescription.CurrentJobTitle.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = HrSpringMvcApplication.class)
@WebAppConfiguration
public class HrSpringMvcApplicationTests {

	CompanyService compService;
	CandidateService candService;
	JobDescription jd;

	@Before
	public void init() {

	}

	private Candidate createObjectFromCandidate(String firstName, String lastName, String userName, String password,
			String confirmedPassword, String email, String phone, JobDescription jobProfile) {
		Candidate candidate = new Candidate();
		candidate.setFirstName(firstName);
		candidate.setLastName(lastName);
		candidate.setUserName(userName);
		candidate.setPassword(password);
		candidate.setPasswordConfirmed(confirmedPassword);
		candidate.setEmail(email);
		candidate.setPhone(phone);
		candidate.setJobDescription(jobProfile);
		return candidate;

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

	@Test
	public void testDeleteCompany() {

	}

	@Test
	public void testSaveCompany() {
		List<JobDescription>jobList = new LinkedList<>();
		jobList.add(createJobDescriptionObject(jd.));
		Company company = createObjectFromCompany("nokia","nokya","phons","nokia.emp@yahoo.com","040-03457623",
				"phons",jobList,"Cluj-Napoca");

	}

	@After
	public void destroy() {

	}

}
