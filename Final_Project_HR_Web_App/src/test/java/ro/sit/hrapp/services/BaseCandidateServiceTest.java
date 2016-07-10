package ro.sit.hrapp.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import ro.sit.hrapp.domain.Candidate;
import ro.sit.hrapp.domain.Company;
import ro.sit.hrapp.domain.JobDescription;
import ro.sit.hrapp.domain.JobDescription.CurrentJobTitle;
import ro.sit.hrapp.domain.JobDescription.Location;
import ro.sit.hrapp.domain.JobDescription.PersonalSkills;
import ro.sit.hrapp.domain.JobDescription.ProfessionalSkills;
import ro.sit.hrapp.domain.JobDescription.YearsOfExperience;
import ro.sit.hrapp.service.CandidateService;
import ro.sit.hrapp.service.validator.CandidateRegistrationValidator;

public abstract class BaseCandidateServiceTest {

	protected abstract CandidateService getCandidateService();

	protected abstract CandidateRegistrationValidator getValidator();

	CandidateService candidateService = new CandidateService();
	JobDescription jd = new JobDescription();

	@After
	public void tearDown() {
		Collection<Candidate> candidates = getCandidateService().listAll();

		for (Candidate candidate : candidates) {
			getCandidateService().deleteCandidate(candidate.getId());
		}
	}

	@Test
	public void test_empty_get_all() {
		Collection<Candidate> candidate = getCandidateService().listAll();
		Assert.assertTrue(candidate.isEmpty());
	}

	private List<ProfessionalSkills> listProfessionalSkills() {
		List<ProfessionalSkills> proSkils = new LinkedList<ProfessionalSkills>();
		proSkils.add(JobDescription.ProfessionalSkills.JAVA);
		proSkils.add(JobDescription.ProfessionalSkills.SPRING);
		proSkils.add(JobDescription.ProfessionalSkills.JDBC);

		return proSkils;
	}

	private List<PersonalSkills> listPersonalSkills() {
		List<PersonalSkills> persSkils = new LinkedList<PersonalSkills>();
		persSkils.add(JobDescription.PersonalSkills.GOOD_COMMNUNICATOR);
		persSkils.add(JobDescription.PersonalSkills.TEAM_PLAYER);
		persSkils.add(JobDescription.PersonalSkills.GOOD_LISTENER);
		return persSkils;
	}

	@Test
	public void addCandidate() {
		// given

		jd = (createJobDescriptionObject(JobDescription.CurrentJobTitle.BA,
				JobDescription.YearsOfExperience.ZERO_TO_ONE, JobDescription.Location.CLUJ_NAPOCA,
				listProfessionalSkills(), listPersonalSkills()));
		Candidate candidate = createObjectFromCandidate("biro", "stefan", "junior", "stefanbiro.emp@yahoo.com",
				"1234567890", "javajunior", "javajunior", "Cluj-Napoca", jd);
		Errors errors = new BeanPropertyBindingResult(candidate, "candidate");
		// when
		getValidator().validate(candidate, errors);
		getCandidateService().saveCandidate(candidate);

		Collection<Candidate> comp = getCandidateService().listAll();
		List<Candidate> candidateList = new ArrayList<>(comp);
		// then
		System.out.println(errors.toString());
		assertFalse(errors.hasErrors()); // validate fields from candidate

		assertEquals("biro", candidateList.get(0).getFirstName());
		assertTrue(candidateList.size() == 1);

	}

	@Test
	public void deleteCandidate() {
		// given
		jd = createJobDescriptionObject(JobDescription.CurrentJobTitle.BA, JobDescription.YearsOfExperience.ZERO_TO_ONE,
				JobDescription.Location.CLUJ_NAPOCA, listProfessionalSkills(), listPersonalSkills());
		Candidate candidateToDelete = createObjectFromCandidate("biro", "stefan", "junior", "stefanbiro.emp@yahoo.com",
				"1234567890", "javajunior", "javajunior", "Cluj-Napoca", jd);
		Errors errors = new BeanPropertyBindingResult(candidateToDelete, "candidate");

		// when
		getValidator().validate(candidateToDelete, errors); // validate fields
															// from candidate
		getCandidateService().saveCandidate(candidateToDelete);
		Collection<Candidate> candidate = getCandidateService().listAll();
		Long id = candidateToDelete.getId();
		getCandidateService().deleteCandidate(id);
		candidate = getCandidateService().listAll();
		// then
		System.out.println(errors.toString());
		assertFalse(errors.hasErrors());
		assertTrue(candidate.isEmpty());

	}

	@Test
	public void findCandidateById() {
		// given
		jd = createJobDescriptionObject(JobDescription.CurrentJobTitle.BA, JobDescription.YearsOfExperience.ZERO_TO_ONE,
				JobDescription.Location.CLUJ_NAPOCA, listProfessionalSkills(), listPersonalSkills());
		Candidate candidate = createObjectFromCandidate("biro", "stefan", "junior", "stefanbiro.emp@yahoo.com",
				"1234567890", "javajunior", "javajunior", "Cluj-Napoca", jd);
		Errors errors = new BeanPropertyBindingResult(candidate, "candidate");
		getCandidateService().saveCandidate(candidate);
		// when
		Long candidateId = candidate.getId();
		assertEquals(candidate, getCandidateService().getDAO().findByIdCandidate(candidateId));

	}

	@Test
	public void test_add_no_candidate__first_name() {
		// given
		jd = createJobDescriptionObject(JobDescription.CurrentJobTitle.BA, JobDescription.YearsOfExperience.ZERO_TO_ONE,
				JobDescription.Location.CLUJ_NAPOCA, listProfessionalSkills(), listPersonalSkills());
		Candidate candidate = createObjectFromCandidate("", "stefan", "junior", "stefanbiro.emp@yahoo.com",
				"1234567890", "javajunior", "javajunior", "Cluj-Napoca", jd);
		Errors errors = new BeanPropertyBindingResult(candidate, "candidate");
		// when
		getValidator().validate(candidate, errors); // validate fields from
													// company
		getCandidateService().saveCandidate(candidate);
		// then
		assertEquals("Please enter your first name.", errors.getFieldError().getDefaultMessage());

		getCandidateService().getDAO().deleteCandidate(candidate);

	}

	@Test
	public void test_add_no_username() {

		jd = createJobDescriptionObject(JobDescription.CurrentJobTitle.BA, JobDescription.YearsOfExperience.ZERO_TO_ONE,
				JobDescription.Location.CLUJ_NAPOCA, listProfessionalSkills(), listPersonalSkills());
		Candidate candidate = createObjectFromCandidate("biro", "stefan", "", "stefanbiro.emp@yahoo.com", "1234567890",
				"javajunior", "javajunior", "Cluj-Napoca", jd);
		Errors errors = new BeanPropertyBindingResult(candidate, "company");
		// when
		getValidator().validate(candidate, errors); // validate fields from
													// company
		getCandidateService().saveCandidate(candidate);
		// then
		assertEquals("Please enter your username.", errors.getFieldError().getDefaultMessage());

		getCandidateService().getDAO().deleteCandidate(candidate);
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

}
