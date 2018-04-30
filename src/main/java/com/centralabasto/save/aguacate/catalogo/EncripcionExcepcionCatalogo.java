package com.centralabasto.save.aguacate.catalogo;

public enum EncripcionExcepcionCatalogo {

	ERROR_CODIFICACION_CONTRASENNIA((short)100, "Ocurrió un error al intentar cifrar la contrasennia")
	, ERROR_DECODIFICACION_CONTRASENNIA((short)100, "Ocurrió un error al intentar descifrar la contrasennia");
	
	private final short codigo;
	private final String mensaje;
	
	private EncripcionExcepcionCatalogo(short codigo, String mensaje) {
		this.codigo = codigo;
		this.mensaje = mensaje;
	}
	
	public short getCodigo() {
		return codigo;
	}
	public String getMensaje() {
		return mensaje;
	}
}
