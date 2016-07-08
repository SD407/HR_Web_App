/*
 * HRSpringMVC
 * ro.sit.hrapp.domain  
 * JobDescription.java
 * 
 *
 * MADE FOR TRAINING PURPOSES.
 *
 */
package ro.sit.hrapp.domain;

import java.util.List;

public class JobDescription extends AbstractModel{

	private String userName;
	private String currentJobTitle;
	private String yearOfExperience;
	private String location;
	private List<String> professionalSkills;
	private List<String> personalSkills;
	
	/**
	 * @return the userName to get
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
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
	public String getYearOfExperience() {
		return yearOfExperience;
	}
	/**
	 * @param yearOfExperience the yearOfExperience to set
	 */
	public void setYearOfExperience(String yearOfExperience) {
		this.yearOfExperience = yearOfExperience;
	}
	/**
	 * @return the location to get
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * @return the professionalSkills to get
	 */
	public List<String> getProfessionalSkills() {
		return professionalSkills;
	}
	/**
	 * @param professionalSkills the professionalSkills to set
	 */
	public void setProfessionalSkills(List<String> professionalSkills) {
		this.professionalSkills = professionalSkills;
	}
	/**
	 * @return the personalSkills to get
	 */
	public List<String> getPersonalSkills() {
		return personalSkills;
	}
	/**
	 * @param personalSkills the personalSkills to set
	 */
	public void setPersonalSkills(List<String> personalSkills) {
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
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((personalSkills == null) ? 0 : personalSkills.hashCode());
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
		if (currentJobTitle == null) {
			if (other.currentJobTitle != null)
				return false;
		} else if (!currentJobTitle.equals(other.currentJobTitle))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (personalSkills == null) {
			if (other.personalSkills != null)
				return false;
		} else if (!personalSkills.equals(other.personalSkills))
			return false;
		if (professionalSkills == null) {
			if (other.professionalSkills != null)
				return false;
		} else if (!professionalSkills.equals(other.professionalSkills))
			return false;
		if (yearOfExperience == null) {
			if (other.yearOfExperience != null)
				return false;
		} else if (!yearOfExperience.equals(other.yearOfExperience))
			return false;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "JobDescription [userName=" + userName + ", currentJobTitle=" + currentJobTitle + ", yearOfExperience="
				+ yearOfExperience + ", location=" + location + ", professionalSkills=" + professionalSkills
				+ ", personalSkills=" + personalSkills + "]";
	}
	

}
