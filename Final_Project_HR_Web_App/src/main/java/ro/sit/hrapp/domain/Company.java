/*
 * starter_app
 * ro.sci.ems.domain  
 * Company.java
 * 
 *
 * MADE FOR TRAINING PURPOSES.
 *
 */
package ro.sit.hrapp.domain;

import java.util.List;

/**
 * @author Sorin_Dragan
 *
 */
public class Company extends AbstractModel {
	
	private String companyName;
	private String userName;
	private String email;
	private String phoneNumber;
	private String password;
	private String passwordConfirmed;
	private String jobLocation;
	
	private List<JobDescription> jobDescription;

	/**
	 * @return the companyName to get
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

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
	 * @return the email to get
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the phone to get
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhoneNumber(String phone) {
		this.phoneNumber = phone;
	}

	/**
	 * @return the password to get
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the passwordConfirmed to get
	 */
	public String getPasswordConfirmed() {
		return passwordConfirmed;
	}

	/**
	 * @param passwordConfirmed the passwordConfirmed to set
	 */
	public void setPasswordConfirmed(String passwordConfirmed) {
		this.passwordConfirmed = passwordConfirmed;
	}

	/**
	 * @return the jobLocation to get
	 */
	public String getJobLocation() {
		return jobLocation;
	}

	/**
	 * @param jobLocation the jobLocation to set
	 */
	public void setJobLocation(String jobLocation) {
		this.jobLocation = jobLocation;
	}

	/**
	 * @return the jobProfile to get
	 */
	public List<JobDescription> getJobDescription() {
		return jobDescription;
	}

	/**
	 * @param jobProfile the jobProfile to set
	 */
	public void setJobDescription(List<JobDescription> jobProfile) {
		this.jobDescription = jobProfile;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((companyName == null) ? 0 : companyName.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((jobLocation == null) ? 0 : jobLocation.hashCode());
		result = prime * result + ((jobDescription == null) ? 0 : jobDescription.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((passwordConfirmed == null) ? 0 : passwordConfirmed.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
		Company other = (Company) obj;
		if (companyName == null) {
			if (other.companyName != null)
				return false;
		} else if (!companyName.equals(other.companyName))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (jobLocation == null) {
			if (other.jobLocation != null)
				return false;
		} else if (!jobLocation.equals(other.jobLocation))
			return false;
		if (jobDescription == null) {
			if (other.jobDescription != null)
				return false;
		} else if (!jobDescription.equals(other.jobDescription))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (passwordConfirmed == null) {
			if (other.passwordConfirmed != null)
				return false;
		} else if (!passwordConfirmed.equals(other.passwordConfirmed))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Company [companyName=" + companyName + ", userName=" + userName + ", email=" + email + ", phoneNumber="
				+ phoneNumber + ", jobDescription=" + jobDescription + "]";
	}
	
	
	
}
