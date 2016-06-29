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

import java.net.URISyntaxException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ro.sit.hrapp.dao.CandidateDAO;
import ro.sit.hrapp.dao.db.JDBCTemplateCandidateDAO;


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

}
