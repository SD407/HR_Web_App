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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Sorin_Dragan
 *
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
				.withUser("admin").password("admin").roles("USER","ADMIN").and()
				.withUser("user").password("password").roles("USER");
	}
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            	.antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin().loginPage("/spring/login")
            	.failureUrl("/spring/login?error")
            	.defaultSuccessUrl("/spring/home")
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
