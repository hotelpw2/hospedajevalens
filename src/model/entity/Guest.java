package model.entity;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)

public class Guest {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	@Persistent
	private String name;
	@Persistent
	private String city;
	@Persistent
	private String phone;
	@Persistent
	private String dni;
	@Persistent
	private boolean gender;
	@Persistent
	private boolean status;
	@Persistent
	private String age;
	@Persistent
	private String email;
	@Persistent
	private Date created;
	@Persistent
	private double cartera = 2000.0;

	public Guest(String name, String city, String phone, String dni, boolean gender, boolean status, String age,
			String email, Date created) {
		this.name = name;
		this.city = city;
		this.phone = phone;
		this.dni = dni;
		this.gender = gender;
		this.status = status;
		this.age = age;
		this.email = email;
		this.created = new Date();
	}

	public void transaccion(double precio) {
		if (cartera - precio < 0) {
			cartera += 2000.0;
			cartera -= precio;
		} else {
			cartera -= precio;
		}
	}
	public double getCartera(){
		return cartera;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
}