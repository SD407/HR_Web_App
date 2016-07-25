/*
 * Final_Project_HR_Web_App
 * ro.sit.hrapp.dao.db  
 * JDBCTemplateJobDescriptionDAO.java
 * 
 *
 * MADE FOR TRAINING PURPOSES.
 *
 */
package ro.sit.hrapp.dao.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import ro.sit.hrapp.dao.JobDescriptionDAO;
import ro.sit.hrapp.domain.JobDescription;

/**
 * @author Sorin_Dragan
 *
 */
public class JDBCTemplateCandidateJobDescriptionDAO implements JobDescriptionDAO {

	private JdbcTemplate jdbcTemplate;

	/**
	 * Constructor for class JDBCTemplateEmpoyeeDAO
	 */
	public JDBCTemplateCandidateJobDescriptionDAO(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Collection<JobDescription> getAll() {
		return jdbcTemplate.query("select * from public.candidate_skills", new JobDescriptionMapper());
	}

	@Override
	public JobDescription findById(Long id) {
		return jdbcTemplate.queryForObject("select * from public.candidate_skills where candidate_skill_id=?",
				new Long[] { id }, new JobDescriptionMapper());
	}

	@Override
	public JobDescription update(JobDescription model) {

		if (model.getId() > 0) {
			this.jdbcTemplate.update(
					"update public.candidate_skills set job_title=?, years_experience=?, location=?, personal_skill=?,"
					+ " professional_skill=? where candidate_skill_id=?",model.getCurrentJobTitle(), model.getYearOfExperience(), 
					model.getLocation(), model.getPersonalSkills(), model.getProfessionalSkills(), model.getId());
		} else {
			this.jdbcTemplate.update(
					"insert into public.candidate_skills (job_title, years_experience, location)"
							+ " values (?, ?, ?)", model.getCurrentJobTitle(),
							model.getYearOfExperience(), model.getLocation());
		}

		return model;
	}

	@Override
	public boolean delete(JobDescription model) {
		// boolean result = false;
		// if(!result) {
		// this.jdbcTemplate.update("delete from public.candidate_skills where
		// username=?" );
		// result = true;
		// }
		// return result;
		return false;
	}

	@Override
	public Collection<JobDescription> searchByNameJobDescription(String query) {
		return null;
	}

	private static class JobDescriptionMapper implements RowMapper<JobDescription> {

		@Override
		public JobDescription mapRow(ResultSet rs, int rowNum) throws SQLException {
			JobDescription jobDescription = new JobDescription();
			jobDescription.setId(rs.getLong("candidate_skill_id"));
			jobDescription.setUserName(rs.getString("username"));
			jobDescription.setCurrentJobTitle(rs.getString("job_title"));
			jobDescription.setYearOfExperience(rs.getString("years_experience"));
			jobDescription.setLocation(rs.getString("location"));
			jobDescription.setPersonalSkills(rs.getString("personal_skill"));
			jobDescription.setProfessionalSkills(rs.getString("professional_skill"));
			return jobDescription;
		}

	}

	/* (non-Javadoc)
	 * @see ro.sit.hrapp.dao.JobDescriptionDAO#findMatches()
	 */
	@Override
	public List<JobDescription> findMatches(Long id) {
		return null;
	}

}
