package com.centralabasto.save.aguacate.catalogo;

public enum IndexCatalogo {

	CREDENCIALES_INCORRECTAS("Contraseña incorrecta para el usuario seleccionado");
	
	private final String mensaje;
	
	private IndexCatalogo(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public String getMensaje() {
		return mensaje;
	}
}
