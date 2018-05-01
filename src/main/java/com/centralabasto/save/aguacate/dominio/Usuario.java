package com.centralabasto.save.aguacate.dominio;

import java.io.Serializable;
import java.security.Principal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Usuario implements Serializable, Principal {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Short clave;
	
	private String contrasennia;
	
	private String nombre;
	
	private String apellidoPaterno;
	
	private String apellidoMaterno;
	
	private Date fechaAlta;
	
	private Short activo;
	
	private Date fechaUltimoAcceso;
	
	@ManyToOne
	@JoinColumn(name="clave_perfil")
	private Perfil perfil;

	public Short getClave() {
		return clave;
	}

	public void setClave(Short clave) {
		this.clave = clave;
	}

	public String getContrasennia() {
		return contrasennia;
	}

	public void setContrasennia(String contrasennia) {
		this.contrasennia = contrasennia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public Short getActivo() {
		return activo;
	}

	public void setActivo(Short activo) {
		this.activo = activo;
	}

	public Date getFechaUltimoAcceso() {
		return fechaUltimoAcceso;
	}

	public void setFechaUltimoAcceso(Date fechaUltimoAcceso) {
		this.fechaUltimoAcceso = fechaUltimoAcceso;
	}

	@Override
	public String getName() {
		StringBuilder nombreCompleto = new StringBuilder();
		nombreCompleto.append(nombre);
		nombreCompleto.append(" ").append(apellidoPaterno);
		nombreCompleto.append(" ").append(apellidoMaterno);
		
		return nombreCompleto.toString();
	}
	
	@Override
	public String toString() {
		return "Usuario [clave=" + clave + ", contrasennia=" + contrasennia + ", nombre=" + nombre
				+ ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno + ", fechaAlta="
				+ fechaAlta + ", activo=" + activo + ", fechaUltimoAcceso=" + fechaUltimoAcceso + "]";
	}
}
