package com.centralabasto.save.aguacate.catalogo;

public enum AutenticacionErrorCatalogo {

	CREDENCIALES_INCORRECTAS("La contraseña proporcionada es incorrecta para el usuario seleccionado")
	, CUENTA_DESHABILITADA("La cuenta del usuario seleccionado se ha deshabilitado.");
	
	private final String mensaje;
	
	AutenticacionErrorCatalogo(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public String getMensaje() {
		return mensaje;
	}
}
