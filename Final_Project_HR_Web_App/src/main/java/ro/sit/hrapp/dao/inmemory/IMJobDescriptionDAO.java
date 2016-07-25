/*
 * Final_Project_HR_Web_App
 * ro.sit.hrapp.dao.inmemory  
 * IMJobDescriptionDAO.java
 * 
 *
 * MADE FOR TRAINING PURPOSES.
 *
 */
package ro.sit.hrapp.dao.inmemory;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import ro.sit.hrapp.dao.JobDescriptionDAO;
import ro.sit.hrapp.domain.JobDescription;

/**
 * @author Sorin_Dragan
 *
 */

//@Component
public class IMJobDescriptionDAO extends IMBaseDAO<JobDescription> implements JobDescriptionDAO{
	
	@Override
	public Collection<JobDescription> searchByNameJobDescription(String query) {

		Collection<JobDescription> all = new LinkedList<JobDescription>();

		for (Iterator<JobDescription> it = all.iterator(); it.hasNext();) {
			JobDescription jd = it.next();
			
			String ss = jd.getCurrentJobTitle();
			if (!ss.toLowerCase().contains(query.toLowerCase())) {
				it.remove();
			}
		}

		return all;
	}

	/* (non-Javadoc)
	 * @see ro.sit.hrapp.dao.JobDescriptionDAO#findMatches()
	 */
	@Override
	public List<JobDescription> findMatches(Long id) {
		return null;
	}

}
