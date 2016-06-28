///*
// * mvc-demo
// * ro.sci.ems.dao.db  
// * JDBCTemplateEmpoyeeDAO.java
// * 
// *
// * MADE FOR TRAINING PURPOSES.
// *
// */
//package ro.sit.hrapp.dao.db;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.Collection;
//
//import javax.sql.DataSource;
//
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//
//import ro.sit.hrapp.dao.CandidateDAO;
//import ro.sit.hrapp.domain.Candidate;
//
///**
// * @author Sorin_Dragan
// *
// */
//public class JDBCTemplateCandidateDAO implements CandidateDAO {
//	
//	private JdbcTemplate jdbcTemplate;
//	
//	/**
//	 * Constructor for class JDBCTemplateEmpoyeeDAO
//	 */
//	public JDBCTemplateCandidateDAO(DataSource dataSource) {
//		jdbcTemplate = new JdbcTemplate(dataSource);
//	}
//	
//	//Candidate related
//	/* (non-Javadoc)
//	 * @see ro.sci.ems.dao.BaseDAO#getAll()
//	 */
//	@Override
//	public Collection<Candidate> getAll() {
//		return jdbcTemplate.query("select * from candidate", new CandidateMapper());
//	}
//
//	/* (non-Javadoc)
//	 * @see ro.sci.ems.dao.BaseDAO#findById(java.lang.Long)
//	 */
//	@Override
//	public Candidate findById(Long id) {
//		return jdbcTemplate.queryForObject("select * from candidate where id = ", 
//				new Long[] {id}, 
//				new CandidateMapper());
//	}
//
//	/* (non-Javadoc)
//	 * @see ro.sit.hrapp.dao.BaseDAO#update(ro.sit.hrapp.domain.AbstractModel)
//	 */
//	@Override
//	public Candidate update(Candidate model) {
//		return null;
//	}
//
//	/* (non-Javadoc)
//	 * @see ro.sit.hrapp.dao.BaseDAO#delete(ro.sit.hrapp.domain.AbstractModel)
//	 */
//	@Override
//	public boolean delete(Candidate model) {
//		return false;
//	}
//
//	/* (non-Javadoc)
//	 * @see ro.sit.hrapp.dao.CandidateDAO#searchByNameCandidate(java.lang.String)
//	 */
//	@Override
//	public Collection<Candidate> searchByNameCandidate(String query) {
//		return null;
//	}
//	
//	
//	private static class CandidateMapper implements RowMapper<Candidate> {
//
//		/* (non-Javadoc)
//		 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
//		 */
//		@Override
//		public Candidate mapRow(ResultSet rs, int rowNum) throws SQLException {
//			Candidate candidate = new Candidate();
//			candidate.setId(rs.getLong("id"));
//			candidate.setFirstName(rs.getString("username"));
//			candidate.setFirstName(rs.getString("password"));
//			candidate.setFirstName(rs.getString("first_name"));
//			candidate.setLastName(rs.getString("last_name"));
//			candidate.setLastName(rs.getString("email"));
//			candidate.setLastName(rs.getString("phone_number"));
//			return candidate;
//		}
//		
//	}
//
//}
