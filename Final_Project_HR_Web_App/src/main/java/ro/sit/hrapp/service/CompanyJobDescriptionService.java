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
import java.util.List;

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
public class CompanyJobDescriptionService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyJobDescriptionService.class);

	@Autowired
	private JobDescriptionDAO companyJobDescriptionDAO;

	public Collection<JobDescription> listAll() {
		return companyJobDescriptionDAO.getAll();
	}

	public boolean deleteJobDescription(Long id) {
		LOGGER.debug("Deleting jd for id: " + id);
		JobDescription jobDescription = companyJobDescriptionDAO.findById(id);
		if (jobDescription != null) {
			companyJobDescriptionDAO.delete(jobDescription);
			return true;
		}

		return false;

	}

	public JobDescription get(Long id) {
		LOGGER.debug("Getting jd for id: " + id);
		return companyJobDescriptionDAO.findById(id);

	}

	public void saveJobDescription(JobDescription jobDescription) {
		LOGGER.debug("Saving: " + jobDescription);
		companyJobDescriptionDAO.update(jobDescription);
	}
	
	public List<JobDescription> findMatches() {
		LOGGER.debug("Finding match...");
		return companyJobDescriptionDAO.findMatches();
	}

	public JobDescriptionDAO getDAO() {
		return companyJobDescriptionDAO;
	}

	public void setCompanyJobDescriptionDAO(JobDescriptionDAO companyJobDescriptionDAO) {
		this.companyJobDescriptionDAO = companyJobDescriptionDAO;
	}
	
}
