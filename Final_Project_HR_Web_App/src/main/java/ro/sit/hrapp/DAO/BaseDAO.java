package ro.sit.hrapp.dao;

import java.util.Collection;

import ro.sit.hrapp.domain.AbstractModel;

public interface BaseDAO<T extends AbstractModel> {

	// for companys
	Collection<T> getAllCompanies();

	T findByIdCompany(Long id);

	T updateCompany(T model);

	boolean deleteCompany(T model);

	// for candidate

	Collection<T> getAllCandidates();

	T findByIdCandidate(Long id);

	T updateCandidate(T model);

	boolean deleteCandidate(T model);

}
