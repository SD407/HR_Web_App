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

import ro.sit.hrapp.domain.JobDescription;

public class JobDescription {
	
	private String currentJobTitle;
	private int yearOfExperience;
	private String prefferedLocation;
	private String professionalSkills;
	private String personalSkills;
	
	/**
	 * @return the currentJobTitle to get
	 */
	public String getCurrentJobTitle() {
		return currentJobTitle;
	}
	
	/**
	 * @param currentJobTitle the currentJobTitle to set
	 */
	public void setCurrentJobTitle(String currentJobTitle) {
		this.currentJobTitle = currentJobTitle;
	}
	
	/**
	 * @return the yearOfExperience to get
	 */
	public int getYearOfExperience() {
		return yearOfExperience;
	}
	
	/**
	 * @param yearOfExperience the yearOfExperience to set
	 */
	public void setYearOfExperience(int yearOfExperience) {
		this.yearOfExperience = yearOfExperience;
	}
	
	/**
	 * @return the prefferedLocation to get
	 */
	public String getPrefferedLocation() {
		return prefferedLocation;
	}
	
	/**
	 * @param prefferedLocation the prefferedLocation to set
	 */
	public void setPrefferedLocation(String prefferedLocation) {
		this.prefferedLocation = prefferedLocation;
	}
	
	/**
	 * @return the professionalSkills to get
	 */
	public String getProfessionalSkills() {
		return professionalSkills;
	}
	
	/**
	 * @param professionalSkills the professionalSkills to set
	 */
	public void setProfessionalSkills(String professionalSkills) {
		this.professionalSkills = professionalSkills;
	}
	
	/**
	 * @return the personalSkills to get
	 */
	public String getPersonalSkills() {
		return personalSkills;
	}
	
	/**
	 * @param personalSkills the personalSkills to set
	 */
	public void setPersonalSkills(String personalSkills) {
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
		result = prime * result + yearOfExperience;
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
		if (currentJobTitle == null) {
			if (other.currentJobTitle != null)
				return false;
		} else if (!currentJobTitle.equals(other.currentJobTitle))
			return false;
		if (personalSkills == null) {
			if (other.personalSkills != null)
				return false;
		} else if (!personalSkills.equals(other.personalSkills))
			return false;
		if (prefferedLocation == null) {
			if (other.prefferedLocation != null)
				return false;
		} else if (!prefferedLocation.equals(other.prefferedLocation))
			return false;
		if (professionalSkills == null) {
			if (other.professionalSkills != null)
				return false;
		} else if (!professionalSkills.equals(other.professionalSkills))
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
		return "JobProfile [currentJobTitle=" + currentJobTitle + ", yearOfExperience=" + yearOfExperience
				+ ", prefferedLocation=" + prefferedLocation + ", professionalSkills=" + professionalSkills
				+ ", personalSkills=" + personalSkills + "]";
	}
	
	@SuppressWarnings("unused")
	private enum CurrentJobTitle {
		BA,PM, UI_UX, JAVA;
	}
	
	@SuppressWarnings("unused")
	private enum Location {
		CLUJ_NAPOCA, BUCURESTI;
	}
	
	@SuppressWarnings("unused")
	private enum ProfessionalSkills {
		JAVA, JDBC, SPRING;
	}
	
	@SuppressWarnings("unused")
	private enum PersonalSkills {
		TEAM_PLAYER, GOOD_LISTENER, GOOD_COMMNUNICATOR;
	}
	
}
