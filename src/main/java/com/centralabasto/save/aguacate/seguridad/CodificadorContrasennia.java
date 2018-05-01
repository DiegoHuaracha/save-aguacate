package com.centralabasto.save.aguacate.seguridad;

import java.security.MessageDigest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.stereotype.Component;

import com.centralabasto.save.aguacate.catalogo.EncripcionExcepcionCatalogo;
import com.centralabasto.save.aguacate.excepcion.EncripcionExcepcion;
import com.centralabasto.save.aguacate.seguridad.config.EncripcionConfig;

@Component
public class CodificadorContrasennia {

	@Autowired
	private EncripcionConfig encripcionConfig;
	
	public String getContrasenniaEncriptada(String contrasenniaTextual) throws EncripcionExcepcion {
		String contrasenniaEncriptada;
		
		try {
			MessageDigest digestor = MessageDigest.getInstance(encripcionConfig.getAlgoritmo());
			byte[] contrasenniaEncriptadaBytes = digestor.digest(contrasenniaTextual.getBytes());
			
			contrasenniaEncriptada = new String(Hex.encode(contrasenniaEncriptadaBytes));
        } catch (Exception e) {
        	throw new EncripcionExcepcion(
        			EncripcionExcepcionCatalogo.ERROR_CODIFICACION_CONTRASENNIA.getCodigo()
        			, EncripcionExcepcionCatalogo.ERROR_CODIFICACION_CONTRASENNIA.getMensaje()
        			, e);
		}
		
		return contrasenniaEncriptada;
	}
	
	public boolean coincideContrasennia(String contrasenniaEncriptada, String contrasenniaTextual) throws EncripcionExcepcion {
		boolean bandera = false;
		
		try {
			String contrasenniaTextualEncriptada = getContrasenniaEncriptada(contrasenniaTextual);
			
			if(contrasenniaEncriptada.equals(contrasenniaTextualEncriptada)) {
				bandera = true;
			}
        } catch (Exception e) {
        	throw new EncripcionExcepcion(
        			EncripcionExcepcionCatalogo.ERROR_DECODIFICACION_CONTRASENNIA.getCodigo()
        			, EncripcionExcepcionCatalogo.ERROR_DECODIFICACION_CONTRASENNIA.getMensaje()
        			, e);
		}
		
		return bandera;
	}
}
