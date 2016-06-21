package ro.sit.hrapp.dao;

import ro.sit.hrapp.domain.AbstractModel;

public interface BaseDAO<T extends AbstractModel> {
	
	// for companys

	T findByIdCompany(Long id);
	
	T updateCompany(T model);

	boolean deleteCompany(T model);

	// for candidate
	
	T findByIdCandidate(Long id);
	
	T updateCandidate(T model);

	boolean deleteCandidate(T model);
	
}
