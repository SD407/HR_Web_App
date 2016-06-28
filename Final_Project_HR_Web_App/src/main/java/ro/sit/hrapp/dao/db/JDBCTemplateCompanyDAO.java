///*
// * Final_Project_HR_Web_App
// * ro.sit.hrapp.dao.db  
// * a.java
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
//import ro.sit.hrapp.dao.CompanyDAO;
//import ro.sit.hrapp.domain.Company;
//
///**
// * @author Sorin_Dragan
// *
// */
//public class JDBCTemplateCompanyDAO implements CompanyDAO {
//	
//	private JdbcTemplate jdbcTemplate;
//	
//	/**
//	 * Constructor for class JDBCTemplateEmpoyeeDAO
//	 */
//	public JDBCTemplateCompanyDAO(DataSource dataSource) {
//		jdbcTemplate = new JdbcTemplate(dataSource);
//	}
//	
//	//Candidate related
//	/* (non-Javadoc)
//	 * @see ro.sci.ems.dao.BaseDAO#getAll()
//	 */
//	@Override
//	public Collection<Company> getAll() {
//		return jdbcTemplate.query("select * from employee", new CompanyMapper());
//	}
//
//	/* (non-Javadoc)
//	 * @see ro.sci.ems.dao.BaseDAO#findById(java.lang.Long)
//	 */
//	@Override
//	public Company findById(Long id) {
//		return jdbcTemplate.queryForObject("select * from employee where id = ", 
//				new Long[] {id}, 
//				new CompanyMapper());
//	}
//
//	/* (non-Javadoc)
//	 * @see ro.sit.hrapp.dao.BaseDAO#update(ro.sit.hrapp.domain.AbstractModel)
//	 */
//	@Override
//	public Company update(Company model) {
//		return null;
//	}
//
//	/* (non-Javadoc)
//	 * @see ro.sit.hrapp.dao.BaseDAO#delete(ro.sit.hrapp.domain.AbstractModel)
//	 */
//	@Override
//	public boolean delete(Company model) {
//		return false;
//	}
//
//	/* (non-Javadoc)
//	 * @see ro.sit.hrapp.dao.CandidateDAO#searchByNameCandidate(java.lang.String)
//	 */
//	@Override
//	public Collection<Company> searchByNameCompany(String query) {
//		return null;
//	}
//	
//	
//	private static class CompanyMapper implements RowMapper<Company> {
//
//		/* (non-Javadoc)
//		 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
//		 */
//		@Override
//		public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
//			Company company = new Company();
//			company.setId(rs.getLong("id"));
//			return company;
//		}
//		
//	}
//
//}
//
