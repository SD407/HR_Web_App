package ro.sit.hrapp.service;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ro.sit.hrapp.dao.CandidateDAO;
import ro.sit.hrapp.domain.Candidate;

/**
 * 
 * @author stefan
 *
 */
public class CandidateService {

	// doua servicii diferite
	private static final Logger LOGGER = LoggerFactory.getLogger(CandidateService.class);

	@Autowired
	private CandidateDAO candidateDAO;
	
	public Collection<Candidate> listAll() {
		return candidateDAO.getAllCandidates();
	}

	public boolean deleteCandidate(Long id) {
		LOGGER.debug("Deleting jd for id: " + id);
		Candidate candidate = candidateDAO.findByIdCandidate(id);
		if (candidate != null) {
			candidateDAO.deleteCandidate(candidate);
			return true;
		}

		return false;

	}

	public void saveCandidate(Candidate candidate) {
		LOGGER.debug("Saving: " + candidate);
		candidateDAO.updateCandidate(candidate);
	}

	public CandidateDAO getDAO() {
		return candidateDAO;
	}

	public void setDAO(CandidateDAO dao) {
		this.candidateDAO = dao;
	}

}
