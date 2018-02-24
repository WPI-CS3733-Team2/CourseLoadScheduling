package org.dselent.scheduling.server.httpReturnObject;

import java.util.Date;
import java.util.List;

public class UserWithScheduleInfo {
	// attributes	
	private Integer id;
	private String wpiId;
	private String userName;
	private String firstName;
	private String lastName;
	private String email;
	private Integer accountState;
	private Date createdAt;
	private Date updatedAt;
	private List<CourseInfo> coursesAssigned;

	// methods

	public UserWithScheduleInfo(Integer id, String wpiId, String userName, String firstName, String lastName,
			String email, Integer accountState, Date createdAt, Date updatedAt, List<CourseInfo> coursesAssigned) {
		super();
		this.id = id;
		this.wpiId = wpiId;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.accountState = accountState;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.coursesAssigned = coursesAssigned;
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getWpiId() {
		return wpiId;
	}

	public void setWpiId(String wpiId) {
		this.wpiId = wpiId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<CourseInfo> getCoursesAssigned() {
		return coursesAssigned;
	}

	public void setCoursesAssigned(List<CourseInfo> coursesAssigned) {
		this.coursesAssigned = coursesAssigned;
	}

	public Integer getAccountState() {
		return accountState;
	}

	public void setAccountState(Integer accountState) {
		this.accountState = accountState;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountState == null) ? 0 : accountState.hashCode());
		result = prime * result + ((coursesAssigned == null) ? 0 : coursesAssigned.hashCode());
		result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((updatedAt == null) ? 0 : updatedAt.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		result = prime * result + ((wpiId == null) ? 0 : wpiId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserWithScheduleInfo other = (UserWithScheduleInfo) obj;
		if (accountState == null) {
			if (other.accountState != null)
				return false;
		} else if (!accountState.equals(other.accountState))
			return false;
		if (coursesAssigned == null) {
			if (other.coursesAssigned != null)
				return false;
		} else if (!coursesAssigned.equals(other.coursesAssigned))
			return false;
		if (createdAt == null) {
			if (other.createdAt != null)
				return false;
		} else if (!createdAt.equals(other.createdAt))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
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
		if (updatedAt == null) {
			if (other.updatedAt != null)
				return false;
		} else if (!updatedAt.equals(other.updatedAt))
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

	@Override
	public String toString() {
		return "UserWithScheduleInfo [id=" + id + ", wpiId=" + wpiId + ", userName=" + userName + ", firstName="
				+ firstName + ", lastName=" + lastName + ", email=" + email + ", accountState=" + accountState
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", coursesAssigned=" + coursesAssigned
				+ "]";
	}

	
}
