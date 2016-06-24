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
@RequestMapping("/index")
public class RegisterController {
	
	//common
	@RequestMapping(value = "/register")
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

	@RequestMapping("registerCandidateSuccess")
	public ModelAndView renderCandidateSuccessfullRegistrationPage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("applicants", candidateService.listAll());
		modelAndView.addObject("title", "Registration Successfull");
		return modelAndView;
	}

	@RequestMapping(value = "registerCandidate", method = RequestMethod.POST)
	public ModelAndView registerCandidate(Candidate candidate, BindingResult bindingResult) {

		ModelAndView modelAndView = null;
		candidateValidator.validate(candidate, bindingResult);

		boolean hasErros = false;
		if (!bindingResult.hasErrors()) {
			modelAndView = new ModelAndView();
			candidateService.saveCandidate(candidate);
			modelAndView.setView(new RedirectView("registerCandidateSuccess"));
			hasErros = true;
		} else {
			hasErros = true;
		}
		if (hasErros) {
			modelAndView = new ModelAndView("/index/registerCandidate");
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
		modelAndView.addObject("applicants", companyService.listAll());
		modelAndView.addObject("title", "Registration Successfull");
		return modelAndView;
	}

	@RequestMapping(value = "registerCompany", method = RequestMethod.POST)
	public ModelAndView registerUser(Company company, BindingResult bindingResult) {

		ModelAndView modelAndView = null;
		companyValidator.validate(company, bindingResult);

		boolean hasErros = false;
		if (!bindingResult.hasErrors()) {
			modelAndView = new ModelAndView();
			companyService.saveCompany(company);
			modelAndView.setView(new RedirectView("registerCompanySuccess"));
			hasErros = true;
		} else {
			hasErros = true;
		}
		if (hasErros) {
			modelAndView = new ModelAndView("/index/registerCompany");
			modelAndView.addObject("company", company);
			modelAndView.addObject("errors", bindingResult.getAllErrors());
		}
		return modelAndView;
	}


}
