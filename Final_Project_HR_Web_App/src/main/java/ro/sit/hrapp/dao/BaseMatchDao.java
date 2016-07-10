package ro.sit.hrapp.dao;

import java.util.List;

import ro.sit.hrapp.domain.AbstractModel;
import ro.sit.hrapp.domain.Candidate;
import ro.sit.hrapp.domain.Company;

public interface BaseMatchDao<T extends AbstractModel> {

	List<Candidate> findCandidates(Company companies);
	List<Company> findCompanies(Candidate candidate);
	
}
