package org.dselent.scheduling.server.httpReturnObject;

import org.dselent.scheduling.server.model.Model;
import org.dselent.scheduling.server.model.custom.UserInfo;

// Currently intended to convert a regular UserInfo model into a special return object
// Strips out information not intended to send to the client (encrypted password, salt, etc...)
public class ReturnUserInfo extends Model
{
	
	private Integer usersId;
	private String usersWpiId;
	private String usersUserName;
	private String usersFirstName;
	private String usersLastName;
	private String usersEmail;
	private String usersAccountState;
	private Long usersCreatedAt;
	private Long usersUpdatedAt;

	// faculty
	
	private Integer facultyId;
	
	// user_roles
	
	private Integer userRolesId;
	private String userRolesRoleName;


	public ReturnUserInfo(UserInfo userInfo)
	{
		usersId = userInfo.getUsersId();
		usersWpiId = userInfo.getUsersWpiId();
		usersUserName = userInfo.getUsersUserName();
		usersFirstName = userInfo.getUsersFirstName();
		usersLastName = userInfo.getUsersLastName();
		usersEmail = userInfo.getUsersEmail();
		usersAccountState = userInfo.getUsersAccountState();
		usersCreatedAt = userInfo.getUsersCreatedAt().getEpochSecond();
		usersUpdatedAt = userInfo.getUsersUpdatedAt().getEpochSecond();

		// faculty
		
		facultyId = userInfo.getFacultyId();
		
		// user_roles
		
		userRolesId = userInfo.getUserRolesId();
		userRolesRoleName = userInfo.getUserRolesRoleName();
	}


	public Integer getUsersId()
	{
		return usersId;
	}


	public String getUsersWpiId()
	{
		return usersWpiId;
	}


	public String getUsersUserName()
	{
		return usersUserName;
	}


	public String getUsersFirstName()
	{
		return usersFirstName;
	}


	public String getUsersLastName()
	{
		return usersLastName;
	}


	public String getUsersEmail()
	{
		return usersEmail;
	}


	public String getUsersAccountState()
	{
		return usersAccountState;
	}


	public Long getUsersCreatedAt()
	{
		return usersCreatedAt;
	}


	public Long getUsersUpdatedAt()
	{
		return usersUpdatedAt;
	}


	public Integer getFacultyId()
	{
		return facultyId;
	}


	public Integer getUserRolesId()
	{
		return userRolesId;
	}


	public String getUserRolesRoleName()
	{
		return userRolesRoleName;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((facultyId == null) ? 0 : facultyId.hashCode());
		result = prime * result + ((userRolesId == null) ? 0 : userRolesId.hashCode());
		result = prime * result + ((userRolesRoleName == null) ? 0 : userRolesRoleName.hashCode());
		result = prime * result + ((usersAccountState == null) ? 0 : usersAccountState.hashCode());
		result = prime * result + ((usersCreatedAt == null) ? 0 : usersCreatedAt.hashCode());
		result = prime * result + ((usersEmail == null) ? 0 : usersEmail.hashCode());
		result = prime * result + ((usersFirstName == null) ? 0 : usersFirstName.hashCode());
		result = prime * result + ((usersId == null) ? 0 : usersId.hashCode());
		result = prime * result + ((usersLastName == null) ? 0 : usersLastName.hashCode());
		result = prime * result + ((usersUpdatedAt == null) ? 0 : usersUpdatedAt.hashCode());
		result = prime * result + ((usersUserName == null) ? 0 : usersUserName.hashCode());
		result = prime * result + ((usersWpiId == null) ? 0 : usersWpiId.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof ReturnUserInfo)) {
			return false;
		}
		ReturnUserInfo other = (ReturnUserInfo) obj;
		if (facultyId == null) {
			if (other.facultyId != null) {
				return false;
			}
		} else if (!facultyId.equals(other.facultyId)) {
			return false;
		}
		if (userRolesId == null) {
			if (other.userRolesId != null) {
				return false;
			}
		} else if (!userRolesId.equals(other.userRolesId)) {
			return false;
		}
		if (userRolesRoleName == null) {
			if (other.userRolesRoleName != null) {
				return false;
			}
		} else if (!userRolesRoleName.equals(other.userRolesRoleName)) {
			return false;
		}
		if (usersAccountState == null) {
			if (other.usersAccountState != null) {
				return false;
			}
		} else if (!usersAccountState.equals(other.usersAccountState)) {
			return false;
		}
		if (usersCreatedAt == null) {
			if (other.usersCreatedAt != null) {
				return false;
			}
		} else if (!usersCreatedAt.equals(other.usersCreatedAt)) {
			return false;
		}
		if (usersEmail == null) {
			if (other.usersEmail != null) {
				return false;
			}
		} else if (!usersEmail.equals(other.usersEmail)) {
			return false;
		}
		if (usersFirstName == null) {
			if (other.usersFirstName != null) {
				return false;
			}
		} else if (!usersFirstName.equals(other.usersFirstName)) {
			return false;
		}
		if (usersId == null) {
			if (other.usersId != null) {
				return false;
			}
		} else if (!usersId.equals(other.usersId)) {
			return false;
		}
		if (usersLastName == null) {
			if (other.usersLastName != null) {
				return false;
			}
		} else if (!usersLastName.equals(other.usersLastName)) {
			return false;
		}
		if (usersUpdatedAt == null) {
			if (other.usersUpdatedAt != null) {
				return false;
			}
		} else if (!usersUpdatedAt.equals(other.usersUpdatedAt)) {
			return false;
		}
		if (usersUserName == null) {
			if (other.usersUserName != null) {
				return false;
			}
		} else if (!usersUserName.equals(other.usersUserName)) {
			return false;
		}
		if (usersWpiId == null) {
			if (other.usersWpiId != null) {
				return false;
			}
		} else if (!usersWpiId.equals(other.usersWpiId)) {
			return false;
		}
		return true;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ReturnUserInfo [usersId=");
		builder.append(usersId);
		builder.append(", usersWpiId=");
		builder.append(usersWpiId);
		builder.append(", usersUserName=");
		builder.append(usersUserName);
		builder.append(", usersFirstName=");
		builder.append(usersFirstName);
		builder.append(", usersLastName=");
		builder.append(usersLastName);
		builder.append(", usersEmail=");
		builder.append(usersEmail);
		builder.append(", usersAccountState=");
		builder.append(usersAccountState);
		builder.append(", usersCreatedAt=");
		builder.append(usersCreatedAt);
		builder.append(", usersUpdatedAt=");
		builder.append(usersUpdatedAt);
		builder.append(", facultyId=");
		builder.append(facultyId);
		builder.append(", userRolesId=");
		builder.append(userRolesId);
		builder.append(", userRolesRoleName=");
		builder.append(userRolesRoleName);
		builder.append("]");
		return builder.toString();
	}
	
	
}
