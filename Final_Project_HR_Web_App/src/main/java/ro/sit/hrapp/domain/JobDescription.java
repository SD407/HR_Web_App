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
	
	private CurrentJobTitle currentJobTitle;
	private YearsOfExperience yearOfExperience;
	private PrefferedLocation prefferedLocation;
	private ProfessionalSkills professionalSkills;
	private PersonalSkills personalSkills;
	
	/**
	 * @return the currentJobTitle to get
	 */
	public CurrentJobTitle getCurrentJobTitle() {
		return currentJobTitle;
	}

	/**
	 * @param currentJobTitle the currentJobTitle to set
	 */
	public void setCurrentJobTitle(CurrentJobTitle currentJobTitle) {
		this.currentJobTitle = currentJobTitle;
	}

	/**
	 * @return the yearOfExperience to get
	 */
	public YearsOfExperience getYearOfExperience() {
		return yearOfExperience;
	}

	/**
	 * @param yearOfExperience the yearOfExperience to set
	 */
	public void setYearOfExperience(YearsOfExperience yearOfExperience) {
		this.yearOfExperience = yearOfExperience;
	}

	/**
	 * @return the prefferedLocation to get
	 */
	public PrefferedLocation getPrefferedLocation() {
		return prefferedLocation;
	}

	/**
	 * @param prefferedLocation the prefferedLocation to set
	 */
	public void setPrefferedLocation(PrefferedLocation prefferedLocation) {
		this.prefferedLocation = prefferedLocation;
	}

	/**
	 * @return the professionalSkills to get
	 */
	public ProfessionalSkills getProfessionalSkills() {
		return professionalSkills;
	}

	/**
	 * @param professionalSkills the professionalSkills to set
	 */
	public void setProfessionalSkills(ProfessionalSkills professionalSkills) {
		this.professionalSkills = professionalSkills;
	}

	/**
	 * @return the personalSkills to get
	 */
	public PersonalSkills getPersonalSkills() {
		return personalSkills;
	}

	/**
	 * @param personalSkills the personalSkills to set
	 */
	public void setPersonalSkills(PersonalSkills personalSkills) {
		this.personalSkills = personalSkills;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currentJobTitle == null) ? 0 : currentJobTitle.hashCode());
		result = prime * result + ((personalSkills == null) ? 0 : personalSkills.hashCode());
		result = prime * result + ((prefferedLocation == null) ? 0 : prefferedLocation.hashCode());
		result = prime * result + ((professionalSkills == null) ? 0 : professionalSkills.hashCode());
		result = prime * result + ((yearOfExperience == null) ? 0 : yearOfExperience.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JobDescription other = (JobDescription) obj;
		if (currentJobTitle != other.currentJobTitle)
			return false;
		if (personalSkills != other.personalSkills)
			return false;
		if (prefferedLocation != other.prefferedLocation)
			return false;
		if (professionalSkills != other.professionalSkills)
			return false;
		if (yearOfExperience != other.yearOfExperience)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "JobDescription [currentJobTitle=" + currentJobTitle + ", yearOfExperience=" + yearOfExperience
				+ ", prefferedLocation=" + prefferedLocation + ", professionalSkills=" + professionalSkills
				+ ", personalSkills=" + personalSkills + "]";
	}

	private enum CurrentJobTitle {
		BA,PM, UI_UX, JAVA;
	}
	
	private enum YearsOfExperience {
		ZERO_TO_ONE (0, 1),
		ONE_TO_THREE (0 ,3),
		THREE_TO_FIVE (3, 5),
		FIVE_TO_EIGHT (5, 8);
		
		private int startYear;
		private int endYear;
		
		YearsOfExperience(int startYear, int endYear) {
			this.startYear = startYear;
			this.endYear = endYear;
		}
		
		 private int returnStartYear() {
			 return startYear;
		 }
		 
		 private int returnEndYear() {
			 return endYear;
		 }
		 
	}
	
	private enum PrefferedLocation {
		CLUJ_NAPOCA, BUCURESTI;
	}
	
	private enum ProfessionalSkills {
		JAVA, JDBC, SPRING;
	}
	
	private enum PersonalSkills {
		TEAM_PLAYER, GOOD_LISTENER, GOOD_COMMNUNICATOR;
	}
	
}
