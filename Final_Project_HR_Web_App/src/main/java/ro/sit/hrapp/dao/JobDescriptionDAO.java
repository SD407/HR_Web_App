/*
 * Final_Project_HR_Web_App
 * ro.sit.hrapp.dao  
 * JobDescriptionDAO.java
 * 
 *
 * MADE FOR TRAINING PURPOSES.
 *
 */
package ro.sit.hrapp.dao;

import java.util.Collection;
import java.util.List;

import ro.sit.hrapp.domain.JobDescription;

/**
 * @author Sorin_Dragan
 *
 */
public interface JobDescriptionDAO extends BaseDAO<JobDescription>{
	
	Collection<JobDescription> searchByNameJobDescription(String query);
	
	List<JobDescription> findMatches(Long id);

}
