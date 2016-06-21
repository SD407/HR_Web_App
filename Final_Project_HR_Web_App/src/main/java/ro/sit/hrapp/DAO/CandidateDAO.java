package ro.sit.hrapp.DAO;

import java.util.Collection;

import ro.sit.hrapp.domain.Candidate;

public interface CandidateDAO extends BaseDAO<Candidate> {

	Collection<Candidate> searchByNameCandidate(String query);
}
