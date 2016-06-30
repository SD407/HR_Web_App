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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

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
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public ModelAndView logoutPage (HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){    
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		modelAndView.setView(new RedirectView("/spring/login?logout"));
		return modelAndView;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginPage(@RequestParam(value = "error", required = false) String error,
								  @RequestParam(value = "logout", required = false) String logout) {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("error", "Invalid Credentials provided.");
		modelAndView.addObject("message", "Logged out successfully.");
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
}
