package org.dselent.scheduling.server.httpReturnObject;



public class UserFaculty {
	// attributes	
	private Integer id;
	private String wpiId;
	private String userName;
	private String firstName;
	private String lastName;
	private String email;
	//private String salt;
	private String accountState;
	private Boolean deleted;
	private Integer facultyId;
	private Integer rank;

	// methods

	public Integer getId()
	{
		return id;
	}

	public UserFaculty(int id, String wpiId, String userName, String firstName, String lastName, String email,
			String accountState, Boolean deleted, int facultyId, int rank) {
		super();
		this.id = id;
		this.wpiId = wpiId;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		//this.salt = salt;
		this.accountState = accountState;
		this.deleted = deleted;
		this.facultyId = facultyId;
		this.rank = rank;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * @return the wpiId
	 */
	public String getWpiId() {
		return wpiId;
	}

	/**
	 * @param wpiId the wpiId to set
	 */
	public void setWpiId(String wpiId) {
		this.wpiId = wpiId;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}


	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}


	

	public String getAccountState()
	{
		return accountState;
	}

	public void setAccountState(String accountState)
	{
		this.accountState = accountState;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}



	/**
	 * @return the facultyId
	 */
	public Integer getFacultyId() {
		return facultyId;
	}

	/**
	 * @param facultyId the facultyId to set
	 */
	public void setFacultyId(Integer facultyId) {
		this.facultyId = facultyId;
	}

	/**
	 * @return the rank
	 */
	public Integer getRank() {
		return rank;
	}

	/**
	 * @param rank the rank to set
	 */
	public void setRank(Integer rank) {
		this.rank = rank;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountState == null) ? 0 : accountState.hashCode());
		result = prime * result + ((deleted == null) ? 0 : deleted.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((facultyId == null) ? 0 : facultyId.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((rank == null) ? 0 : rank.hashCode());
		//result = prime * result + ((salt == null) ? 0 : salt.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		result = prime * result + ((wpiId == null) ? 0 : wpiId.hashCode());
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
		UserFaculty other = (UserFaculty) obj;
		if (accountState == null) {
			if (other.accountState != null)
				return false;
		} else if (!accountState.equals(other.accountState))
			return false;
		
		if (deleted == null) {
			if (other.deleted != null)
				return false;
		} else if (!deleted.equals(other.deleted))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
	
		if (facultyId == null) {
			if (other.facultyId != null)
				return false;
		} else if (!facultyId.equals(other.facultyId))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (rank == null) {
			if (other.rank != null)
				return false;
		} else if (!rank.equals(other.rank))
			return false;
		
		
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		if (wpiId == null) {
			if (other.wpiId != null)
				return false;
		} else if (!wpiId.equals(other.wpiId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserFaculty [id=" + id + ", wpiId=" + wpiId + ", userName=" + userName + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email +  ", salt="
				 + accountState + ", deleted=" + deleted +  ", facultyId=" + facultyId + ", rank=" + rank + "]";
	}


}
