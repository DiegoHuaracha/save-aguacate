package com.centralabasto.save.aguacate.dominio;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Perfil implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Short clave;
	
	private String nombre;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
			name="permiso_perfil"
			, joinColumns=@JoinColumn(name="clave_perfil")
			, inverseJoinColumns=@JoinColumn(name="clave_permiso")
	)
	private List<Permiso> permisos;

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

	public List<Permiso> getPermisos() {
		return permisos;
	}

	public void setPermisos(List<Permiso> permisos) {
		this.permisos = permisos;
	}

	@Override
	public String toString() {
		return "Perfil [clave=" + clave + ", nombre=" + nombre + "]";
	}
}
