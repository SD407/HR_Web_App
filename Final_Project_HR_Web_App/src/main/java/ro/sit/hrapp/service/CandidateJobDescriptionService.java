/*
 * Final_Project_HR_Web_App
 * ro.sit.hrapp.service  
 * JobDescriptionService.java
 * 
 *
 * MADE FOR TRAINING PURPOSES.
 *
 */
package ro.sit.hrapp.service;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.sit.hrapp.dao.JobDescriptionDAO;
import ro.sit.hrapp.domain.JobDescription;

/**
 * @author Sorin_Dragan
 *
 */

@Service
public class CandidateJobDescriptionService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CandidateJobDescriptionService.class);

	@Autowired
	private JobDescriptionDAO candidateJobDescriptionDAO;

	public Collection<JobDescription> listAll() {
		return candidateJobDescriptionDAO.getAll();
	}

	public boolean deleteJobDescription(Long id) {
		LOGGER.debug("Deleting jd for id: " + id);
		JobDescription jobDescription = candidateJobDescriptionDAO.findById(id);
		if (jobDescription != null) {
			candidateJobDescriptionDAO.delete(jobDescription);
			return true;
		}

		return false;

	}

	public JobDescription get(Long id) {
		LOGGER.debug("Getting jd for id: " + id);
		return candidateJobDescriptionDAO.findById(id);

	}

	public void saveJobDescription(JobDescription jobDescription) {
		LOGGER.debug("Saving: " + jobDescription);
		candidateJobDescriptionDAO.update(jobDescription);
	}

	public JobDescriptionDAO getDAO() {
		return candidateJobDescriptionDAO;
	}

	public void setCandidateJobDescriptionDAO(JobDescriptionDAO candidateJobDescriptionDAO) {
		this.candidateJobDescriptionDAO = candidateJobDescriptionDAO;
	}

	/**
	 * @param id
	 * @return
	 */
	public Object findMatches(long id) {
		LOGGER.debug("Finding match...");
		return candidateJobDescriptionDAO.findMatches(id);
	}
	
}
