package model.entity;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Resource {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	@Persistent
	private String resourceName;
	@Persistent
	private boolean resourceStatus;
	@Persistent
	private String resourceCreated;
	
	public Resource(String name, boolean status, String created) {
		this.resourceName=name;
		this.resourceStatus=status;
		this.resourceCreated=created;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id=id;
	}
	
	public String getResourceName() {
		return resourceName;
	}
	
	public void setResourceName(String name) {
		this.resourceName=name;
	}
	
	public boolean getResourceStatus() {
		return resourceStatus;
	}
	
	public void setResourceStatus(boolean status) {
		this.resourceStatus=status;
	}
	
	public String getResourceCreated() {
		return resourceCreated;
	}
	
	public void setResourceCreated(String created) {
		this.resourceCreated=created;
	}
}
