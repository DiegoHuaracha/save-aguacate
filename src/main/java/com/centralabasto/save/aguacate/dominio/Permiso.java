package com.centralabasto.save.aguacate.dominio;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Permiso implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Short clave;
	
	private String nombre;
	
	private String claveapp;

	public Short getClave() {
		return clave;
	}

	public void setClave(Short clave) {
		this.clave = clave;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getClaveapp() {
		return claveapp;
	}

	public void setClaveapp(String claveapp) {
		this.claveapp = claveapp;
	}

	@Override
	public String toString() {
		return "Permiso [clave=" + clave + ", nombre=" + nombre + ", claveapp=" + claveapp + "]";
	}
}
