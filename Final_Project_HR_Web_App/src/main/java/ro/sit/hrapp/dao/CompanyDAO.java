package ro.sit.hrapp.dao;

import java.util.Collection;

import ro.sit.hrapp.domain.Company;

/**
 * 
 * @author stefan
 *
 */
public interface CompanyDAO extends BaseDAO<Company> {

	Collection<Company> searchByNameCompany(String query);

}