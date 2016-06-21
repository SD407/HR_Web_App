/*
 * HRSpringMVC
 * ro.sit.hrapp.domain  
 * A.java
 * 
 *
 * MADE FOR TRAINING PURPOSES.
 *
 */
package ro.sit.hrapp.domain;

public class JobDescription extends AbstractModel {

	private CurrentJobTitle currentJob = null;
	private YearsOfExperience yearsOfExperience = null;
	private Location prefferedLocation = null;
	private ProfessionalSkills professionalSkills = null;
	private PersonalSkills personalSkills = null;

	public enum YearsOfExperience {
		ZERO_TO_ONE(0, 1), ONE_TO_THREE(1, 3), MORE_THAN_THREE(3, 6);

		private int startYear;
		private int endYear;

		YearsOfExperience(int startYear, int endYear) {
			this.startYear = startYear;
			this.endYear = endYear;
		}

	}

	public enum CurrentJobTitle {
		BA, PM, UI_UX, JAVA;
	}

	public enum Location {
		CLUJ_NAPOCA, BUCURESTI;
	}

	public enum ProfessionalSkills {
		JAVA, JDBC, SPRING;
	}

	public enum PersonalSkills {
		TEAM_PLAYER, GOOD_LISTENER, GOOD_COMMNUNICATOR;
	}

	public CurrentJobTitle getCurrentJob() {
		return currentJob;
	}

	public Location getPrefferedLocation() {
		return prefferedLocation;
	}

	public ProfessionalSkills getProfessionalSkills() {
		return professionalSkills;
	}

	public PersonalSkills getPersonalSkills() {
		return personalSkills;
	}

	public YearsOfExperience getYearsOfExperience() {
		return yearsOfExperience;
	}

	public void setCurrentJob(CurrentJobTitle currentJob) {
		this.currentJob = currentJob;
	}

	public void setYearsOfExperience(YearsOfExperience yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}

	public void setPrefferedLocation(Location prefferedLocation) {
		this.prefferedLocation = prefferedLocation;
	}

	public void setProfessionalSkills(ProfessionalSkills professionalSkills) {
		this.professionalSkills = professionalSkills;
	}

	public void setPersonalSkills(PersonalSkills personalSkills) {
		this.personalSkills = personalSkills;
	}
	
	

}
