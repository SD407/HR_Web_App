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
import ro.sit.hrapp.domain.JobDescription;
import ro.sit.hrapp.service.CandidateService;
import ro.sit.hrapp.service.validator.CandidateRegistrationValidator;

public abstract class BaseCandidateServiceTest {

	protected abstract CandidateService getCandidateService();

	protected abstract CandidateRegistrationValidator getValidator();

	CandidateService candidateService = new CandidateService();
	JobDescription jd;
	List<JobDescription> jobList = new LinkedList<>();

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

	@Test
	public void addCandidate() {
		// given

		jobList.add(createJobDescriptionObject());
		Candidate candidate = createObjectFromCandidate("biro", "stefan", "junior", "stefanbiro.emp@yahoo.com",
				"1234567890", "javajunior", "javajunior", "Cluj-Napoca", jobList);
		Errors errors = new BeanPropertyBindingResult(candidate, "candidate");
		// when
		getValidator().validate(candidate, errors);
		getCandidateService().saveCandidate(candidate);
		// verifi cae un singur element
		Collection<Candidate> comp = getCandidateService().listAll();
		List<Candidate> candidateList = new ArrayList<>(comp);
		// then
		System.out.println(errors.toString());
		assertFalse(errors.hasErrors()); // validate fields from candidate
		assertEquals("biro", candidateList.get(0).getFirstName());

		

	}

	@Test
	public void deleteCandidate() {
		// given
		jobList.add(createJobDescriptionObject());
		Candidate candidateToDelete = createObjectFromCandidate("biro", "stefan", "junior", "stefanbiro.emp@yahoo.com",
				"1234567890", "javajunior", "javajunior", "Cluj-Napoca", jobList);
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

	//@Test
	public void candidateListNotEmpty() {
		// given
		jobList.add(createJobDescriptionObject());
		Candidate candidate = createObjectFromCandidate("biro", "stefan", "junior", "stefanbiro.emp@yahoo.com",
				"1234567890", "javajunior", "javajunior", "Cluj-Napoca", jobList);
		Errors errors = new BeanPropertyBindingResult(candidate, "candidate");
		// when
		getValidator().validate(candidate, errors);
		// adding candidate
		getCandidateService().saveCandidate(candidate);
		Collection<Candidate> comp = getCandidateService().listAll();
		List<Candidate> candidateList = new ArrayList<>(comp);
		// then
		System.out.println(errors.toString());
		assertFalse(errors.hasErrors()); // validate fields from candidate
		assertFalse(getCandidateService().getDAO().getAll().isEmpty());

		getCandidateService().getDAO().delete(candidate);

	}

	@Test
	public void findCandidateById() {
		// given
		jobList.add(createJobDescriptionObject());
		Candidate candidate = createObjectFromCandidate("biro", "stefan", "junior", "stefanbiro.emp@yahoo.com",
				"1234567890", "javajunior", "javajunior", "Cluj-Napoca", jobList);
		Errors errors = new BeanPropertyBindingResult(candidate, "candidate");
		getCandidateService().saveCandidate(candidate);
		// when
		Long candidateId = candidate.getId();
		assertEquals(candidate, getCandidateService().getDAO().findById(candidateId));

	}

	@Test
	public void test_add_no_candidate__first_name() {
		// given
		jobList.add(createJobDescriptionObject());
		Candidate candidate = createObjectFromCandidate("", "stefan", "junior", "stefanbiro.emp@yahoo.com",
				"1234567890", "javajunior", "javajunior", "Cluj-Napoca", jobList);
		Errors errors = new BeanPropertyBindingResult(candidate, "candidate");
		// when
		getValidator().validate(candidate, errors); // validate fields from
													// company
		getCandidateService().saveCandidate(candidate);
		// then
		assertEquals("Please enter your first name.", errors.getFieldError().getDefaultMessage());

		getCandidateService().getDAO().delete(candidate);

	}

	@Test
	public void test_add_no_username() {

		jobList.add(createJobDescriptionObject());
		Candidate candidate = createObjectFromCandidate("biro", "stefan", "", "stefanbiro.emp@yahoo.com", "1234567890",
				"javajunior", "javajunior", "Cluj-Napoca", jobList);
		Errors errors = new BeanPropertyBindingResult(candidate, "company");
		// when
		getValidator().validate(candidate, errors); // validate fields from
													// company
		getCandidateService().saveCandidate(candidate);
		// then
		assertEquals("Please enter your username.", errors.getFieldError().getDefaultMessage());

		getCandidateService().getDAO().delete(candidate);
	}

	private Candidate createObjectFromCandidate(String firstName, String lastName, String userName, String email,
			String phoneNumber, String password, String passwordConfirmed, String location,
			List<JobDescription> jobProfile) {
		Candidate candidate = new Candidate();
		candidate.setFirstName(firstName);
		candidate.setLastName(lastName);
		candidate.setUserName(userName);
		candidate.setEmail(email);
		candidate.setPhoneNumber(phoneNumber);
		candidate.setPassword(password);
		candidate.setPasswordConfirmed(passwordConfirmed);
		candidate.setPreferedLocation(location);
//		candidate.setJobDescription(jobProfile); //Stefan will fix these issues.

		return candidate;
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

}
