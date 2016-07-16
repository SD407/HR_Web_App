package ro.sit.hrapp.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
	JobDescription jobList = new JobDescription();
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

	// primele teste sunt 1 la 1 adica un company jd cu un canidate jd
	/**
	 * this test is for 0 % match
	 */
	@Test
	public void testNoMatch() {

		// company

		List<ProfessionalSkills> proSkilsCompany = new LinkedList<>();
		proSkilsCompany.add(ProfessionalSkills.JAVA);
		proSkilsCompany.add(ProfessionalSkills.JDBC);
		proSkilsCompany.add(ProfessionalSkills.SPRING);

		List<PersonalSkills> persSkilsCompany = new LinkedList<>();
		persSkilsCompany.add(PersonalSkills.GOOD_COMMNUNICATOR);
		persSkilsCompany.add(PersonalSkills.TEAM_PLAYER);
		persSkilsCompany.add(PersonalSkills.GOOD_LISTENER);

		jobList = createJobDescriptionObject(JobDescription.CurrentJobTitle.BA,
				JobDescription.YearsOfExperience.THREE_TO_FIVE, JobDescription.Location.BUCURESTI, proSkilsCompany,
				persSkilsCompany);

		Company company = createObjectFromCompany("nokia", "nokya", "phones", "nokia.emp@yahoo.com", "1234567890",
				"phones", jobList, "BUCURESTI");

		// candidate
		List<Candidate> candidateList = new LinkedList<>();

		List<ProfessionalSkills> proSkilsCandidate = new LinkedList<>();
		proSkilsCandidate.add(ProfessionalSkills.HTML);
		proSkilsCandidate.add(ProfessionalSkills.QBASE);

		List<PersonalSkills> persSkilsCandidate = new LinkedList<>();
		persSkilsCandidate.add(PersonalSkills.AUTODIDACT);
		persSkilsCandidate.add(PersonalSkills.HELPFULL);
		persSkilsCandidate.add(PersonalSkills.TEAM_MOTIVATOR);

		jd1 = (createJobDescriptionObject(JobDescription.CurrentJobTitle.JAVA,
				JobDescription.YearsOfExperience.ZERO_TO_ONE, JobDescription.Location.CLUJ_NAPOCA, proSkilsCandidate,
				persSkilsCandidate));

		Candidate candidate1 = createObjectFromCandidate("biro", "stefan", "junior", "stefanbiro.emp@yahoo.com",
				"1234567890", "javajunior", "javajunior", "CLUJ_NAPOCA", jd1);

		// saving candidate
		getCandidateService().saveCandidate(candidate1);

		// generating candidateId
		long id1 = candidate1.getId();

		// create a candidate list from dao

		candidateList.add(getCandidateService().getDAO().findByIdCandidate(id1));

		List<Long> match = new LinkedList<>();

		// aici apelam metoda din matchservice
		match = matchService.searchMatchForCompany(company, candidateList);

		// no candidate in list
		assertEquals(0, match.size(), 0.0);

	}

	/**
	 * this test is for similar condition in both sides 100 % match
	 */
	@Test
	public void test100() {

		// company

		List<ProfessionalSkills> proSkilsCompany = new LinkedList<>();
		proSkilsCompany.add(ProfessionalSkills.JAVA);
		proSkilsCompany.add(ProfessionalSkills.JDBC);
		proSkilsCompany.add(ProfessionalSkills.SPRING);
		proSkilsCompany.add(ProfessionalSkills.QBASE);
		proSkilsCompany.add(ProfessionalSkills.TEST);

		List<PersonalSkills> persSkilsCompany = new LinkedList<>();
		persSkilsCompany.add(PersonalSkills.GOOD_COMMNUNICATOR);
		persSkilsCompany.add(PersonalSkills.TEAM_PLAYER);
		persSkilsCompany.add(PersonalSkills.GOOD_LISTENER);
		persSkilsCompany.add(PersonalSkills.AUTODIDACT);

		jobList = createJobDescriptionObject(JobDescription.CurrentJobTitle.JAVA,
				JobDescription.YearsOfExperience.ZERO_TO_ONE, JobDescription.Location.CLUJ_NAPOCA, proSkilsCompany,
				persSkilsCompany);
		Company company = createObjectFromCompany("nokia", "nokya", "phones", "nokia.emp@yahoo.com", "1234567890",
				"phones", jobList, "BUCURESTI");

		// candidate

		List<Candidate> candidateList = new LinkedList<>();

		List<ProfessionalSkills> proSkilsCandidate = new LinkedList<>();
		proSkilsCandidate.add(ProfessionalSkills.JAVA);
		proSkilsCandidate.add(ProfessionalSkills.JDBC);
		proSkilsCandidate.add(ProfessionalSkills.SPRING);
		proSkilsCandidate.add(ProfessionalSkills.QBASE);
		proSkilsCandidate.add(ProfessionalSkills.TEST);

		List<PersonalSkills> persSkilsCandidate = new LinkedList<>();
		persSkilsCandidate.add(PersonalSkills.GOOD_COMMNUNICATOR);
		persSkilsCandidate.add(PersonalSkills.TEAM_PLAYER);
		persSkilsCandidate.add(PersonalSkills.GOOD_LISTENER);
		persSkilsCandidate.add(PersonalSkills.AUTODIDACT);

		jd1 = (createJobDescriptionObject(JobDescription.CurrentJobTitle.JAVA,
				JobDescription.YearsOfExperience.ZERO_TO_ONE, JobDescription.Location.CLUJ_NAPOCA, proSkilsCandidate,
				persSkilsCandidate));

		Candidate candidate1 = createObjectFromCandidate("biro", "stefan", "junior", "stefanbiro.emp@yahoo.com",
				"1234567890", "javajunior", "javajunior", "CLUJ_NAPOCA", jd1);

		// saving candidate
		getCandidateService().saveCandidate(candidate1);

		// generating candidateId
		long id1 = candidate1.getId();

		// create a candidate list from dao

		candidateList.add(getCandidateService().getDAO().findByIdCandidate(id1));// 1

		List<Long> match = new LinkedList<>();

		match = matchService.searchMatchForCompany(company, candidateList);

		Candidate c1 = new Candidate();
		for (long l : match) {
			c1 = getCandidateService().getDAO().findByIdCandidate(l);
			float m = Math.round(c1.getMatch());
			assertEquals("biro", c1.getFirstName());
			assertEquals(100, m, 0.0);
		}
		/**
		 * OBS! datorita algoritmului procentul maxim se modifica usor in
		 * functie de nr se skiluri cerute asa am reusit sa gandesc algorimtul !
		 * adica procetul de incremetare va diferii de exmplu daca 20 / la 5
		 * skiluri sau la 4 ideea e ca diferenta nu e mare si da o informatie
		 * mai mult calitativa : adica ca procentul e 100 sau 98 tot un
		 * procentaj mare este si va diferentia oricum candidat de candidat nu
		 * am putut gasi un algoritm mai bun - daca ai idei sunt binevenite!
		 */

	}

	// aceste teste sunt combinari adica nici 100 % nici 0 %
	/**
	 * this test is for no personalSkills and no profesional skills only
	 * location,jobTitles,expericence - 60%
	 */
	@Test
	public void test60() {

		// company
		List<ProfessionalSkills> proSkilsCompany = new LinkedList<>();
		proSkilsCompany.add(ProfessionalSkills.JAVA);
		proSkilsCompany.add(ProfessionalSkills.JDBC);
		proSkilsCompany.add(ProfessionalSkills.SPRING);

		List<PersonalSkills> persSkilsCompany = new LinkedList<>();
		persSkilsCompany.add(PersonalSkills.GOOD_COMMNUNICATOR);
		persSkilsCompany.add(PersonalSkills.TEAM_PLAYER);
		persSkilsCompany.add(PersonalSkills.GOOD_LISTENER);

		jobList = createJobDescriptionObject(JobDescription.CurrentJobTitle.JAVA,
				JobDescription.YearsOfExperience.ZERO_TO_ONE, JobDescription.Location.CLUJ_NAPOCA, proSkilsCompany,
				persSkilsCompany);
		Company company = createObjectFromCompany("nokia", "nokya", "phones", "nokia.emp@yahoo.com", "1234567890",
				"phones", jobList, "BUCURESTI");

		// candidate
		List<Candidate> candidateList = new LinkedList<>();

		List<ProfessionalSkills> proSkilsCandidate = new LinkedList<>();
		proSkilsCandidate.add(ProfessionalSkills.HTML);

		List<PersonalSkills> persSkilsCandidate = new LinkedList<>();
		persSkilsCandidate.add(PersonalSkills.AUTODIDACT);
		persSkilsCandidate.add(PersonalSkills.HELPFULL);
		persSkilsCandidate.add(PersonalSkills.TEAM_MOTIVATOR);

		jd1 = (createJobDescriptionObject(JobDescription.CurrentJobTitle.JAVA,
				JobDescription.YearsOfExperience.ZERO_TO_ONE, JobDescription.Location.CLUJ_NAPOCA, proSkilsCandidate,
				persSkilsCandidate));

		Candidate candidate1 = createObjectFromCandidate("biro", "stefan", "junior", "stefanbiro.emp@yahoo.com",
				"1234567890", "javajunior", "javajunior", "CLUJ_NAPOCA", jd1);

		// saving candidate
		getCandidateService().saveCandidate(candidate1);

		// generating candidateId
		long id1 = candidate1.getId();

		// create a candidate list from dao

		candidateList.add(getCandidateService().getDAO().findByIdCandidate(id1));// 1

		List<Long> match = new LinkedList<>();

		match = matchService.searchMatchForCompany(company, candidateList);
		Candidate c1 = new Candidate();
		for (long l : match) {
			c1 = getCandidateService().getDAO().findByIdCandidate(l);
			float m = Math.round(c1.getMatch());
			assertEquals("biro", c1.getFirstName());
			assertEquals(60, m, 0.0);
		}

	}

	/**
	 * this test is for jobTitle , if this not match other thing dont matter
	 * aici nu are rost sa mai caute altceva daca jobul nu corespunde -
	 */
	@Test
	public void testNoJobTitles() {

		// company
		List<ProfessionalSkills> proSkilsCompany = new LinkedList<>();
		proSkilsCompany.add(ProfessionalSkills.JAVA);
		proSkilsCompany.add(ProfessionalSkills.JDBC);
		proSkilsCompany.add(ProfessionalSkills.SPRING);

		List<PersonalSkills> persSkilsCompany = new LinkedList<>();
		persSkilsCompany.add(PersonalSkills.GOOD_COMMNUNICATOR);
		persSkilsCompany.add(PersonalSkills.TEAM_PLAYER);
		persSkilsCompany.add(PersonalSkills.GOOD_LISTENER);

		List<Candidate> candidateList = new LinkedList<>();

		// candidate
		List<ProfessionalSkills> proSkilsCandidate = new LinkedList<>();
		proSkilsCandidate.add(ProfessionalSkills.JAVA);
		proSkilsCandidate.add(ProfessionalSkills.JDBC);
		proSkilsCandidate.add(ProfessionalSkills.SPRING);

		List<PersonalSkills> persSkilsCandidate = new LinkedList<>();
		persSkilsCandidate.add(PersonalSkills.GOOD_COMMNUNICATOR);
		persSkilsCandidate.add(PersonalSkills.TEAM_PLAYER);
		persSkilsCandidate.add(PersonalSkills.GOOD_LISTENER);

		jobList = createJobDescriptionObject(JobDescription.CurrentJobTitle.JAVA,
				JobDescription.YearsOfExperience.ONE_TO_THREE, JobDescription.Location.BUCURESTI, proSkilsCompany,
				persSkilsCompany);
		Company company = createObjectFromCompany("nokia", "nokya", "phones", "nokia.emp@yahoo.com", "1234567890",
				"phones", jobList, "BUCURESTI");

		jd1 = (createJobDescriptionObject(JobDescription.CurrentJobTitle.BA,
				JobDescription.YearsOfExperience.ZERO_TO_ONE, JobDescription.Location.CLUJ_NAPOCA, proSkilsCandidate,
				persSkilsCandidate));

		Candidate candidate1 = createObjectFromCandidate("biro", "stefan", "junior", "stefanbiro.emp@yahoo.com",
				"1234567890", "javajunior", "javajunior", "CLUJ_NAPOCA", jd1);

		// saving candidate
		getCandidateService().saveCandidate(candidate1);

		// generating candidateId
		long id1 = candidate1.getId();

		// create a candidate list from dao

		candidateList.add(getCandidateService().getDAO().findByIdCandidate(id1));// 1

		List<Long> match = new LinkedList<>();

		match = matchService.searchMatchForCompany(company, candidateList);

		assertEquals(0, match.size(), 0.0); // lista goala

	}

	// testari separate fara un crietriu aici toate procentele ar trebui sa dea
	// aporx 80%

	// no location 80 % match
	@Test
	public void testNoLocation() {

		// company

		List<ProfessionalSkills> proSkilsCompany = new LinkedList<>();
		proSkilsCompany.add(ProfessionalSkills.JAVA);
		proSkilsCompany.add(ProfessionalSkills.JDBC);
		proSkilsCompany.add(ProfessionalSkills.SPRING);
		proSkilsCompany.add(ProfessionalSkills.HTML);

		List<PersonalSkills> persSkilsCompany = new LinkedList<>();
		persSkilsCompany.add(PersonalSkills.GOOD_COMMNUNICATOR);
		persSkilsCompany.add(PersonalSkills.TEAM_PLAYER);
		persSkilsCompany.add(PersonalSkills.GOOD_LISTENER);

		jobList = (createJobDescriptionObject(JobDescription.CurrentJobTitle.JAVA,
				JobDescription.YearsOfExperience.ZERO_TO_ONE, JobDescription.Location.BUCURESTI, proSkilsCompany,
				persSkilsCompany));
		Company company = createObjectFromCompany("nokia", "nokya", "phones", "nokia.emp@yahoo.com", "1234567890",
				"phones", jobList, "BUCURESTI");

		// candidate

		List<Candidate> candidateList = new LinkedList<>();

		List<ProfessionalSkills> proSkilsCandidate = new LinkedList<>();
		proSkilsCandidate.add(ProfessionalSkills.JAVA);
		proSkilsCandidate.add(ProfessionalSkills.JDBC);
		proSkilsCandidate.add(ProfessionalSkills.SPRING);
		proSkilsCandidate.add(ProfessionalSkills.HTML);

		List<PersonalSkills> persSkilsCandidate = new LinkedList<>();
		persSkilsCandidate.add(PersonalSkills.GOOD_COMMNUNICATOR);
		persSkilsCandidate.add(PersonalSkills.TEAM_PLAYER);
		persSkilsCandidate.add(PersonalSkills.GOOD_LISTENER);

		jd1 = (createJobDescriptionObject(JobDescription.CurrentJobTitle.JAVA,
				JobDescription.YearsOfExperience.ZERO_TO_ONE, JobDescription.Location.CLUJ_NAPOCA, proSkilsCandidate,
				persSkilsCandidate));

		Candidate candidate1 = createObjectFromCandidate("biro", "stefan", "junior", "stefanbiro.emp@yahoo.com",
				"1234567890", "javajunior", "javajunior", "CLUJ_NAPOCA", jd1);

		// saving candidate
		getCandidateService().saveCandidate(candidate1);

		// generating candidateId
		long id1 = candidate1.getId();

		// create a candidate list from dao

		candidateList.add(getCandidateService().getDAO().findByIdCandidate(id1));// 1

		List<Long> match = new LinkedList<>();

		match = matchService.searchMatchForCompany(company, candidateList);

		Candidate c1 = new Candidate();
		for (long l : match) {
			c1 = getCandidateService().getDAO().findByIdCandidate(l);
			float m = Math.round(c1.getMatch());
			assertEquals("biro", c1.getFirstName());
			assertEquals(78, m, 0.0);
		}
		// aceasi poveste 78 depinde de nr de skiluri comparate

	}

	// no experience 80 % match
	@Test
	public void testNoExperience() {

		// company
		List<ProfessionalSkills> proSkilsCompany = new LinkedList<>();
		proSkilsCompany.add(ProfessionalSkills.JAVA);
		proSkilsCompany.add(ProfessionalSkills.JDBC);
		proSkilsCompany.add(ProfessionalSkills.SPRING);
		proSkilsCompany.add(ProfessionalSkills.TEST);

		List<PersonalSkills> persSkilsCompany = new LinkedList<>();
		persSkilsCompany.add(PersonalSkills.GOOD_COMMNUNICATOR);
		persSkilsCompany.add(PersonalSkills.TEAM_PLAYER);
		persSkilsCompany.add(PersonalSkills.GOOD_LISTENER);

		jobList = createJobDescriptionObject(JobDescription.CurrentJobTitle.JAVA,
				JobDescription.YearsOfExperience.FIVE_TO_EIGHT, JobDescription.Location.CLUJ_NAPOCA, proSkilsCompany,
				persSkilsCompany);
		Company company = createObjectFromCompany("nokia", "nokya", "phones", "nokia.emp@yahoo.com", "1234567890",
				"phones", jobList, "BUCURESTI");

		// candidate
		List<Candidate> candidateList = new LinkedList<>();

		List<ProfessionalSkills> proSkilsCandidate = new LinkedList<>();
		proSkilsCandidate.add(ProfessionalSkills.JAVA);
		proSkilsCandidate.add(ProfessionalSkills.JDBC);
		proSkilsCandidate.add(ProfessionalSkills.SPRING);
		proSkilsCandidate.add(ProfessionalSkills.TEST);

		List<PersonalSkills> persSkilsCandidate = new LinkedList<>();
		persSkilsCandidate.add(PersonalSkills.GOOD_COMMNUNICATOR);
		persSkilsCandidate.add(PersonalSkills.TEAM_PLAYER);
		persSkilsCandidate.add(PersonalSkills.GOOD_LISTENER);

		jd1 = (createJobDescriptionObject(JobDescription.CurrentJobTitle.JAVA,
				JobDescription.YearsOfExperience.ZERO_TO_ONE, JobDescription.Location.CLUJ_NAPOCA, proSkilsCandidate,
				persSkilsCandidate));

		Candidate candidate1 = createObjectFromCandidate("biro", "stefan", "junior", "stefanbiro.emp@yahoo.com",
				"1234567890", "javajunior", "javajunior", "CLUJ_NAPOCA", jd1);

		// saving candidate
		getCandidateService().saveCandidate(candidate1);

		// generating candidateId
		long id1 = candidate1.getId();

		// create a candidate list from dao

		candidateList.add(getCandidateService().getDAO().findByIdCandidate(id1));// 1

		List<Long> match = new LinkedList<>();

		match = matchService.searchMatchForCompany(company, candidateList);

		Candidate c1 = new Candidate();
		for (long l : match) {
			c1 = getCandidateService().getDAO().findByIdCandidate(l);
			float m = Math.round(c1.getMatch());
			assertEquals("biro", c1.getFirstName());
			assertEquals(78, m, 0.0);
		}

	}

	// no proSkills 80 % match
	@Test
	public void testNoProSkills() {

		// company
		List<ProfessionalSkills> proSkilsCompany = new LinkedList<>();
		proSkilsCompany.add(ProfessionalSkills.QBASE);
		proSkilsCompany.add(ProfessionalSkills.TEST);
		proSkilsCompany.add(ProfessionalSkills.HTML);

		List<PersonalSkills> persSkilsCompany = new LinkedList<>();
		persSkilsCompany.add(PersonalSkills.GOOD_COMMNUNICATOR);
		persSkilsCompany.add(PersonalSkills.TEAM_PLAYER);
		persSkilsCompany.add(PersonalSkills.GOOD_LISTENER);

		jobList = createJobDescriptionObject(JobDescription.CurrentJobTitle.JAVA,
				JobDescription.YearsOfExperience.ZERO_TO_ONE, JobDescription.Location.CLUJ_NAPOCA, proSkilsCompany,
				persSkilsCompany);
		Company company = createObjectFromCompany("nokia", "nokya", "phones", "nokia.emp@yahoo.com", "1234567890",
				"phones", jobList, "BUCURESTI");

		// candidate

		List<Candidate> candidateList = new LinkedList<>();

		List<ProfessionalSkills> proSkilsCandidate = new LinkedList<>();
		proSkilsCandidate.add(ProfessionalSkills.JAVA);
		proSkilsCandidate.add(ProfessionalSkills.JDBC);
		proSkilsCandidate.add(ProfessionalSkills.SPRING);

		List<PersonalSkills> persSkilsCandidate = new LinkedList<>();
		persSkilsCandidate.add(PersonalSkills.GOOD_COMMNUNICATOR);
		persSkilsCandidate.add(PersonalSkills.TEAM_PLAYER);
		persSkilsCandidate.add(PersonalSkills.GOOD_LISTENER);

		jd1 = (createJobDescriptionObject(JobDescription.CurrentJobTitle.JAVA,
				JobDescription.YearsOfExperience.ZERO_TO_ONE, JobDescription.Location.CLUJ_NAPOCA, proSkilsCandidate,
				persSkilsCandidate));

		Candidate candidate1 = createObjectFromCandidate("biro", "stefan", "junior", "stefanbiro.emp@yahoo.com",
				"1234567890", "javajunior", "javajunior", "CLUJ_NAPOCA", jd1);

		// saving candidate
		getCandidateService().saveCandidate(candidate1);

		// generating candidateId
		long id1 = candidate1.getId();

		// create a candidate list from dao

		candidateList.add(getCandidateService().getDAO().findByIdCandidate(id1));

		List<Long> match = new LinkedList<>();

		match = matchService.searchMatchForCompany(company, candidateList);

		Candidate c1 = new Candidate();
		for (long l : match) {
			c1 = getCandidateService().getDAO().findByIdCandidate(l);
			float m = Math.round(c1.getMatch());
			assertEquals("biro", c1.getFirstName());
			assertEquals(78, m, 0.0);
		}

	}

	// no persSkills 80 % match
	@Test
	public void testNoPersSkills() {

		// company
		List<ProfessionalSkills> proSkilsCompany = new LinkedList<>();
		proSkilsCompany.add(ProfessionalSkills.JAVA);
		proSkilsCompany.add(ProfessionalSkills.JDBC);
		proSkilsCompany.add(ProfessionalSkills.SPRING);

		List<PersonalSkills> persSkilsCompany = new LinkedList<>();
		persSkilsCompany.add(PersonalSkills.GOOD_COMMNUNICATOR);
		persSkilsCompany.add(PersonalSkills.TEAM_PLAYER);
		persSkilsCompany.add(PersonalSkills.GOOD_LISTENER);
		persSkilsCompany.add(PersonalSkills.HELPFULL);

		jobList = createJobDescriptionObject(JobDescription.CurrentJobTitle.JAVA,
				JobDescription.YearsOfExperience.ZERO_TO_ONE, JobDescription.Location.CLUJ_NAPOCA, proSkilsCompany,
				persSkilsCompany);
		Company company = createObjectFromCompany("nokia", "nokya", "phones", "nokia.emp@yahoo.com", "1234567890",
				"phones", jobList, "BUCURESTI");

		// candidate
		List<Candidate> candidateList = new LinkedList<>();

		List<ProfessionalSkills> proSkilsCandidate = new LinkedList<>();
		proSkilsCandidate.add(ProfessionalSkills.JAVA);
		proSkilsCandidate.add(ProfessionalSkills.JDBC);
		proSkilsCandidate.add(ProfessionalSkills.SPRING);

		List<PersonalSkills> persSkilsCandidate = new LinkedList<>();
		persSkilsCandidate.add(PersonalSkills.AUTODIDACT);
		persSkilsCandidate.add(PersonalSkills.TEAM_MOTIVATOR);

		jd1 = (createJobDescriptionObject(JobDescription.CurrentJobTitle.JAVA,
				JobDescription.YearsOfExperience.ZERO_TO_ONE, JobDescription.Location.CLUJ_NAPOCA, proSkilsCandidate,
				persSkilsCandidate));

		Candidate candidate1 = createObjectFromCandidate("biro", "stefan", "junior", "stefanbiro.emp@yahoo.com",
				"1234567890", "javajunior", "javajunior", "CLUJ_NAPOCA", jd1);

		// saving candidate
		getCandidateService().saveCandidate(candidate1);

		// generating candidateId
		long id1 = candidate1.getId();

		// create a candidate list from dao

		candidateList.add(getCandidateService().getDAO().findByIdCandidate(id1));// 1

		List<Long> match = new LinkedList<>();

		match = matchService.searchMatchForCompany(company, candidateList);

		Candidate c1 = new Candidate();
		for (long l : match) {
			c1 = getCandidateService().getDAO().findByIdCandidate(l);
			float m = Math.round(c1.getMatch());
			assertEquals("biro", c1.getFirstName());
			assertEquals(78, m, 0.0);
		}

	}

	// aici testez ca angajatorul are mai multe skiluri si candidatul mai putine
	//  aici se copmara si trebuie sa de-a porcent mai mic de potrivire
	// combination match
	@Test
	public void testDiffrentProSkills() {

		// company
		List<ProfessionalSkills> proSkilsCompany = new LinkedList<>();
		proSkilsCompany.add(ProfessionalSkills.JAVA);
		proSkilsCompany.add(ProfessionalSkills.JDBC);
		proSkilsCompany.add(ProfessionalSkills.SPRING);
		proSkilsCompany.add(ProfessionalSkills.TEST);
		proSkilsCompany.add(ProfessionalSkills.HTML);

		List<PersonalSkills> persSkilsCompany = new LinkedList<>();
		persSkilsCompany.add(PersonalSkills.GOOD_COMMNUNICATOR);
		persSkilsCompany.add(PersonalSkills.TEAM_PLAYER);
		persSkilsCompany.add(PersonalSkills.GOOD_LISTENER);
		persSkilsCompany.add(PersonalSkills.AUTODIDACT);
		persSkilsCompany.add(PersonalSkills.TEAM_MOTIVATOR);

		jobList = (createJobDescriptionObject(JobDescription.CurrentJobTitle.JAVA,
				JobDescription.YearsOfExperience.ZERO_TO_ONE, JobDescription.Location.CLUJ_NAPOCA, proSkilsCompany,
				persSkilsCompany));
		Company company = createObjectFromCompany("nokia", "nokya", "phones", "nokia.emp@yahoo.com", "1234567890",
				"phones", jobList, "BUCURESTI");

		// candidate
		List<Candidate> candidateList = new LinkedList<>();

		List<ProfessionalSkills> proSkilsCandidate = new LinkedList<>();
		proSkilsCandidate.add(ProfessionalSkills.JAVA);
		proSkilsCandidate.add(ProfessionalSkills.JDBC);
		proSkilsCandidate.add(ProfessionalSkills.SPRING);

		List<PersonalSkills> persSkilsCandidate = new LinkedList<>();
		persSkilsCandidate.add(PersonalSkills.GOOD_COMMNUNICATOR);
		persSkilsCandidate.add(PersonalSkills.TEAM_PLAYER);
		persSkilsCandidate.add(PersonalSkills.GOOD_LISTENER);
		persSkilsCandidate.add(PersonalSkills.AUTODIDACT);
		persSkilsCandidate.add(PersonalSkills.TEAM_MOTIVATOR);

		jd1 = (createJobDescriptionObject(JobDescription.CurrentJobTitle.JAVA,
				JobDescription.YearsOfExperience.ZERO_TO_ONE, JobDescription.Location.CLUJ_NAPOCA, proSkilsCandidate,
				persSkilsCandidate));

		Candidate candidate1 = createObjectFromCandidate("biro", "stefan", "junior", "stefanbiro.emp@yahoo.com",
				"1234567890", "javajunior", "javajunior", "CLUJ_NAPOCA", jd1);

		// saving candidate
		getCandidateService().saveCandidate(candidate1);

		// generating candidateId
		long id1 = candidate1.getId();

		// create a candidate list from dao

		candidateList.add(getCandidateService().getDAO().findByIdCandidate(id1));// 1

		List<Long> match = new LinkedList<>();

		match = matchService.searchMatchForCompany(company, candidateList);

		Candidate c1 = new Candidate();
		for (long l : match) {
			c1 = getCandidateService().getDAO().findByIdCandidate(l);
			float m = Math.round(c1.getMatch());
			assertEquals("biro", c1.getFirstName());
			assertNotEquals(100, m, 0.0);
		}

	}

	// aici am mai multi candidati pe acelasi jd company
	/**
	 * test multiple candidates - only one match
	 */
	@Test
	public void testMultipleCandidates1() {
		// given

		// company
		jobList = createJobDescriptionObject(JobDescription.CurrentJobTitle.PM,
				JobDescription.YearsOfExperience.ZERO_TO_ONE, JobDescription.Location.BUCURESTI,
				listProfessionalSkillsCompany(), listPersonalSkillsCompany());
		Company company = createObjectFromCompany("nokia", "nokya", "phones", "nokia.emp@yahoo.com", "1234567890",
				"phones", jobList, "Cluj-Napoca");

		// candidate 1
		jd1 = (createJobDescriptionObject(JobDescription.CurrentJobTitle.BA,
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
		jd3 = (createJobDescriptionObject(JobDescription.CurrentJobTitle.PM,
				JobDescription.YearsOfExperience.ZERO_TO_ONE, JobDescription.Location.BUCURESTI,
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

		Candidate c1 = new Candidate();
		for (long l : match) {
			c1 = getCandidateService().getDAO().findByIdCandidate(l);
			System.out.println(c1.getFirstName());
			System.out.println(c1.getMatch());
		}
		assertEquals("bocean", c1.getFirstName());
     // numai candidatul 3 se potriveste
	}

	
	// aici toti candidatii se potrivesc intr-o anumita masura si se cauta candidatul cu potrivirea
	// ce mai mare
	/**
	 * test multiple candidates - highest match percent is searched and
	 * candidate
	 */
	@Test
	public void searchCandidateWithHighestMatch() {

		// company
		jobList = createJobDescriptionObject(JobDescription.CurrentJobTitle.PM,
				JobDescription.YearsOfExperience.ONE_TO_THREE, JobDescription.Location.BUCURESTI,
				listProfessionalSkillsCompany(), listPersonalSkillsCompany());
		Company company = createObjectFromCompany("nokia", "nokya", "phones", "nokia.emp@yahoo.com", "1234567890",
				"phones", jobList, "Cluj-Napoca");

		// candidate 1
		jd1 = (createJobDescriptionObject(JobDescription.CurrentJobTitle.PM,
				JobDescription.YearsOfExperience.ONE_TO_THREE, JobDescription.Location.CLUJ_NAPOCA,
				listProfessionalSkills1(), listPersonalSkills1()));
		Candidate candidate1 = createObjectFromCandidate("dragan", "sorin", "junior", "stefanbiro.emp@yahoo.com",
				"1234567890", "javajunior", "javajunior", "Cluj-Napoca", jd1);

		// candidate 2
		jd2 = (createJobDescriptionObject(JobDescription.CurrentJobTitle.PM,
				JobDescription.YearsOfExperience.ONE_TO_THREE, JobDescription.Location.CLUJ_NAPOCA,
				listProfessionalSkills2(), listPersonalSkills2()));
		Candidate candidate2 = createObjectFromCandidate("bara", "crina", "junior", "stefanbiro.emp@yahoo.com",
				"1234567890", "javajunior", "javajunior", "Cluj-Napoca", jd2);

		// candidate 3
		jd3 = (createJobDescriptionObject(JobDescription.CurrentJobTitle.PM,
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

		Candidate c1 = new Candidate();
		List<Float> percents = new LinkedList<>();
		for (long l : match) {
			c1 = getCandidateService().getDAO().findByIdCandidate(l);
			percents.add(c1.getMatch());

		}
		// match for all candidates
		assertEquals(78f, percents.get(0), 0.0);

		assertEquals(70f, percents.get(1), 0.0);

		assertEquals(50f, percents.get(2), 0.0);

		// compute the highest match
		float maximum = matchService.highestMatch(percents);

		assertEquals(78, maximum, 0.0);

		Candidate bestCandidate = new Candidate();
		bestCandidate = matchService.CandidateHighestMatch(maximum, listCandidates);
		assertEquals("dragan", bestCandidate.getFirstName());

	}

	// this test put toghether candidate and matching percent -> list
	@Test
	public void listAllCandidates() {
		// company
		jobList = createJobDescriptionObject(JobDescription.CurrentJobTitle.PM,
				JobDescription.YearsOfExperience.ONE_TO_THREE, JobDescription.Location.BUCURESTI,
				listProfessionalSkillsCompany(), listPersonalSkillsCompany());
		Company company = createObjectFromCompany("nokia", "nokya", "phones", "nokia.emp@yahoo.com", "1234567890",
				"phones", jobList, "Cluj-Napoca");

		// candidate 1
		jd1 = (createJobDescriptionObject(JobDescription.CurrentJobTitle.PM,
				JobDescription.YearsOfExperience.ONE_TO_THREE, JobDescription.Location.CLUJ_NAPOCA,
				listProfessionalSkills1(), listPersonalSkills1()));
		Candidate candidate1 = createObjectFromCandidate("dragan", "sorin", "junior", "stefanbiro.emp@yahoo.com",
				"1234567890", "javajunior", "javajunior", "Cluj-Napoca", jd1);

		// candidate 2
		jd2 = (createJobDescriptionObject(JobDescription.CurrentJobTitle.PM,
				JobDescription.YearsOfExperience.ONE_TO_THREE, JobDescription.Location.CLUJ_NAPOCA,
				listProfessionalSkills2(), listPersonalSkills2()));
		Candidate candidate2 = createObjectFromCandidate("bara", "crina", "junior", "stefanbiro.emp@yahoo.com",
				"1234567890", "javajunior", "javajunior", "Cluj-Napoca", jd2);

		// candidate 3
		jd3 = (createJobDescriptionObject(JobDescription.CurrentJobTitle.PM,
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

		Candidate c1 = new Candidate();
		MatchService ms = new MatchService();
		List<Float> percents = new LinkedList<>();
		for (long l : match) {
			c1 = getCandidateService().getDAO().findByIdCandidate(l);
			percents.add(c1.getMatch());

		}
		float maximum = ms.highestMatch(percents);
		c1 = ms.CandidateHighestMatch(maximum, listCandidates);
		Map<Float, Candidate> listAllCandidate = new HashMap<>();
		listAllCandidate = ms.listMatchPercent(percents, listCandidates);

		assertEquals(3, listAllCandidate.size()); // lista de 3

		assertEquals("bocean", listAllCandidate.get(50f).getFirstName());
		assertEquals("bara", listAllCandidate.get(70f).getFirstName());
		assertEquals("dragan", listAllCandidate.get(78f).getFirstName());

	}

	
	// aici se cauta o lista cu candidati peste un anumit procent de potrivire
	/**
	 * this test is for list for a given percent the candidates with highest
	 * percent
	 */
	@Test
	public void listAllCandidatesWithMatch() {
		// company
		jobList = createJobDescriptionObject(JobDescription.CurrentJobTitle.PM,
				JobDescription.YearsOfExperience.ONE_TO_THREE, JobDescription.Location.BUCURESTI,
				listProfessionalSkillsCompany(), listPersonalSkillsCompany());
		Company company = createObjectFromCompany("nokia", "nokya", "phones", "nokia.emp@yahoo.com", "1234567890",
				"phones", jobList, "Cluj-Napoca");

		// candidate 1
		jd1 = (createJobDescriptionObject(JobDescription.CurrentJobTitle.PM,
				JobDescription.YearsOfExperience.ONE_TO_THREE, JobDescription.Location.CLUJ_NAPOCA,
				listProfessionalSkills1(), listPersonalSkills1()));
		Candidate candidate1 = createObjectFromCandidate("biro", "stefan", "junior", "stefanbiro.emp@yahoo.com",
				"1234567890", "javajunior", "javajunior", "Cluj-Napoca", jd1);

		// candidate 2
		jd2 = (createJobDescriptionObject(JobDescription.CurrentJobTitle.PM,
				JobDescription.YearsOfExperience.ONE_TO_THREE, JobDescription.Location.CLUJ_NAPOCA,
				listProfessionalSkills2(), listPersonalSkills2()));
		Candidate candidate2 = createObjectFromCandidate("bara", "crina", "junior", "stefanbiro.emp@yahoo.com",
				"1234567890", "javajunior", "javajunior", "Cluj-Napoca", jd2);

		// candidate 3
		jd3 = (createJobDescriptionObject(JobDescription.CurrentJobTitle.PM,
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

		Candidate c1 = new Candidate();
		List<Float> percents = new LinkedList<>();
		for (long l : match) {
			c1 = getCandidateService().getDAO().findByIdCandidate(l);
			percents.add(c1.getMatch());
		}

		Map<Float, Candidate> listAllCandidate = new TreeMap<>();
		listAllCandidate = matchService.listMatchPercent(percents, listCandidates);

		// given match percent
		float matchPercent = 51f;
		Map<Integer, Candidate> candidateList = matchService.listCandidateWithMatch(matchPercent, listAllCandidate);

		assertEquals(2, candidateList.size());
		// assertEquals("bocean", candidateList.get(0).getFirstName()); //50 %
		assertEquals("biro", candidateList.get(1).getFirstName());// 78  %
		assertEquals("bara", candidateList.get(2).getFirstName());// 70  %
		
		// daca modifici 51 la 71 atucni numai biro va fi in lista ca ceilalti sunt 50 si 70
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
			String phone, String confirmedPassword, JobDescription jobList, String jobLocation) {

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
		proSkils.add(JobDescription.ProfessionalSkills.QBASE);
		proSkils.add(JobDescription.ProfessionalSkills.TEST);
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
		// proSkils.add(JobDescription.ProfessionalSkills.QBASE);
		// proSkils.add(JobDescription.ProfessionalSkills.TEST);

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
		persSkils.add(JobDescription.PersonalSkills.GOOD_LISTENER);
		return persSkils;
	}

	private List<PersonalSkills> listPersonalSkills2() {
		List<PersonalSkills> persSkils = new LinkedList<PersonalSkills>();
		persSkils.add(JobDescription.PersonalSkills.GOOD_COMMNUNICATOR);
		persSkils.add(JobDescription.PersonalSkills.TEAM_PLAYER);
		persSkils.add(JobDescription.PersonalSkills.GOOD_LISTENER);
		return persSkils;
	}

	private List<PersonalSkills> listPersonalSkills3() {
		List<PersonalSkills> persSkils = new LinkedList<PersonalSkills>();
		persSkils.add(JobDescription.PersonalSkills.GOOD_COMMNUNICATOR);
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
