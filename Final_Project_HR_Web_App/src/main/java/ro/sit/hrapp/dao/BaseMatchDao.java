package ro.sit.hrapp.dao;

import java.util.List;

import ro.sit.hrapp.domain.AbstractModel;
import ro.sit.hrapp.domain.Candidate;
import ro.sit.hrapp.domain.Company;

public interface BaseMatchDao<T extends AbstractModel> {
	
	//You could've written the below code as follows
	// List<T> findModel(T model);

	List<Candidate> findCandidates(Company companies);
	List<Company> findCompanies(Candidate candidate);
	
}
