package ro.sit.hrapp.service;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.sit.hrapp.dao.CandidateDAO;
import ro.sit.hrapp.domain.Candidate;

/**
 * 
 * @author stefan
 *
 */

@Service
public class CandidateService {

	// doua servicii diferite
	private static final Logger LOGGER = LoggerFactory.getLogger(CandidateService.class);

	@Autowired
	private CandidateDAO candidateDAO;

	public Collection<Candidate> listAll() {
		return candidateDAO.getAll();
	}

	public boolean deleteCandidate(Long id) {
		LOGGER.debug("Deleting jd for id: " + id);
		Candidate candidate = candidateDAO.findById(id);
		if (candidate != null) {
			candidateDAO.delete(candidate);
			return true;
		}

		return false;

	}

	public Candidate get(Long id) {
		LOGGER.debug("Getting candidate for id: " + id);
		return candidateDAO.findById(id);

	}

	public void saveCandidate(Candidate candidate) {
		LOGGER.debug("Saving: " + candidate);
		candidateDAO.update(candidate);
	}

	public CandidateDAO getDAO() {
		return candidateDAO;
	}

	public void setCandidateDAO(CandidateDAO candidateDAO) {
		this.candidateDAO = candidateDAO;
	}

}
