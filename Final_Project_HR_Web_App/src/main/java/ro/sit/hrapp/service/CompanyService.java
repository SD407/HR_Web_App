package ro.sit.hrapp.service;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.sit.hrapp.dao.CompanyDAO;
import ro.sit.hrapp.domain.Company;

/**
 * 
 * @author stefan
 *
 */

@Service
public class CompanyService {

	// doua servicii diferite
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyService.class);

	@Autowired
	private CompanyDAO companyDAO;
	
	public Collection<Company> listAll() {
		return companyDAO.getAll();
	}

	public boolean deleteCompany(Long id) {
		LOGGER.debug("Deleting company for id: " + id);
		Company company = companyDAO.findById(id);
		if (company != null) {
			companyDAO.delete(company);
			return true;
		}

		return false;

	}
	public Company get(Long id) {
		LOGGER.debug("Getting employee for id: " + id);
		return companyDAO.findById(id);

	}

	public void saveCompany(Company company) {
		LOGGER.debug("Saving: " + company);

		companyDAO.update(company);

	}

	public CompanyDAO getCompanyDAO() {
		return companyDAO;
	}

	public void setCompanyDAO(CompanyDAO companyDAO) {
		this.companyDAO = companyDAO;
	}

}
