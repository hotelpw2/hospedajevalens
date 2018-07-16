package model.entity;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Room {
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY) 
	private Long id;

	@Persistent
	private String idGuest;
	
	@Persistent
	private int piso;
	
	@Persistent
	private int numero;
	
	@Persistent
	private int estado;
	
	@Persistent
	private int tipo;
	
	public Room(int piso, int numero, int estado, int tipo) {
		this.piso = piso;
		this.numero = numero;
		this.estado = estado;
		this.tipo = tipo;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getIdGuest(){
		return idGuest;
	}
	
	public void setIdGuest(String idGuest){
		this.idGuest = idGuest;
	}

	public int getPiso() {
		return piso;
	}

	public void setPiso(int piso) {
		this.piso = piso;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	
	
	
}