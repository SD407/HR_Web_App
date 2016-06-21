package ro.sit.hrapp.service;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import ro.sit.hrapp.DAO.CandidateDAO;
import ro.sit.hrapp.domain.Candidate;

public class CandidateService {

	// doua servicii diferite
	private static final Logger LOGGER = LoggerFactory.getLogger(CandidateService.class);

	@Autowired
	private CandidateDAO cand_dao;

	public boolean deleteCandidate(Long id) {
		LOGGER.debug("Deleting jd for id: " + id);
		Candidate candidate = cand_dao.findByIdCandidate(id);
		if (candidate != null) {
			cand_dao.deleteCompany(candidate);
			return true;
		}

		return false;

	}

	public void saveCandidate(Candidate candidate) {
		LOGGER.debug("Saving: " + candidate);
		// validate(jd);

		cand_dao.updateCandidate(candidate);

	}

	public void validateCandidate(Candidate candidate) {

		List<String> errors = new LinkedList<String>();

		if (StringUtils.isEmpty(candidate.getFirstName())) {
			errors.add("Job name is Empty");
		}

		if (StringUtils.isEmpty(candidate.getUserName())) {
			errors.add("department is Empty");
		}
		// etc
	}

	public CandidateDAO getJ_dao() {
		return cand_dao;
	}

	public void setJ_dao(CandidateDAO j_dao) {
		this.cand_dao = j_dao;
	}

}
