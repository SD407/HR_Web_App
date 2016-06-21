package ro.sit.hrapp.imdao;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import org.springframework.util.StringUtils;

import ro.sit.hrapp.dao.CandidateDAO;
import ro.sit.hrapp.domain.Candidate;

public class IMCandidateDAO extends ImBaseDao<Candidate> implements CandidateDAO {

	@Override
	public Collection<Candidate> searchByNameCandidate(String query) {
		if (StringUtils.isEmpty(query)) {
			return getAllCandidates();
		}
		
		Collection<Candidate> all = new LinkedList<Candidate>(getAllCandidates());

		for (Iterator<Candidate> it = all.iterator(); it.hasNext();) {
			Candidate candidate = it.next();
			// String ss = emp.getFirstName() + " " + emp.getLastName();
			String ss = "";
			if (!ss.toLowerCase().contains(query.toLowerCase())) {
				it.remove();
			}
		}

		return all;
	}

}
