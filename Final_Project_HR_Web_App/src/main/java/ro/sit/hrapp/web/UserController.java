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

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import ro.sit.hrapp.domain.Candidate;
import ro.sit.hrapp.domain.Company;
import ro.sit.hrapp.domain.JobDescription;
import ro.sit.hrapp.service.CandidateService;
import ro.sit.hrapp.service.CompanyJobDescriptionService;
import ro.sit.hrapp.service.CompanyService;
import ro.sit.hrapp.service.CandidateJobDescriptionService;

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
	
	@Autowired
	private CandidateJobDescriptionService candidateJobDescriptionService;

	@Autowired
	private CompanyJobDescriptionService companyJobDescriptionService;
	
	
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
	public ModelAndView saveCandidateChanges(Candidate candidate, BindingResult bindingResult) {

		ModelAndView modelAndView = new ModelAndView();

		boolean hasErros = false;
		if (!bindingResult.hasErrors()) {
			candidateService.saveCandidate(candidate);
			modelAndView.setView(new RedirectView("/spring/details"));
		} else {
			hasErros = true;
		}
		if (hasErros) {
			modelAndView = new ModelAndView("/spring/editCandidate");
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
	public ModelAndView saveCompanyChanges(Company company, BindingResult bindingResult) {
		
		ModelAndView modelAndView = new ModelAndView();
		
		boolean hasErros = false;
		if (!bindingResult.hasErrors()) {
			companyService.saveCompany(company);
			modelAndView.setView(new RedirectView("/spring/details"));
		} else {
			hasErros = true;
		}
		if (hasErros) {
			modelAndView = new ModelAndView("/spring/editCompany");
			modelAndView.addObject("company", company);
			modelAndView.addObject("errors", bindingResult.getAllErrors());
		}
		return modelAndView;
	}
	
	//candidate job description related
	@RequestMapping(value = "saveCandidateJobDescription", method = RequestMethod.POST)
	public ModelAndView saveCandidateJobDescription(@Valid JobDescription jobDescription, BindingResult bindingResult) {
		
		ModelAndView modelAndView = new ModelAndView();
		
		boolean hasErros = false;
		if (!bindingResult.hasErrors()) {
			candidateJobDescriptionService.saveJobDescription(jobDescription);
			modelAndView.setView(new RedirectView("/spring/details"));
		} else {
			hasErros = true;
		}
		if (hasErros) {
			modelAndView = new ModelAndView("/spring/addCandidateJobDescription");
			modelAndView.addObject("candidateJobDescription", jobDescription);
			modelAndView.addObject("errors", bindingResult.getAllErrors());
		}
		return modelAndView;
	}
	
	@RequestMapping("addCandidateJobDescription")
	public ModelAndView renderAddCandidateJobDescription (long id) {
		ModelAndView modelAndView = new ModelAndView("/spring/addCandidateJobDescription");
		modelAndView.addObject("candidateJobDescription", candidateJobDescriptionService.get(id)); 
		return modelAndView;
	}
	
	@RequestMapping("editCandidateJobDescription")
	public ModelAndView renderEditCandidateJobDescription (long id) {
		ModelAndView modelAndView = new ModelAndView("/spring/editCandidateJobDescription");
		modelAndView.addObject("candidateJobDescription", candidateJobDescriptionService.get(id)); 
		return modelAndView;
	}
	
	//company job description related
	@RequestMapping(value = "saveCompanyJobDescription", method = RequestMethod.POST)
	public ModelAndView saveCompanyJobDescription(@Valid JobDescription jobDescription, BindingResult bindingResult) {
		
		ModelAndView modelAndView = new ModelAndView();
		
		boolean hasErros = false;
		if (!bindingResult.hasErrors()) {
			companyJobDescriptionService.saveJobDescription(jobDescription);
			modelAndView.setView(new RedirectView("/spring/details"));
		} else {
			hasErros = true;
		}
		if (hasErros) {
			modelAndView = new ModelAndView("/spring/addCompanyJobDescription");
			modelAndView.addObject("companyJobDescription", jobDescription);
			modelAndView.addObject("errors", bindingResult.getAllErrors());
		}
		return modelAndView;
	}
	
	@RequestMapping("addCompanyJobDescription")
	public ModelAndView renderAddCompanyJobDescription (long id) {
		ModelAndView modelAndView = new ModelAndView("/spring/addCompanyJobDescription");
		modelAndView.addObject("companyJobDescription", companyJobDescriptionService.get(id)); 
		return modelAndView;
	}
	
	@RequestMapping("editCompanyJobDescription")
	public ModelAndView renderEditCompanyJobDescription (long id) {
		ModelAndView modelAndView = new ModelAndView("/spring/editCompanyJobDescription");
		modelAndView.addObject("companyJobDescription", companyJobDescriptionService.get(id)); 
		return modelAndView;
	}
	

}
