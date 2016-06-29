/*
 * mvc-demo
 * ro.sci.ems.dao.db  
 * JDBCTemplateEmpoyeeDAO.java
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

import ro.sit.hrapp.dao.CandidateDAO;
import ro.sit.hrapp.domain.Candidate;

/**
 * @author Sorin_Dragan
 *
 */
public class JDBCTemplateCandidateDAO implements CandidateDAO {

	private JdbcTemplate jdbcTemplate;

	/**
	 * Constructor for class JDBCTemplateEmpoyeeDAO
	 */
	public JDBCTemplateCandidateDAO(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Collection<Candidate> getAll() {
		return jdbcTemplate.query("select * from public.candidates", new CandidateMapper());
	}

	@Override
	public Candidate findById(Long id) {
		return jdbcTemplate.queryForObject("select * from public.candidates where candidate_id = ", new Long[] { id },
				new CandidateMapper());
	}

	@Override
	public Candidate update(Candidate model) {
//		Try and implement BCrypt
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		String hashedPassword = passwordEncoder.encode(model.getPassword());
		
		this.jdbcTemplate.update("insert into public.users (username, password) " + "values (?, ?)",
				model.getUserName(), model.getPassword());
		
		this.jdbcTemplate.update("insert into public.user_roles (username, role) " + "values (?, ?)",
				model.getUserName(), "ROLE_CANDIDATE");
		
		this.jdbcTemplate.update(
				"insert into public.candidates (username, email, first_name, last_name, phone_number) "
						+ "values (?, ?, ?, ?, ?)", model.getUserName(), model.getEmail(),
						model.getFirstName(), model.getLastName(), model.getPhoneNumber());
		
		return model;
	}

	@Override
	public boolean delete(Candidate model) {
		// return this.jdbcTemplate.update("delete from candidates where
		// candidate_id = ", model.getId() );
		return false;
	}

	@Override
	public Collection<Candidate> searchByNameCandidate(String query) {
		return null;
	}

	private static class CandidateMapper implements RowMapper<Candidate> {

		@Override
		public Candidate mapRow(ResultSet rs, int rowNum) throws SQLException {
			Candidate candidate = new Candidate();
			candidate.setId(rs.getLong("candidate_id"));
			candidate.setUserName(rs.getString("username"));
			candidate.setEmail(rs.getString("email"));
			candidate.setFirstName(rs.getString("first_name"));
			candidate.setLastName(rs.getString("last_name"));
			candidate.setPhoneNumber(rs.getString("phone_number"));
			return candidate;
		}

	}

}
