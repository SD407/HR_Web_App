package ro.sit.hrapp.dao.inmemory;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import org.springframework.stereotype.Component;

import ro.sit.hrapp.dao.CompanyDAO;
import ro.sit.hrapp.domain.Company;

/**
 * 
 * @author stefan
 *
 */

//@Component
public class IMCompanyDAO extends IMBaseDAO<Company> implements CompanyDAO {

	@Override
	public Collection<Company> searchByNameCompany(String query) {

		Collection<Company> all = new LinkedList<Company>();

		for (Iterator<Company> it = all.iterator(); it.hasNext();) {
			Company company = it.next();
			
			String ss = company.getCompanyName();
			if (!ss.toLowerCase().contains(query.toLowerCase())) {
				it.remove();
			}
		}

		return all;
	}

}
