/*
 * Final_Project_HR_Web_App
 * ro.sit.hrapp.cfg  
 * SecurityConfiguration.java
 * 
 *
 * MADE FOR TRAINING PURPOSES.
 *
 */
package ro.sit.hrapp.cfg.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Sorin_Dragan
 *
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		
	  auth.jdbcAuthentication().dataSource(dataSource)
	  	.passwordEncoder(passwordEncoder)
		.usersByUsernameQuery(
			"select username,password, enabled from users where username=?")
		.authoritiesByUsernameQuery(
			"select username, role from user_roles where username=?");
	}	
	
//	In memory authentication
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth
//			.inMemoryAuthentication()
//				.withUser("admin").password("admin").roles("USER","ADMIN").and()
//				.withUser("user").password("password").roles("USER");
//	}
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
	            .antMatchers("/css/**", "/js/**", "/images/**", "/fonts/**").permitAll()
	            .antMatchers("/spring/registrationPage*").permitAll()
	            .antMatchers("/spring/registerCompany*").permitAll()
	            .antMatchers("/spring/registerCandidate*").permitAll()
	            .antMatchers("/spring/home*").permitAll()
	            .antMatchers("/spring/about*").permitAll()
	            .antMatchers("/spring/contact*").permitAll()
            	.antMatchers("/spring/details*").access("hasRole('ROLE_CANDIDATE') "
            				+ "or hasRole('ROLE_COMPANY') or hasRole('ROLE_ADMIN')")
                .anyRequest().authenticated()
                .and()
            .formLogin().loginPage("/spring/login")
            	.failureUrl("/spring/login?error")
            	.defaultSuccessUrl("/spring/details")
                .usernameParameter("userName")
                .passwordParameter("password")
                .permitAll()
                .and()
            .logout()
            	.logoutSuccessUrl("/spring/login?logout")
            	.invalidateHttpSession(true)
            	.permitAll();
    }
}
