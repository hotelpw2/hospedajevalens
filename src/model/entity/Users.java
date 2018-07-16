package model.entity;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
@PersistenceCapable(identityType = IdentityType.APPLICATION)

public class Users {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	@Persistent
	private String userName;
	@Persistent
	private String userRole;
	@Persistent
	private String userMail;
	@Persistent
	private boolean userStatus;
	@Persistent
	private String userCreated;
	
	public Users(String role, String mail, boolean status, String created) {
		this.userRole = role;
		this.userMail = mail;
		this.userStatus = status;
		this.userCreated=created;
	}
	public Users(String name, String role, String mail, boolean status, String created) {
		this.userName = name;
		this.userRole = role;
		this.userMail = mail;
		this.userStatus = status;
		this.userCreated=created;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id=id;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String name) {
		this.userName=name;
	}
	
	public String getUserRole() {
		return userRole;
	}
	
	public void setUserRole(String role) {
		this.userRole=role;
	}
	
	public String getUserMail() {
		return userMail;
	}
	
	public void setUserMail(String mail) {
		this.userMail=mail;
	}
	
	public boolean getUserStatus() {
		return userStatus;
	}
	
	public void setUserStatus(boolean status) {
		this.userStatus=status;
	}
	
	public String getUserCreated() {
		return userCreated;
	}
	
	public void setUserCreated(String created) {
		this.userCreated=created;
	}
}
