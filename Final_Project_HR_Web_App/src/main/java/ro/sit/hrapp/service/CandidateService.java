package ro.sit.hrapp.service;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import ro.sit.hrapp.dao.CandidateDAO;
import ro.sit.hrapp.domain.Candidate;

@Service
public class CandidateService {


	private static final Logger LOGGER = LoggerFactory.getLogger(CandidateService.class);

	@Autowired
	private CandidateDAO cand_dao; // fake
	
	public Collection<Candidate> listAllCandidates() {
		return cand_dao.getAllCandidates();
	}
	
	public Collection<Candidate> search( String query) {
		LOGGER.debug("Searching for " + query);
		return cand_dao.searchByNameCandidate(query);
	}
	

	public boolean deleteCandidate(Long id) {
		LOGGER.debug("Deleting jd for id: " + id);
		Candidate candidate = cand_dao.findByIdCandidate(id);
		if (candidate != null) {
			cand_dao.deleteCompany(candidate);
			return true;
		}

		return false;

	}
	public Candidate getCandidate(Long id) {
		LOGGER.debug("Getting employee for id: " + id);
		return cand_dao.findByIdCandidate(id);

	}

	public void saveCandidate(Candidate candidate) throws ValidationException {
		LOGGER.debug("Saving: " + candidate);
		 validateCandidate(candidate);

		cand_dao.updateCandidate(candidate);

	}

	public void validateCandidate(Candidate candidate)throws ValidationException {

		List<String> errors = new LinkedList<String>();

		if (StringUtils.isEmpty(candidate.getFirstName())) {
			errors.add("First name is Empty");
		}

		if (StringUtils.isEmpty(candidate.getUserName())) {
			errors.add("User name is Empty");
		}
		
		if (StringUtils.isEmpty(candidate.getEmail())) {
			errors.add("email is Empty");
		}
		if (StringUtils.isEmpty(candidate.getLastName())) {
			errors.add("Last name is Empty");
		}
		if (StringUtils.isEmpty(candidate.getPassword())) {
			errors.add("Pasword is Empty");
		}
		if (StringUtils.isEmpty(candidate.getJobDescription())) {
			errors.add("jobdescription is Empty");
		}
		if (StringUtils.isEmpty(candidate.getPasswordConfirmed())) {
			errors.add("confirmed password is Empty");
		}
		
		if (!errors.isEmpty()) {
			throw new ValidationException(errors.toArray(new String[] {}));
		}
		
	}

	public CandidateDAO getCand_dao() {
		return cand_dao;
	}

	public void setCand_dao(CandidateDAO cand_dao) {
		this.cand_dao = cand_dao;
	}


}
