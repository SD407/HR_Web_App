package ro.sit.hrapp.dao.inmemory;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import ro.sit.hrapp.dao.CandidateDAO;
import ro.sit.hrapp.domain.Candidate;
/**
 * 
 * @author stefan
 *
 */
public class IMCandidateDAO extends IMBaseDAO<Candidate> implements CandidateDAO {

	@Override
	public Collection<Candidate> searchByNameCandidate(String query) {

		Collection<Candidate> all = new LinkedList<Candidate>();

		for (Iterator<Candidate> it = all.iterator(); it.hasNext();) {
			Candidate candidate = it.next();
			String ss = candidate.getFirstName() + " " + candidate.getLastName();
			if (!ss.toLowerCase().contains(query.toLowerCase())) {
				it.remove();
			}
		}

		return all;
	}

}
