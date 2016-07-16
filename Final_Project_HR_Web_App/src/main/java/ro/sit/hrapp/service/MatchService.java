package ro.sit.hrapp.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

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


/**
 * Se cauta potriviri dupa 5 criterii(locatie,perskils,proskils,exp,jobTitle)- cautare 
 * individuala. La fiecare cautare pozitiva (adica exista potrivire), id ul respectiv de
 * candidat se incarca intr-un list.Pentru procentul de potrivire global
 *  la fiecare am pus 20 % procent de participare . iar la criteriile unde am liste
 *  (persSkills si proSkills) acolo procentul de 20 s-a impartit la nr de skiluri, care daca se
 *  potrivesc se aduna si dau 20 %. Procentul de potrivire global se inmagazineaza intr-un field
 *  al candidatului acolo am o metoda addMatch(care acumuleaza procentele) si cand am nevoie mi le reda
 *   cu getMatch() pt fiecare candidat
 *   
 *  
 *   */
public class MatchService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MatchService.class);

	// liste de id-uri de candidati care se potrivesc cu jd companiei
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

	/**metoda cauta compatibilitate intre o companie si o lista de candidati*/
	public List<Long> searchMatchForCompany(Company company, List<Candidate> listAllCandidate) {

		/**aici sunt 5 metode care fiecare cauta potriviri de id
		 * in aceste metode se incarca listele cu id-uri de candidati*/
		matchJobLocation(company, listAllCandidate); 
		matchJobTitle(company, listAllCandidate); 
		matchExperience(company, listAllCandidate); 
		matchProfesionalSkills(company, listAllCandidate); 
		matchPersonalSkills(company, listAllCandidate); 

		if (jobTitles.isEmpty()) {
			// aici ar trebui sa dau o conditie  ca daca numele jobului nu se potriveste 
			 // sa nu se faca match , adica sa nu avem inconsistenta : sa nu potrivesca jobul
			// dar din greseala sa se potriveasca altceva ---???
		} else {
			// se retin id uri comune
			locations.retainAll(experiences);
			experiences.retainAll(proSkills);
			proSkills.retainAll(persSkills);
			persSkills.retainAll(jobTitles);
		}
		return jobTitles;

	}

	/**metoda care potriveste dupa locatie si incarca lista corespunzatoare
	 si incarca si un procent de potrivire*/
	private void matchJobLocation(Company company, List<Candidate> listAllCandidate) {

		for (int i = 0; i < listAllCandidate.size(); i++) {
			if (company.getJobDescription().getLocation()
					.equals(listAllCandidate.get(i).getJobDescription().getLocation())) {
				locations.add(listAllCandidate.get(i).getId());
				listAllCandidate.get(i).addMatch(20);
			}

		}

	}
	/**metoda care potriveste dupa skiluri personale si incarca lista corespunzatoare
	 si incarca si un procent de potrivire*/
	private void matchPersonalSkills(Company company, List<Candidate> listAllCandidate) {


		for (int i = 0; i < listAllCandidate.size(); i++) {

			List<PersonalSkills> cand = listAllCandidate.get(i).getJobDescription().getPersonalSkills();

			List<PersonalSkills> comp = company.getJobDescription().getPersonalSkills();

			Long candiId = listAllCandidate.get(i).getId(); // preluare fiecare id din lista

			float percent = 20 / comp.size();
			for (int z = 0; z < cand.size(); z++) {

				if (comp.contains(cand.get(z))) {
					
					persSkills.add(candiId); // adaugare id
					listAllCandidate.get(i).addMatch(percent); // adaugare procent

				}

			}

		}

	}

	/**metoda care potriveste dupa experienta si incarca lista corespunzatoare
	 si incarca si un procent de potrivire*/
	private void matchExperience(Company company, List<Candidate> listAllCandidate) {

		for (int i = 0; i < listAllCandidate.size(); i++) {

			YearsOfExperience cand = listAllCandidate.get(i).getJobDescription().getYearOfExperience();

			YearsOfExperience comp = company.getJobDescription().getYearOfExperience();

			if (comp == cand) {
				Long candiId = listAllCandidate.get(i).getId();
				experiences.add(candiId);
				listAllCandidate.get(i).addMatch(20);
			}

		}

	}

	/**metoda care potriveste dupa skiluri profesionale si incarca lista corespunzatoare
	 si incarca si un procent de potrivire*/
	private void matchProfesionalSkills(Company company, List<Candidate> listAllCandidate) {

		for (int i = 0; i < listAllCandidate.size(); i++) {

			List<ProfessionalSkills> cand = listAllCandidate.get(i).getJobDescription().getProfessionalSkills();

			List<ProfessionalSkills> comp = company.getJobDescription().getProfessionalSkills();

			Long candiId = listAllCandidate.get(i).getId();

			float percent = 20 / comp.size();

			for (int z = 0; z < cand.size(); z++) {
				
				if (comp.contains(cand.get(z))) {
					
					proSkills.add(candiId);
					listAllCandidate.get(i).addMatch(percent);

				}
			}

		}

	}

	/**metoda care potriveste dupa denumirea de job si incarca lista corespunzatoare
	 si incarca si un procent de potrivire*/
	private void matchJobTitle(Company company, List<Candidate> listAllCandidate) {

		for (int i = 0; i < listAllCandidate.size(); i++) {

			CurrentJobTitle cand = listAllCandidate.get(i).getJobDescription().getCurrentJobTitle();

			CurrentJobTitle comp = company.getJobDescription().getCurrentJobTitle();

			if (comp == cand) {
				Long candiId = listAllCandidate.get(i).getId();
				jobTitles.add(candiId);
				listAllCandidate.get(i).addMatch(20);
			}

		}

	}

	// metoda care reda dintr-o lista de procente , cel mai mare porcent 
	public float highestMatch(List<Float> per) {

		float[] percents = new float[per.size()];
		for (int i = 0; i < per.size(); i++) {
			percents[i] = per.get(i);
		}
		float max = percents[0];
		for (int i = 0; i < percents.length; i++) {
			if (max < percents[i]) {
				max = percents[i];
			}
		}
		return max;
	}

	// metoda care cauta candidati dupa un anumit percent
	public Candidate CandidateHighestMatch(float percent, List<Candidate> list) {
		Candidate cand = new Candidate();
		for (int i = 0; i < list.size(); i++) {
			if (percent == list.get(i).getMatch()) {
				cand = list.get(i);
			}
		}
		return cand;
	}

	// metoda care pe baza a doua liste(procente si candidati) creaza o lista comuna(un map)
	// unde key este potrivirea si value este candidatul - asa e usor de regasit un candidat 
	// dupa potrivire
	public Map<Float, Candidate> listMatchPercent(List<Float> percent, List<Candidate> list) {
		Map<Float, Candidate> candList = new HashMap<>();
		for (int i = 0; i < list.size(); i++) {
			candList.put(percent.get(i), list.get(i));
		}

		return candList;

	}

	//acesta meotda creaza un map cu candidatii care au un porcent de potrivire maimare decat
	// un porcent dat Ex. sa obtinem o lista cu candidatii care au peste 70 % potrivire cu
	//jd companiei - repectivii canidati se incarca in map ul respectiv unde key -ul va fi un numar
	//1,2,3 un fel de numar de ordine si value va fi candidatul - aici asi vrea sa ordonez sa le afiseze
	//de exemplu descrescator dar inca nu stiu cum sa fac ! 
	public Map<Integer, Candidate> listCandidateWithMatch(float f, Map<Float, Candidate> relativ) {

		Map<Integer, Candidate> newList = new TreeMap<>();
		Iterator<Entry<Float, Candidate>> it1 = relativ.entrySet().iterator();
		int p = 0;
		while (it1.hasNext()) {
			float local = it1.next().getKey();
			if (local > f) {
				newList.put(new Integer(p), relativ.get(local));
			}
			p++;
		}
		return newList;
	}

}
