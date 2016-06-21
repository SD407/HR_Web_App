package ro.sit.hrapp.imdao;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import ro.sit.hrapp.dao.CompanyDAO;
import ro.sit.hrapp.domain.Company;

@Component
public class IMCompanyDAO extends ImBaseDao<Company> implements CompanyDAO {

	@Override
	public Collection<Company> searchByNameCompany(String query) {

		if (StringUtils.isEmpty(query)) {
			return getAllCompanies();
		}

		Collection<Company> all = new LinkedList<Company>(getAllCompanies());

		for (Iterator<Company> it = all.iterator(); it.hasNext();) {
			Company emp = it.next();
			// String ss = emp.getFirstName() + " " + emp.getLastName();
			String ss = "";
			if (!ss.toLowerCase().contains(query.toLowerCase())) {
				it.remove();
			}
		}

		return all;
	}

	

}
