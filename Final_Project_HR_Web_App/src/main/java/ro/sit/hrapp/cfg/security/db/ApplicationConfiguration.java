/*
 * Final_Project_HR_Web_App
 * ro.sit.hrapp.cfg.security.db  
 * ApplicationConfiguration.java
 * 
 *
 * MADE FOR TRAINING PURPOSES.
 *
 */
package ro.sit.hrapp.cfg.security.db;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Sorin_Dragan
 *
 */

@Configuration
public class ApplicationConfiguration {
	
	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		

		String url = new StringBuilder()
				.append("jdbc:")
				.append("postgresql")
				.append("://")
				.append("127.0.0.1")
				.append(":")
				.append("5432")
				.append("/")
				.append("webapp").toString();
		
		dataSource.setUrl(url);
		dataSource.setUsername("postgres");
		dataSource.setPassword("password");
		return dataSource;
	}

}
