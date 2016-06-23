package ro.sit.hrapp.dao;

import java.util.Collection;

import ro.sit.hrapp.domain.AbstractModel;

/**
 * 
 * @author stefan
 *
 * @param <T>
 */
public interface BaseDAO<T extends AbstractModel> {
	
	// for companies
	
	Collection<T> getAllCompanies(); //added

	T findByIdCompany(Long id);
	
	T updateCompany(T model);

	boolean deleteCompany(T model);

	// for candidate
	
	Collection<T> getAllCandidates(); //added
	
	T findByIdCandidate(Long id);
	
	T updateCandidate(T model);

	boolean deleteCandidate(T model);
	
}
