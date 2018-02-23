package org.dselent.scheduling.server.model.custom;

import java.sql.JDBCType;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.model.Model;


public class UserInfo extends Model
{
	// column names
	public static enum Columns
	{
		// users
		USERS_ID,
		USERS_WPI_ID,
		USERS_USER_NAME,
		USERS_FIRST_NAME,
		USERS_LAST_NAME,
		USERS_EMAIL,
		USERS_ENCRYPTED_PASSWORD,
		USERS_SALT,
		USERS_ACCOUNT_STATE,
		USERS_CREATED_AT,
		USERS_UPDATED_AT,
		USERS_DELETED,

		// faculty
		FACULTY_ID,

		// user_roles
		USER_ROLES_ID,
		USER_ROLES_ROLE_NAME;

	}
		
	// enum list
	private static final List<Columns> COLUMN_LIST = new ArrayList<>();
	
	// type mapping
	private static final Map<Columns, JDBCType> COLUMN_TYPE_MAP = new HashMap<>();
	
	static
	{
		for(Columns key : Columns.values())
		{
			COLUMN_LIST.add(key);
		}
		
		// users
		
		COLUMN_TYPE_MAP.put(Columns.USERS_ID, JDBCType.INTEGER);
		COLUMN_TYPE_MAP.put(Columns.USERS_WPI_ID, JDBCType.VARCHAR);
		COLUMN_TYPE_MAP.put(Columns.USERS_USER_NAME, JDBCType.VARCHAR);
		COLUMN_TYPE_MAP.put(Columns.USERS_FIRST_NAME, JDBCType.VARCHAR);
		COLUMN_TYPE_MAP.put(Columns.USERS_LAST_NAME, JDBCType.VARCHAR);
		COLUMN_TYPE_MAP.put(Columns.USERS_EMAIL, JDBCType.VARCHAR);
		COLUMN_TYPE_MAP.put(Columns.USERS_ENCRYPTED_PASSWORD, JDBCType.VARCHAR);
		COLUMN_TYPE_MAP.put(Columns.USERS_SALT, JDBCType.VARCHAR);
		COLUMN_TYPE_MAP.put(Columns.USERS_ACCOUNT_STATE, JDBCType.VARCHAR);
		COLUMN_TYPE_MAP.put(Columns.USERS_CREATED_AT, JDBCType.TIMESTAMP_WITH_TIMEZONE);
		COLUMN_TYPE_MAP.put(Columns.USERS_UPDATED_AT, JDBCType.TIMESTAMP_WITH_TIMEZONE);
		COLUMN_TYPE_MAP.put(Columns.USERS_DELETED, JDBCType.BOOLEAN);
		
		// faculty
		COLUMN_TYPE_MAP.put(Columns.FACULTY_ID, JDBCType.INTEGER);
		
		// user_roles
		COLUMN_TYPE_MAP.put(Columns.USER_ROLES_ID, JDBCType.INTEGER);
		COLUMN_TYPE_MAP.put(Columns.USER_ROLES_ROLE_NAME, JDBCType.VARCHAR);
	};
	
	// attributes
	
	// users
	
	private Integer usersId;
	private String usersWpiId;
	private String usersUserName;
	private String usersFirstName;
	private String usersLastName;
	private String usersEmail;
	private String usersEncryptedPassword;
	private String usersSalt;
	private String usersAccountState;
	private Instant usersCreatedAt;
	private Instant usersUpdatedAt;
	private Boolean usersDeleted;
	
	// faculty
	
	private Integer facultyId;
	
	// user_roles
	
	private Integer userRolesId;
	private String userRolesRoleName;

	// methods
		
	public static JDBCType getColumnType(Columns column)
	{
		return COLUMN_TYPE_MAP.get(column);
	}
	
	public static String getColumnName(Columns column)
	{
		return column.toString().toLowerCase();
	}
	
	public static List<String> getColumnNameList()
	{
		List<String> columnNameList = new ArrayList<>();
		
		for(Columns column : COLUMN_LIST)
		{
			columnNameList.add(getColumnName(column));
		}
		
		return columnNameList;
	}

	
	// setters and getters
	
	
	public Integer getUsersId()
	{
		return usersId;
	}

	public void setUsersId(Integer usersId)
	{
		this.usersId = usersId;
	}

	public String getUsersWpiId()
	{
		return usersWpiId;
	}

	public void setUsersWpiId(String usersWpiId)
	{
		this.usersWpiId = usersWpiId;
	}

	public String getUsersUserName()
	{
		return usersUserName;
	}

	public void setUsersUserName(String usersUserName)
	{
		this.usersUserName = usersUserName;
	}

	public String getUsersFirstName()
	{
		return usersFirstName;
	}

	public void setUsersFirstName(String usersFirstName)
	{
		this.usersFirstName = usersFirstName;
	}

	public String getUsersLastName()
	{
		return usersLastName;
	}

	public void setUsersLastName(String usersLastName)
	{
		this.usersLastName = usersLastName;
	}

	public String getUsersEmail()
	{
		return usersEmail;
	}

	public void setUsersEmail(String usersEmail)
	{
		this.usersEmail = usersEmail;
	}
	
	public String getUsersEncryptedPassword()
	{
		return usersEncryptedPassword;
	}

	public void setUsersEncryptedPassword(String usersEncryptedPassword)
	{
		this.usersEncryptedPassword = usersEncryptedPassword;
	}

	public String getUsersSalt() {
		return usersSalt;
	}

	public void setUsersSalt(String usersSalt) {
		this.usersSalt = usersSalt;
	}

	public String getUsersAccountState()
	{
		return usersAccountState;
	}

	public void setUsersAccountState(String usersAccountState)
	{
		this.usersAccountState = usersAccountState;
	}

	public Instant getUsersCreatedAt()
	{
		return usersCreatedAt;
	}

	public void setUsersCreatedAt(Instant usersCreatedAt)
	{
		this.usersCreatedAt = usersCreatedAt;
	}
	
	public void setUsersCreatedAt(Timestamp usersCreatedAt)
	{
		if(usersCreatedAt != null)
		{
			this.usersCreatedAt = usersCreatedAt.toInstant();
		}
	}

	public Instant getUsersUpdatedAt()
	{
		return usersUpdatedAt;
	}

	public void setUsersUpdatedAt(Instant usersUpdatedAt)
	{
		this.usersUpdatedAt = usersUpdatedAt;
	}
	
	public void setUsersUpdatedAt(Timestamp usersUpdatedAt)
	{
		if(usersUpdatedAt != null)
		{
			this.usersUpdatedAt = usersUpdatedAt.toInstant();
		}
	}
	
	public Boolean getUsersDeleted()
	{
		return usersDeleted;
	}

	public void setUsersDeleted(Boolean usersDeleted)
	{
		this.usersDeleted = usersDeleted;
	}

	public Integer getFacultyId()
	{
		return facultyId;
	}

	public void setFacultyId(Integer facultyId)
	{
		this.facultyId = facultyId;
	}

	public Integer getUserRolesId()
	{
		return userRolesId;
	}

	public void setUserRolesId(Integer userRolesId)
	{
		this.userRolesId = userRolesId;
	}

	public String getUserRolesRoleName()
	{
		return userRolesRoleName;
	}

	public void setUserRolesRoleName(String userRolesRoleName)
	{
		this.userRolesRoleName = userRolesRoleName;
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
		result = prime * result + ((usersDeleted == null) ? 0 : usersDeleted.hashCode());
		result = prime * result + ((usersEmail == null) ? 0 : usersEmail.hashCode());
		result = prime * result + ((usersEncryptedPassword == null) ? 0 : usersEncryptedPassword.hashCode());
		result = prime * result + ((usersFirstName == null) ? 0 : usersFirstName.hashCode());
		result = prime * result + ((usersId == null) ? 0 : usersId.hashCode());
		result = prime * result + ((usersLastName == null) ? 0 : usersLastName.hashCode());
		result = prime * result + ((usersSalt == null) ? 0 : usersSalt.hashCode());
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
		if (!(obj instanceof UserInfo)) {
			return false;
		}
		UserInfo other = (UserInfo) obj;
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
		if (usersDeleted == null) {
			if (other.usersDeleted != null) {
				return false;
			}
		} else if (!usersDeleted.equals(other.usersDeleted)) {
			return false;
		}
		if (usersEmail == null) {
			if (other.usersEmail != null) {
				return false;
			}
		} else if (!usersEmail.equals(other.usersEmail)) {
			return false;
		}
		if (usersEncryptedPassword == null) {
			if (other.usersEncryptedPassword != null) {
				return false;
			}
		} else if (!usersEncryptedPassword.equals(other.usersEncryptedPassword)) {
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
		if (usersSalt == null) {
			if (other.usersSalt != null) {
				return false;
			}
		} else if (!usersSalt.equals(other.usersSalt)) {
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
		builder.append("UserInfo [usersId=");
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
		builder.append(", usersEncryptedPassword=");
		builder.append(usersEncryptedPassword);
		builder.append(", usersSalt=");
		builder.append(usersSalt);
		builder.append(", usersAccountState=");
		builder.append(usersAccountState);
		builder.append(", usersCreatedAt=");
		builder.append(usersCreatedAt);
		builder.append(", usersUpdatedAt=");
		builder.append(usersUpdatedAt);
		builder.append(", usersDeleted=");
		builder.append(usersDeleted);
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
