/*
 * mvc-demo
 * ro.sci.ems.dao.db  
 * JDBCTemplateCandidateDAO.java
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
		return jdbcTemplate.queryForObject("select * from public.candidates where candidate_id=?", new Long[] { id },
				new CandidateMapper());
	}

	@Override
	public Candidate update(Candidate model) {
		if (model.getId() > 0) {
			this.jdbcTemplate.update("update public.candidates set email=?, first_name=?, last_name=?, phone_number=?"
								+ "where candidate_id=?", model.getEmail(),model.getFirstName(), model.getLastName(), 
								model.getPhoneNumber(), model.getId());
		} else {
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(model.getPassword());
		
		this.jdbcTemplate.update("insert into public.users (username, password) " + "values (?, ?)",
				model.getUserName(), hashedPassword);
		
		this.jdbcTemplate.update("insert into public.user_roles (username, role) " + "values (?, ?)",
				model.getUserName(), "ROLE_CANDIDATE");
		
		this.jdbcTemplate.update("insert into public.candidate_skills (username) values (?)",
				model.getUserName());
		
		this.jdbcTemplate.update(
				"insert into public.candidates (username, email, first_name, last_name, phone_number) "
						+ "values (?, ?, ?, ?, ?)", model.getUserName(), model.getEmail(),
						model.getFirstName(), model.getLastName(), model.getPhoneNumber());
		}
		
		return model;
	}

	@Override
	public boolean delete(Candidate model) {
		boolean result = false;
		if(!result) {
			this.jdbcTemplate.update("delete from public.candidate_skills where username=?", model.getUserName());
			this.jdbcTemplate.update("delete from public.candidates where username=?", model.getUserName());
			this.jdbcTemplate.update("delete from public.user_roles where username=?", model.getUserName());
			this.jdbcTemplate.update("delete from public.users where username=?", model.getUserName());
			result = true;
		}
		return result;
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
