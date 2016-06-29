/*
 * Final_Project_HR_Web_App
 * ro.sit.hrapp.web  
 * RegisterController.java
 * 
 *
 * MADE FOR TRAINING PURPOSES.
 *
 */
package ro.sit.hrapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import ro.sit.hrapp.domain.Candidate;
import ro.sit.hrapp.domain.Company;
import ro.sit.hrapp.service.CandidateService;
import ro.sit.hrapp.service.CompanyService;
import ro.sit.hrapp.service.validator.CandidateRegistrationValidator;
import ro.sit.hrapp.service.validator.CompanyRegistrationValidator;

/**
 * @author Sorin_Dragan
 *
 */

@Controller
@RequestMapping("/spring")
public class RegisterController {
	
	//common
	@RequestMapping(value = "registrationPage")
	public ModelAndView renerCandidateRegisterPage () throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("title", "Register Page"); 
		return modelAndView;
	}
	
	// candidate related
	@Autowired
	CandidateRegistrationValidator candidateValidator;

	@Autowired
	CandidateService candidateService;
	
	@RequestMapping(value = "registerCandidate", method = RequestMethod.GET)
	public ModelAndView renderCandidateRegistrationPage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("title", "Register Candidate");
		modelAndView.addObject(new Candidate());
		return modelAndView;
	}

	@RequestMapping(value = "registerCandidateSuccess")
	public ModelAndView renderCandidateSuccessfullRegistrationPage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("user", getPrincipal());
		modelAndView.addObject("title", "Registration Successfull");
		return modelAndView;
	}

	@RequestMapping(value = "registerCandidate", method = RequestMethod.POST)
	public ModelAndView registerCandidate(Candidate candidate, BindingResult bindingResult) {

		ModelAndView modelAndView = new ModelAndView();
		candidateValidator.validate(candidate, bindingResult);

		boolean hasErros = false;
		if (!bindingResult.hasErrors()) {
			candidateService.saveCandidate(candidate);
			modelAndView.setView(new RedirectView("registerCandidateSuccess"));
		} else {
			hasErros = true;
		}
		if (hasErros) {
			modelAndView = new ModelAndView("/spring/registerCandidate");
			modelAndView.addObject("candidate", candidate);
			modelAndView.addObject("errors", bindingResult.getAllErrors());
		}
		return modelAndView;
	}
	
	// company related
	@Autowired
	CompanyRegistrationValidator companyValidator;

	@Autowired
	CompanyService companyService;

	@RequestMapping(value = "registerCompany", method = RequestMethod.GET)
	public ModelAndView renderRegistrationPage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("title", "Register Company");
		modelAndView.addObject(new Company());
		return modelAndView;
	}

	@RequestMapping("registerCompanySuccess")
	public ModelAndView renderSuccessfullRegistrationPage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("user", getPrincipal());
		modelAndView.addObject("title", "Registration Successfull");
		return modelAndView;
	}

	@RequestMapping(value = "registerCompany", method = RequestMethod.POST)
	public ModelAndView registerCompany(Company company, BindingResult bindingResult) {

		ModelAndView modelAndView = new ModelAndView();
		companyValidator.validate(company, bindingResult);

		boolean hasErros = false;
		if (!bindingResult.hasErrors()) {
			companyService.saveCompany(company);
			modelAndView.setView(new RedirectView("registerCompanySuccess"));
		} else {
			hasErros = true;
		}
		if (hasErros) {
			modelAndView = new ModelAndView("/spring/registerCompany");
			modelAndView.addObject("company", company);
			modelAndView.addObject("errors", bindingResult.getAllErrors());
		}
		return modelAndView;
	}
	
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
