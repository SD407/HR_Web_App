package ro.sit.hrapp.service;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import ro.sit.hrapp.dao.CompanyDAO;
import ro.sit.hrapp.domain.Company;

@Service
public class CompanyService {

	// doua servicii diferite
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyService.class);

	@Autowired
	private CompanyDAO comp_dao;

	public Collection<Company> listAll() {
		return comp_dao.getAllCompanies();
	}

	public Collection<Company> search(String query) {
		LOGGER.debug("Searching for " + query);
		return comp_dao.searchByNameCompany(query);
	}

	public boolean deleteCompany(Long id) {
		LOGGER.debug("Deleting company for id: " + id);
		Company company = comp_dao.findByIdCandidate(id);
		if (company != null) {
			comp_dao.deleteCompany(company);
			return true;
		}

		return false;

	}

	public Company get(Long id) {
		LOGGER.debug("Getting employee for id: " + id);
		return comp_dao.findByIdCompany(id);

	}

	public void saveCompany(Company company) throws ValidationException {
		LOGGER.debug("Saving: " + company);
		validateCompany(company);

		comp_dao.updateCandidate(company);

	}

	public void validateCompany(Company company) throws ValidationException {

		List<String> errors = new LinkedList<String>();

		if (StringUtils.isEmpty(company.getCompanyName())) {
			errors.add("Company name is Empty");
		}

		if (StringUtils.isEmpty(company.getUserName())) {
			errors.add("UserName is Empty");
		}
		if (StringUtils.isEmpty(company.getPassword())) {
			errors.add("Password is Empty");
		}
		if (StringUtils.isEmpty(company.getPasswordConfirmed())) {
			errors.add("Confirmed password is Empty");
		}
		if (StringUtils.isEmpty(company.getEmail())) {
			errors.add("The e-mail  is Empty");
		}
		if (StringUtils.isEmpty(company.getJobLocation())) {
			errors.add("The job location is Empty");
		}
		if (StringUtils.isEmpty(company.getJobDescription())) {
			errors.add("The jobdescription is empty is Empty");
		}
		if (!errors.isEmpty()) {
			throw new ValidationException(errors.toArray(new String[] {}));
		}

	}

	public CompanyDAO getComp_dao() {
		return comp_dao;
	}

	public void setComp_dao(CompanyDAO comp_dao) {
		this.comp_dao = comp_dao;
	}

}
