package ro.sit.hrapp.service;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ro.sit.hrapp.dao.CandidateDAO;
import ro.sit.hrapp.dao.CompanyDAO;
import ro.sit.hrapp.domain.Candidate;
import ro.sit.hrapp.domain.Company;
import ro.sit.hrapp.domain.JobDescription.CurrentJobTitle;
import ro.sit.hrapp.domain.JobDescription.PersonalSkills;
import ro.sit.hrapp.domain.JobDescription.ProfessionalSkills;
import ro.sit.hrapp.domain.JobDescription.YearsOfExperience;

public class MatchService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MatchService.class);

	int macthingPercent = 0;
	List<Long> locations = new LinkedList<>();
	List<Long> persSkills = new LinkedList<>();
	List<Long> proSkills = new LinkedList<>();
	List<Long> experiences = new LinkedList<>();
	List<Long> jobTitles = new LinkedList<>();

	@Autowired
	private CandidateDAO candidateDAO;

	@Autowired
	private CompanyDAO companyDAO;

	public Collection<Candidate> listAllCandidate() {
		return candidateDAO.getAllCandidates();
	}

	public Collection<Company> listAllCompanys() {
		return companyDAO.getAllCompanies();
	}

	// method for comparing a company with all candidates
	// input: a company ,output a list of matching candidates
	public List<Long> searchMatchForCompany(Company company, List<Candidate> listAllCandidate) {

		// create a list with candidate that match
		List<Long> matchingCandidateList = new LinkedList<>();

		// id-s for all candidates that have joblocation like company
		// joblocation
		matchJobLocation(company, listAllCandidate); // 3
		matchJobTitle(company, listAllCandidate); // 6
		matchExperience(company, listAllCandidate); // 5
		matchProfesionalSkills(company, listAllCandidate); // 6
		matchPersonalSkills(company, listAllCandidate); // 10

		// intai se verifica jobtitle,apoi
		// experienta,profesionalskiils,persskills,apoi locatia

		jobTitles.retainAll(experiences);
		experiences.retainAll(proSkills);
		proSkills.retainAll(persSkills);
		locations.retainAll(proSkills);

		return locations;

	}

	private void matchJobLocation(Company company, List<Candidate> listAllCandidate) {

		for (int i = 0; i < listAllCandidate.size(); i++) {
			if (company.getJobLocation().equals(listAllCandidate.get(i).getPreferedLocation())) {
				locations.add(listAllCandidate.get(i).getId());
				listAllCandidate.get(i).addMatch(20);
			}
			
		}

	}

	private void matchPersonalSkills(Company company, List<Candidate> listAllCandidate) {

		for (int i = 0; i < listAllCandidate.size(); i++) {

			List<PersonalSkills> cand = listAllCandidate.get(i).getJobDescription().getPersonalSkills();

			for (int j = 0; j < company.getJobDescription().size(); j++) {

				List<PersonalSkills> comp = company.getJobDescription().get(j).getPersonalSkills();

				Long candiId = listAllCandidate.get(i).getId();
				persSkills.add(candiId);
				for (int z = 0; z < cand.size(); z++) {
					if (comp.contains(cand.get(z))) {
						listAllCandidate.get(i).addMatch(5);

					}

				}

			}
		}

	}

	private void matchExperience(Company company, List<Candidate> listAllCandidate) {

		for (int i = 0; i < listAllCandidate.size(); i++) {

			YearsOfExperience cand = listAllCandidate.get(i).getJobDescription().getYearOfExperience();

			for (int j = 0; j < company.getJobDescription().size(); j++) {

				YearsOfExperience comp = company.getJobDescription().get(j).getYearOfExperience();

				if (comp == cand) {
					Long candiId = listAllCandidate.get(i).getId();
					experiences.add(candiId);
					listAllCandidate.get(i).addMatch(20);
				}

			}

		}

	}

	private void matchProfesionalSkills(Company company, List<Candidate> listAllCandidate) {

		for (int i = 0; i < listAllCandidate.size(); i++) {

			List<ProfessionalSkills> cand = listAllCandidate.get(i).getJobDescription().getProfessionalSkills();

			for (int j = 0; j < company.getJobDescription().size(); j++) {

				List<ProfessionalSkills> comp = company.getJobDescription().get(j).getProfessionalSkills();

				Long candiId = listAllCandidate.get(i).getId();
				proSkills.add(candiId);

				for (int z = 0; z < cand.size(); z++) {
					if (comp.contains(cand.get(z))) {
						listAllCandidate.get(i).addMatch(5);

					}
				}

			}

		}

	}

	private void matchJobTitle(Company company, List<Candidate> listAllCandidate) {

		for (int i = 0; i < listAllCandidate.size(); i++) {

			CurrentJobTitle cand = listAllCandidate.get(i).getJobDescription().getCurrentJobTitle();

			for (int j = 0; j < company.getJobDescription().size(); j++) {

				CurrentJobTitle comp = company.getJobDescription().get(j).getCurrentJobTitle();

				if (comp == cand) {
					Long candiId = listAllCandidate.get(i).getId();
					jobTitles.add(candiId);
					listAllCandidate.get(i).addMatch(20);
				}

			}

		}

	}
}
