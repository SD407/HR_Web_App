/*
 * Final_Project_HR_Web_App
 * ro.sit.hrapp.web  
 * WebPageController.java
 * 
 *
 * MADE FOR TRAINING PURPOSES.
 *
 */
package ro.sit.hrapp.web;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ro.sit.hrapp.service.CandidateService;

/**
 * @author Sorin_Dragan
 *
 */

@Controller
@RequestMapping("/spring")
public class WebPageController {
	
	@Autowired
	private CandidateService candidateService;
	
	@RequestMapping
	public ModelAndView renerIndexPage () throws Exception{
		ModelAndView modelAndView = new ModelAndView("/spring/home");
		modelAndView.addObject("title", "Index"); 
		return modelAndView;
	}
	
	@RequestMapping(value = "/home")
	public ModelAndView renerHomePage () throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("title", "Home Page"); 
		modelAndView.addObject("user", getPrincipal());
		return modelAndView;
	}
	
	@RequestMapping(value = "/about")
	public ModelAndView renderAboutPage () throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("title", "About"); 
		modelAndView.addObject("user", getPrincipal());
		return modelAndView;
	}
	
	@RequestMapping(value = "/contact")
	public ModelAndView renderContactPage () throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("title", "Contact"); 
		modelAndView.addObject("user", getPrincipal());
		return modelAndView;
	}
	
	@RequestMapping(value = "/details")
	public ModelAndView renderDetailsPage () throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("title", "Details"); 
		modelAndView.addObject("candidates", candidateService.listAll());
		modelAndView.addObject("user", getPrincipal());
		modelAndView.addObject("role", getPrincipalRole());
		return modelAndView;
	}
	
	@RequestMapping(value = "/reports")
	public ModelAndView renderReportsPage () throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("title", "Reports"); 
		modelAndView.addObject("user", getPrincipal());
		return modelAndView;
	}
	
	@RequestMapping(value = "/matching")
	public ModelAndView renderMatchingPage () throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("title", "Matching"); 
		modelAndView.addObject("user", getPrincipal());
		return modelAndView;
	}
	
	//finds the current user on the page
	//if the user is not logged in, he's an "anonymousUser"
	//if the user is logged in, it will return his userName
    private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 
        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
    
    //needs to be tested
    private Collection<? extends GrantedAuthority> getPrincipalRole(){
    	Collection<? extends GrantedAuthority> role = null;
    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	
    	if (principal instanceof UserDetails) {
    		role = ((UserDetails)principal).getAuthorities();
    	}
    	return role;
    }

}
