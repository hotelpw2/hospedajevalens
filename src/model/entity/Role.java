package model.entity;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
@PersistenceCapable(identityType = IdentityType.APPLICATION)

public class Role {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	@Persistent
	private String roleName;
	@Persistent
	private boolean roleStatus;
	@Persistent
	private String roleCreated;
	
	public Role(String name, boolean status, String created) {
		this.roleName=name;
		this.roleStatus=status;
		this.roleCreated=created;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id=id;
	}
	
	public String getRoleName() {
		return roleName;
	}
	
	public void setRoleName(String name) {
		this.roleName=name;
	}
	
	public boolean getRoleStatus() {
		return roleStatus;
	}
	
	public void setRoleStatus(boolean status) {
		this.roleStatus=status;
	}
	
	public String getRoleCreated() {
		return roleCreated;
	}
	
	public void setRoleCreated(String created) {
		this.roleCreated=created;
	}
}
