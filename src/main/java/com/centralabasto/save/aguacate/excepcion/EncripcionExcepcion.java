package com.centralabasto.save.aguacate.excepcion;

public class EncripcionExcepcion extends Exception {

	private static final long serialVersionUID = -1035793618841191586L;
	private final short codigo;
	private final String mensaje;
	
	public EncripcionExcepcion(short codigo, String mensaje, Throwable excepcion) {
		super(excepcion);
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
