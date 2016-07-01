/*
 * Final_Project_HR_Web_App
 * ro.sit.hrapp.web  
 * UserController.java
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

/**
 * @author Sorin_Dragan
 *
 */
@Controller
@RequestMapping("/spring")
public class UserController {
	
	@Autowired
	private CandidateService candidateService;
	
	@Autowired
	private CompanyService companyService;
	
	
	//candidate related
	@RequestMapping("editCandidate")
	public ModelAndView renderEditCandidate (long id) {
		ModelAndView modelAndView = new ModelAndView("/spring/editCandidate");
		modelAndView.addObject("candidate", candidateService.get(id)); 
		return modelAndView;
	}
	
	@RequestMapping("deleteCandidate")
	public ModelAndView renderDeleteCandidate(long id) { 
		ModelAndView modelAndView = new ModelAndView();;
		candidateService.deleteCandidate(id);
		modelAndView.setView(new RedirectView("/spring/details"));
		return modelAndView;
	}
	
	@RequestMapping(value = "saveCandidateChanges", method = RequestMethod.POST)
	public ModelAndView registerSaveCandidateChanges(Candidate candidate, BindingResult bindingResult) {

		ModelAndView modelAndView = new ModelAndView();

		boolean hasErros = false;
		if (!bindingResult.hasErrors()) {
			candidateService.saveCandidate(candidate);
			modelAndView.setView(new RedirectView("/spring/details"));
		} else {
			hasErros = true;
		}
		if (hasErros) {
			modelAndView = new ModelAndView("/spring/saveCandidateChanges");
			modelAndView.addObject("candidate", candidate);
			modelAndView.addObject("errors", bindingResult.getAllErrors());
		}
		return modelAndView;
	}
	
	//company related
	@RequestMapping("editCompany")
	public ModelAndView renderEditCompany (long id) {
		ModelAndView modelAndView = new ModelAndView("/spring/editCompany");
		modelAndView.addObject("company", companyService.get(id)); 
		return modelAndView;
	}
	
	@RequestMapping("deleteCompany")
	public ModelAndView renderDeleteCompany(long id) { 
		ModelAndView modelAndView = new ModelAndView();;
		companyService.deleteCompany(id);
		modelAndView.setView(new RedirectView("/spring/details"));
		return modelAndView;
	}
	
	@RequestMapping(value = "saveCompanyChanges", method = RequestMethod.POST)
	public ModelAndView registerSaveCompanyChanges(Company company, BindingResult bindingResult) {
		
		ModelAndView modelAndView = new ModelAndView();
		
		boolean hasErros = false;
		if (!bindingResult.hasErrors()) {
			companyService.saveCompany(company);
			modelAndView.setView(new RedirectView("/spring/details"));
		} else {
			hasErros = true;
		}
		if (hasErros) {
			modelAndView = new ModelAndView("/spring/saveCompanyChanges");
			modelAndView.addObject("company", company);
			modelAndView.addObject("errors", bindingResult.getAllErrors());
		}
		return modelAndView;
	}

}
