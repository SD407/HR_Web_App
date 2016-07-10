package ro.sit.hrapp.services;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.After;
import org.junit.Test;

import ro.sit.hrapp.domain.Candidate;
import ro.sit.hrapp.domain.Company;
import ro.sit.hrapp.domain.JobDescription;
import ro.sit.hrapp.domain.JobDescription.CurrentJobTitle;
import ro.sit.hrapp.domain.JobDescription.Location;
import ro.sit.hrapp.domain.JobDescription.PersonalSkills;
import ro.sit.hrapp.domain.JobDescription.ProfessionalSkills;
import ro.sit.hrapp.domain.JobDescription.YearsOfExperience;
import ro.sit.hrapp.service.CandidateService;
import ro.sit.hrapp.service.CompanyService;
import ro.sit.hrapp.service.MatchService;

public abstract class BaseMatchServiceTest {

	protected abstract MatchService getMatchService();

	protected abstract CompanyService getCompanyService();

	protected abstract CandidateService getCandidateService();

	MatchService matchService = new MatchService();
	CompanyService companyService = new CompanyService();
	List<JobDescription> jobList = new LinkedList<>();
	JobDescription jd1 = new JobDescription();
	JobDescription jd2 = new JobDescription();
	JobDescription jd3 = new JobDescription();
	List<Candidate> listCandidates = new LinkedList<>();

	@After
	public void tearDown() {

		Collection<Company> companies = getCompanyService().listAll();
		Collection<Candidate> candidates = getCandidateService().listAll();

		for (Company company : companies) {
			getCompanyService().deleteCompany(company.getId());
		}
		for (Candidate candidate : candidates) {
			getCandidateService().deleteCandidate(candidate.getId());
		}
	}

	@Test
	public void testMatch() {
		// given

		// company
		jobList.add(createJobDescriptionObject(JobDescription.CurrentJobTitle.JAVA,
				JobDescription.YearsOfExperience.ZERO_TO_ONE, JobDescription.Location.CLUJ_NAPOCA,
				listProfessionalSkillsCompany(), listPersonalSkillsCompany()));
		Company company = createObjectFromCompany("nokia", "nokya", "phones", "nokia.emp@yahoo.com", "1234567890",
				"phones", jobList, "Cluj-Napoca");

		// candidate 1
		jd1 = (createJobDescriptionObject(JobDescription.CurrentJobTitle.JAVA,
				JobDescription.YearsOfExperience.FIVE_TO_EIGHT, JobDescription.Location.CLUJ_NAPOCA,
				listProfessionalSkills1(), listPersonalSkills1()));
		Candidate candidate1 = createObjectFromCandidate("biro", "stefan", "junior", "stefanbiro.emp@yahoo.com",
				"1234567890", "javajunior", "javajunior", "Cluj-Napoca", jd1);

		// candidate 2
		jd2 = (createJobDescriptionObject(JobDescription.CurrentJobTitle.JAVA,
				JobDescription.YearsOfExperience.THREE_TO_FIVE, JobDescription.Location.CLUJ_NAPOCA,
				listProfessionalSkills2(), listPersonalSkills2()));
		Candidate candidate2 = createObjectFromCandidate("bara", "crina", "junior", "stefanbiro.emp@yahoo.com",
				"1234567890", "javajunior", "javajunior", "Cluj-Napoca", jd2);

		// candidate 3
		jd3 = (createJobDescriptionObject(JobDescription.CurrentJobTitle.JAVA,
				JobDescription.YearsOfExperience.ZERO_TO_ONE, JobDescription.Location.CLUJ_NAPOCA,
				listProfessionalSkills3(), listPersonalSkills3()));
		Candidate candidate3 = createObjectFromCandidate("bocean", "stefan", "junior", "stefanbiro.emp@yahoo.com",
				"1234567890", "javajunior", "javajunior", "Cluj-Napoca", jd3);

		// saving candidates

		getCandidateService().saveCandidate(candidate1);
		getCandidateService().saveCandidate(candidate2);
		getCandidateService().saveCandidate(candidate3);

		// generating candidateId
		long id1 = candidate1.getId();
		long id2 = candidate2.getId();
		long id3 = candidate3.getId();

		// create a candidate list from dao

		listCandidates.add(getCandidateService().getDAO().findByIdCandidate(id1));
		listCandidates.add(getCandidateService().getDAO().findByIdCandidate(id2));
		listCandidates.add(getCandidateService().getDAO().findByIdCandidate(id3));

		List<Long> match = new LinkedList<>();

		match = matchService.searchMatchForCompany(company, listCandidates);

		//System.out.println(match.size());
		
		Candidate c1 = new Candidate();
		for (long l : match) {
			c1 = getCandidateService().getDAO().findByIdCandidate(l);
			System.out.println(c1.getFirstName());
			System.out.println(c1.getMatch());
		}
		
	}

	private JobDescription createJobDescriptionObject(CurrentJobTitle ba, YearsOfExperience zeroToOne,
			Location clujNapoca, List<ProfessionalSkills> profesionalSkils, List<PersonalSkills> personalSkils) {

		JobDescription jd = new JobDescription();
		jd.setCurrentJobTitle(ba);
		jd.setYearOfExperience(zeroToOne);
		jd.setLocation(clujNapoca);
		jd.setProfessionalSkills(profesionalSkils);
		jd.setPersonalSkills(personalSkils);
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

	private List<ProfessionalSkills> listProfessionalSkillsCompany() {
		List<ProfessionalSkills> proSkils = new LinkedList<ProfessionalSkills>();
		proSkils.add(JobDescription.ProfessionalSkills.JAVA);
		proSkils.add(JobDescription.ProfessionalSkills.SPRING);
		proSkils.add(JobDescription.ProfessionalSkills.JDBC);
		proSkils.add(JobDescription.ProfessionalSkills.QBASE);
		proSkils.add(JobDescription.ProfessionalSkills.TEST);

		return proSkils;
	}

	private List<ProfessionalSkills> listProfessionalSkills1() {
		List<ProfessionalSkills> proSkils = new LinkedList<ProfessionalSkills>();
    	//proSkils.add(JobDescription.ProfessionalSkills.QBASE);
		//proSkils.add(JobDescription.ProfessionalSkills.TEST);
		proSkils.add(JobDescription.ProfessionalSkills.JAVA);
		proSkils.add(JobDescription.ProfessionalSkills.SPRING);
		proSkils.add(JobDescription.ProfessionalSkills.JDBC);

		return proSkils;
	}

	private List<ProfessionalSkills> listProfessionalSkills2() {
		List<ProfessionalSkills> proSkils = new LinkedList<ProfessionalSkills>();
		proSkils.add(JobDescription.ProfessionalSkills.JAVA);
		proSkils.add(JobDescription.ProfessionalSkills.SPRING);
		proSkils.add(JobDescription.ProfessionalSkills.JDBC);

		return proSkils;
	}

	private List<ProfessionalSkills> listProfessionalSkills3() {
		List<ProfessionalSkills> proSkils = new LinkedList<ProfessionalSkills>();
		proSkils.add(JobDescription.ProfessionalSkills.JAVA);
		proSkils.add(JobDescription.ProfessionalSkills.SPRING);
		proSkils.add(JobDescription.ProfessionalSkills.JDBC);

		return proSkils;
	}

	private List<PersonalSkills> listPersonalSkillsCompany() {
		List<PersonalSkills> persSkils = new LinkedList<PersonalSkills>();
		persSkils.add(JobDescription.PersonalSkills.GOOD_COMMNUNICATOR);
		persSkils.add(JobDescription.PersonalSkills.TEAM_PLAYER);
		persSkils.add(JobDescription.PersonalSkills.GOOD_LISTENER);
		return persSkils;
	}

	private List<PersonalSkills> listPersonalSkills1() {
		List<PersonalSkills> persSkils = new LinkedList<PersonalSkills>();
		persSkils.add(JobDescription.PersonalSkills.GOOD_COMMNUNICATOR);
		persSkils.add(JobDescription.PersonalSkills.TEAM_PLAYER);
		//persSkils.add(JobDescription.PersonalSkills.GOOD_LISTENER);
		return persSkils;
	}

	private List<PersonalSkills> listPersonalSkills2() {
		List<PersonalSkills> persSkils = new LinkedList<PersonalSkills>();
		persSkils.add(JobDescription.PersonalSkills.GOOD_COMMNUNICATOR);
		//persSkils.add(JobDescription.PersonalSkills.TEAM_PLAYER);
		persSkils.add(JobDescription.PersonalSkills.GOOD_LISTENER);
		return persSkils;
	}

	private List<PersonalSkills> listPersonalSkills3() {
		List<PersonalSkills> persSkils = new LinkedList<PersonalSkills>();
		//persSkils.add(JobDescription.PersonalSkills.GOOD_COMMNUNICATOR);
		persSkils.add(JobDescription.PersonalSkills.TEAM_PLAYER);
		persSkils.add(JobDescription.PersonalSkills.GOOD_LISTENER);
		return persSkils;
	}

	private Candidate createObjectFromCandidate(String firstName, String lastName, String userName, String email,
			String phoneNumber, String password, String passwordConfirmed, String location, JobDescription jobProfile) {
		Candidate candidate = new Candidate();
		candidate.setFirstName(firstName);
		candidate.setLastName(lastName);
		candidate.setUserName(userName);
		candidate.setEmail(email);
		candidate.setPhoneNumber(phoneNumber);
		candidate.setPassword(password);
		candidate.setPasswordConfirmed(passwordConfirmed);
		candidate.setPreferedLocation(location);
		candidate.setJobDescription(jobProfile);

		return candidate;
	}
}
