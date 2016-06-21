package ro.sit.hrapp.service;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import ro.sit.hrapp.DAO.CompanyDAO;
import ro.sit.hrapp.domain.Company;

public class CompanyService {

	// doua servicii diferite
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyService.class);

	@Autowired
	private CompanyDAO comp_dao;

	public boolean deleteCompany(Long id) {
		LOGGER.debug("Deleting company for id: " + id);
		Company company = comp_dao.findByIdCandidate(id);
		if (company != null) {
			comp_dao.deleteCompany(company);
			return true;
		}

		return false;

	}

	public void saveCompany(Company company) {
		LOGGER.debug("Saving: " + company);
		// validate(jd);

		comp_dao.updateCandidate(company);

	}

	public void validateCompany(Company company) {

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
			errors.add("UserName is Empty");
		}

	}

	public CompanyDAO getC_dao() {
		return comp_dao;
	}

	public void setC_dao(CompanyDAO c_dao) {
		this.comp_dao = c_dao;
	}

}
