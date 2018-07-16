package model.entity;



import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
@PersistenceCapable(identityType = IdentityType.APPLICATION)

public class Access {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	@Persistent
	private String roleAccess;
	@Persistent
	private String resourceAccess;
	@Persistent
	private boolean accessStatus;
	@Persistent
	private String accessCreated;
	
	public Access(String role, String resources, boolean status, String created) {
		this.roleAccess=role;
		this.resourceAccess=resources;
		this.accessStatus=status;
		this.accessCreated=created;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id=id;
	}
	
	public String getRoleAccess() {
		return roleAccess;
	}
	
	public void setRoleAccess(String role) {
		this.roleAccess=role;
	}
	
	public String getResourceAccess() {
		return resourceAccess;
	}
	
	public void setResourceAccess(String resource) {
		this.resourceAccess=resource;
	}
	
	public boolean getAccessStatus() {
		return accessStatus;
	}
	
	public void setAccessStatus(boolean status) {
		this.accessStatus=status;
	}
	
	public String getAccessCreated() {
		return accessCreated;
	}
	
	public void setAccessCreated(String created) {
		this.accessCreated=created;
	}
}