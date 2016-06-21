package ro.sit.hrapp.dao;

import java.util.Collection;

import ro.sit.hrapp.domain.Candidate;

public interface CandidateDAO extends BaseDAO<Candidate> {

	Collection<Candidate> searchByNameCandidate(String query);
}
