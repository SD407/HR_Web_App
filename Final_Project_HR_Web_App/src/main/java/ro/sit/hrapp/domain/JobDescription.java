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
	private Location location;
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
	 * @return the location to get
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(Location location) {
		this.location = location;
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
		result = prime * result + ((location == null) ? 0 : location.hashCode());
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
		if (location != other.location)
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
				+ ", prefferedLocation=" + location + ", professionalSkills=" + professionalSkills
				+ ", personalSkills=" + personalSkills + "]";
	}

	public enum CurrentJobTitle {
		BA,PM, UI_UX, JAVA;
	}
	
	public enum YearsOfExperience {
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
		
		 @SuppressWarnings("unused")
		private int returnStartYear() {
			 return startYear;
		 }
		 
		 @SuppressWarnings("unused")
		private int returnEndYear() {
			 return endYear;
		 }
		 
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
	
}
