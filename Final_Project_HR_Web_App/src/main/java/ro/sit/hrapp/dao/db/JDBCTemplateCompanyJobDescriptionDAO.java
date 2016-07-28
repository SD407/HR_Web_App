/*
 * Final_Project_HR_Web_App
 * ro.sit.hrapp.dao.db  
 * JDBCTemplateCompanyJobDescriptionDAO.java
 * 
 *
 * MADE FOR TRAINING PURPOSES.
 *
 */
package ro.sit.hrapp.dao.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import ro.sit.hrapp.dao.JobDescriptionDAO;
import ro.sit.hrapp.domain.JobDescription;

/**
 * @author Sorin_Dragan
 *
 */
public class JDBCTemplateCompanyJobDescriptionDAO implements JobDescriptionDAO {

	private JdbcTemplate jdbcTemplate;

	/**
	 * Constructor for class JDBCTemplateEmpoyeeDAO
	 */
	public JDBCTemplateCompanyJobDescriptionDAO(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Collection<JobDescription> getAll() {
		return jdbcTemplate.query("select * from public.company_skills", new JobDescriptionMapper());
	}

	@Override
	public JobDescription findById(Long id) {
		return jdbcTemplate.queryForObject("select * from public.company_skills where company_skill_id=?",
				new Long[] { id }, new JobDescriptionMapper());
	}

	@Override
	public JobDescription update(JobDescription model) {

		if (model.getId() > 0) {
			this.jdbcTemplate.update(
					"update public.company_skills set job_title=?, years_experience=?, location=?, personal_skill=?,"
							+ " professional_skill=? where company_skill_id=?",
					model.getCurrentJobTitle(), model.getYearOfExperience(), model.getLocation(),
					model.getPersonalSkills(), model.getProfessionalSkills(), model.getId());
		} else {
			this.jdbcTemplate.update(
					"insert into public.company_skills (job_title, years_experience, location)" + " values (?, ?, ?)",
					model.getCurrentJobTitle(), model.getYearOfExperience(), model.getLocation());
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
			jobDescription.setId(rs.getLong("company_skill_id"));
			jobDescription.setUserName(rs.getString("username"));
			jobDescription.setCurrentJobTitle(rs.getString("job_title"));
			jobDescription.setYearOfExperience(rs.getString("years_experience"));
			jobDescription.setLocation(rs.getString("location"));
			jobDescription.setPersonalSkills(rs.getString("personal_skill"));
			jobDescription.setProfessionalSkills(rs.getString("professional_skill"));
			return jobDescription;
		}

	}

	@Autowired
	DataSource dataSource;
	@Override
	/**
	 * Finds matching candidates
	 */
	public List<JobDescription> findMatches(Long id) {


		JdbcTemplate companyJdbcTemplate = new JdbcTemplate(dataSource);

		String companyListSQL = "select * from company_skills where company_skill_id=?";
		List<JobDescription> companySkillList = companyJdbcTemplate.query(companyListSQL, new Long[] { id }, mapJD());

		String candidateListSQL = "select * from candidate_skills";
		List<JobDescription> candidateSkillList = this.jdbcTemplate.query(candidateListSQL, mapJD());
		
		List<JobDescription> result = new ArrayList<>();

		for (int i = 0; i < companySkillList.size(); i++) {
			for (int j = 0; j < candidateSkillList.size(); j++) {
				
				float matchPercentage = 0f;
				
				int i1 = Integer.parseInt(companySkillList.get(i).getYearOfExperience().substring(2));
				int i2 = Integer.parseInt(candidateSkillList.get(j).getYearOfExperience().substring(2));
				
				/*
				 * company has the same location as the candidate?
				 */
				if (companySkillList.get(i).getLocation().equals(candidateSkillList.get(j).getLocation())) {
					matchPercentage += 20;
					/*
					 * company years of experience are matched?
					 */
					if (i1 <= i2) {
						matchPercentage += 20;
						/*
						 * the job position searched by the company is the same as the candidates?
						 */
						if (companySkillList.get(i).getCurrentJobTitle().equals(candidateSkillList.get(j).getCurrentJobTitle())) {
							matchPercentage += 20;
							/*
							 * getPersonalSkills gets a string of skills separated by commas
							 */
							if ((companySkillList.get(i).getPersonalSkills().length() >= candidateSkillList.get(j).getPersonalSkills().length()) ||
								(companySkillList.get(i).getPersonalSkills().length() <= candidateSkillList.get(j).getPersonalSkills().length())) {
								String [] personalSkills = candidateSkillList.get(j).getPersonalSkills().split(",");
								matchPercentage += ((20 / 3) * personalSkills.length);
								/*
								 * getProfessionalSkills gets a string of skills separated by commas
								 */
								if ((companySkillList.get(i).getProfessionalSkills().length() >= candidateSkillList.get(j).getProfessionalSkills().length()) ||
									(companySkillList.get(i).getProfessionalSkills().length() <= candidateSkillList.get(j).getProfessionalSkills().length())) {
									String [] professionalSkills = candidateSkillList.get(j).getProfessionalSkills().split(",");
									matchPercentage += ((20 / 3) * professionalSkills.length);
									/*
									 * If matching percentage is over 70, add to result
									 */
									if (matchPercentage > 70f) {
										result.add(candidateSkillList.get(j));
									}
								}
							}
						}
					}
				}
			}
		}
		
		return result;
	}

	/**
	 * @return
	 */
	private RowMapper<JobDescription> mapJD() {
		return new RowMapper<JobDescription>() {
			@Override
			public JobDescription mapRow(ResultSet rs, int rowNum) throws SQLException {
				JobDescription jobDescription = new JobDescription();
				jobDescription.setUserName(rs.getString("username"));
				jobDescription.setCurrentJobTitle(rs.getString("job_title"));
				jobDescription.setYearOfExperience(rs.getString("years_experience"));
				jobDescription.setLocation(rs.getString("location"));
				jobDescription.setPersonalSkills(rs.getString("personal_skill"));
				jobDescription.setProfessionalSkills(rs.getString("professional_skill"));
				return jobDescription;
			}

		};
	}

}
