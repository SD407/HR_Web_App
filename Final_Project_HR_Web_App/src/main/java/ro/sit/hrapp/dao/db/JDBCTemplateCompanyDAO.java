/*
 * mvc-demo
 * ro.sci.ems.dao.db  
 * JDBCTemplateCompanyDAO.java
 * 
 *
 * MADE FOR TRAINING PURPOSES.
 *
 */
package ro.sit.hrapp.dao.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ro.sit.hrapp.dao.CompanyDAO;
import ro.sit.hrapp.domain.Company;

/**
 * @author Sorin_Dragan
 *
 */
public class JDBCTemplateCompanyDAO implements CompanyDAO {

	private JdbcTemplate jdbcTemplate;

	/**
	 * Constructor for class JDBCTemplateEmpoyeeDAO
	 */
	public JDBCTemplateCompanyDAO(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Collection<Company> getAll() {
		return jdbcTemplate.query("select * from public.companies", new CompanyMapper());
	}

	@Override
	public Company findById(Long id) {
		return jdbcTemplate.queryForObject("select * from public.companies where company_id=?", new Long[] { id },
				new CompanyMapper());
	}

	@Override
	public Company update(Company model) {
		if (model.getId() > 0) {
			this.jdbcTemplate.update("update public.companies set email=?, company_name=?, phone_number=?"
								+ "where company_id = ?", model.getEmail(),
								model.getCompanyName(), model.getPhoneNumber(), model.getId());
		} else {
			
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(model.getPassword());
		
		this.jdbcTemplate.update("insert into public.users (username, password) " + "values (?, ?)",
				model.getUserName(), hashedPassword);
		
		this.jdbcTemplate.update("insert into public.user_roles (username, role) " + "values (?, ?)",
				model.getUserName(), "ROLE_COMPANY");
		
		this.jdbcTemplate.update("insert into public.company_skills (username) values (?)",
				model.getUserName());
		
		this.jdbcTemplate.update(
				"insert into public.companies (username, email, company_name, phone_number) "
						+ "values (?, ?, ?, ?)", model.getUserName(), model.getEmail(),
						model.getCompanyName(), model.getPhoneNumber());
		}
		
		return model;
	}

	@Override
	public boolean delete(Company model) {
		boolean result = false;
		if(!result) {
			this.jdbcTemplate.update("delete from public.company_skills where username=?", model.getUserName());
			this.jdbcTemplate.update("delete from public.companies where username=?", model.getUserName());
			this.jdbcTemplate.update("delete from public.user_roles where username=?", model.getUserName());
			this.jdbcTemplate.update("delete from public.users where username=?", model.getUserName());
			result = true;
		}
		return result;
	}

	@Override
	public Collection<Company> searchByNameCompany(String query) {
		return null;
	}

	private static class CompanyMapper implements RowMapper<Company> {

		@Override
		public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
			Company candidate = new Company();
			candidate.setId(rs.getLong("company_id"));
			candidate.setUserName(rs.getString("username"));
			candidate.setEmail(rs.getString("email"));
			candidate.setCompanyName(rs.getString("company_name"));
			candidate.setPhoneNumber(rs.getString("phone_number"));
			return candidate;
		}

	}

}
