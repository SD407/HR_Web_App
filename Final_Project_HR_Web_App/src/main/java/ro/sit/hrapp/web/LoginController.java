/*
 * Final_Project_HR_Web_App
 * ro.sit.hrapp.web  
 * LoginController.java
 * 
 *
 * MADE FOR TRAINING PURPOSES.
 *
 */
package ro.sit.hrapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Sorin_Dragan
 *
 */
@Controller
public class LoginController {
	
	@RequestMapping("/spring/login")
	public ModelAndView renderLoginPage () throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("title", "Login"); 
		return modelAndView;
	}
	
//	@RequestMapping(value = "/spring/login", method = RequestMethod.GET)
//	public ModelAndView loginPage(@RequestParam(value = "error",required = false) String error,
//	@RequestParam(value = "/spring/logout",	required = false) String logout) {
//		
//		ModelAndView model = new ModelAndView();
//		if (error != null) {
//			model.addObject("error", "Invalid Credentials provided.");
//		}
//
//		if (logout != null) {
//			model.addObject("message", "Logged out successfully.");
//		}
//
//		model.setViewName("login");
//		return model;
//	}
	
}
