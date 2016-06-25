/*
 * Final_Project_HR_Web_App
 * ro.sit.hrapp.cfg.security  
 * a.java
 * 
 *
 * MADE FOR TRAINING PURPOSES.
 *
 */
//package ro.sit.hrapp.cfg.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Import;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
//import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;
//
///**
// * @author Sorin_Dragan
// *
// */
//@EnableWebMvc
//@Configuration
//@ComponentScan({ "ro.sit.hrapp.cfg.security.*" })
//@Import(value = { SecurityConfig.class })
//public class LoginApplicationConfig {
//	
//	@Bean
//	public FreeMarkerViewResolver viewResolver() {
//		FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
//		viewResolver.setCache(true);
//		viewResolver.setPrefix("");
//		viewResolver.setSuffix(".ftl");
//		return viewResolver;
//	}
//	
//	@Bean 
//	public FreeMarkerConfigurer freemarkerConfig() { 
//	    FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer(); 
//	    freeMarkerConfigurer.setTemplateLoaderPath("/templates/spring/");
//	    return freeMarkerConfigurer; 
//	}
//	
//}
