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

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Sorin_Dragan
 *
 */

@Controller
@RequestMapping("/spring")
public class WebPageController {
	
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

}
