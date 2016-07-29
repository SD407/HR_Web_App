/*
 * Final_Project_HR_Web_App
 * ro.sit.hrapp.cfg.security.db  
 * ApplicationConfiguration.java
 * 
 *
 * MADE FOR TRAINING PURPOSES.
 *
 */
package ro.sit.hrapp.cfg.db;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import ro.sit.hrapp.dao.CandidateDAO;
import ro.sit.hrapp.dao.CompanyDAO;
import ro.sit.hrapp.dao.JobDescriptionDAO;
import ro.sit.hrapp.dao.db.JDBCTemplateCandidateDAO;
import ro.sit.hrapp.dao.db.JDBCTemplateCompanyDAO;
import ro.sit.hrapp.dao.db.JDBCTemplateCompanyJobDescriptionDAO;
import ro.sit.hrapp.dao.db.JDBCTemplateCandidateJobDescriptionDAO;


/**
 * @author Sorin_Dragan
 *
 */

@Configuration
public class ApplicationConfiguration {
	
//	@Bean
//	public DataSource dataSource() {
//		BasicDataSource dataSource = new BasicDataSource();
//		dataSource.setDriverClassName("org.postgresql.Driver");
//		
//
//		String url = new StringBuilder()
//				.append("jdbc:")
//				.append("postgresql")
//				.append("://")
//				.append("127.0.0.1")
//				.append(":")
//				.append("5432")
//				.append("/")
//				.append("webapp").toString();
//		
//		dataSource.setUrl(url);
//		dataSource.setUsername("postgres");
//		dataSource.setPassword("password");
//		return dataSource;
//	}

	//Config for HEROKU DB
    @Bean
    public BasicDataSource dataSource() {
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        String username = System.getenv("JDBC_DATABASE_USERNAME");
        String password = System.getenv("JDBC_DATABASE_PASSWORD");

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(dbUrl);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);

        return basicDataSource;
    }

	@Bean
	public CandidateDAO candidateDAO() {
		return new JDBCTemplateCandidateDAO(dataSource());
	}
	
	@Bean
	public CompanyDAO companyDAO() {
		return new JDBCTemplateCompanyDAO(dataSource());
	}
	
	@Bean
	public JobDescriptionDAO candidateJobDescriptionDAO() {
		return new JDBCTemplateCandidateJobDescriptionDAO(dataSource());
	}
	
	@Bean
	public JobDescriptionDAO companyJobDescriptionDAO() {
		return new JDBCTemplateCompanyJobDescriptionDAO(dataSource());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
}
