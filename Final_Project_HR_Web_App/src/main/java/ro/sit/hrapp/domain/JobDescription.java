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

public class JobDescription {

	public CurrentJobTitle currentJob = null;
	public YearsOfExperience yearsOfExperience = null;
	public Location prefferedLocation = null;
	ProfessionalSkills professionalSkills = null;
	PersonalSkills personalSkills = null;

	
	public JobDescription(CurrentJobTitle currentJob, YearsOfExperience yearOfExperience, Location prefferedLocation,
			ProfessionalSkills professionalSkills, PersonalSkills personalSkills) {
		super();
		this.currentJob = currentJob;
		this.yearsOfExperience = yearOfExperience;
		this.prefferedLocation = prefferedLocation;
		this.professionalSkills = professionalSkills;
		this.personalSkills = personalSkills;
	}

	 enum YearsOfExperience {
        ZERO_TO_ONE (0, 1);
        
        private int startYear;
        private int endYear;
        YearsOfExperience(int startYear, int endYear) {
            this.startYear = startYear;
            this.endYear = endYear;
        }
        
    }

	@SuppressWarnings("unused")
	enum CurrentJobTitle {
		BA, PM, UI_UX, JAVA;
	}

	@SuppressWarnings("unused")
	enum Location {
		CLUJ_NAPOCA, BUCURESTI;
	}

	@SuppressWarnings("unused")
	enum ProfessionalSkills {
		JAVA, JDBC, SPRING;
	}

	@SuppressWarnings("unused")
	enum PersonalSkills {
		TEAM_PLAYER, GOOD_LISTENER, GOOD_COMMNUNICATOR;
	}

}
