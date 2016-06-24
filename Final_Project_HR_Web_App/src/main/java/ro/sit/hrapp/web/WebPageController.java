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

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Sorin_Dragan
 *
 */

@Controller
@RequestMapping("/index")
public class WebPageController {
	
	@RequestMapping
	public ModelAndView renerIndexPage () throws Exception{
		ModelAndView modelAndView = new ModelAndView("/index/home");
		modelAndView.addObject("title", "Index"); 
		return modelAndView;
	}
	
	@RequestMapping(value = "/home")
	public ModelAndView renerHomePage () throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("title", "Home Page"); 
		return modelAndView;
	}
	
	@RequestMapping(value = "/about")
	public ModelAndView renderAboutPage () throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("title", "About"); 
		return modelAndView;
	}
	
	@RequestMapping(value = "/contact")
	public ModelAndView renderContactPage () throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("title", "Contact"); 
		return modelAndView;
	}

}
