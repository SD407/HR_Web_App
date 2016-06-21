package ro.sit.hrapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.ContextConfiguration;

import ro.sit.hrapp.cfg.ApplicationConfiguration;

@SpringBootApplication
@ContextConfiguration(classes = {ApplicationConfiguration.class})
public class HrSpringMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(HrSpringMvcApplication.class, args);
	}
}
