package com.centralabasto.save.aguacate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.centralabasto.save.aguacate.excepcion.EncripcionExcepcion;
import com.centralabasto.save.aguacate.seguridad.CodificadorContrasennia;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EncripcionTest {

	@Autowired
	private CodificadorContrasennia codificadorContrasennia;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EncripcionTest.class);
	
	@Test
	public void testValidacionContrasenniaCorrecta() {
		String contrasenniaOriginal = "laquesea123";
		String contrasenniaValidacion = "laquesea123";
		
		try {
			String contrasenniaEncriptada = codificadorContrasennia.getContrasenniaEncriptada(contrasenniaOriginal);
			boolean coincidenContrasennias = codificadorContrasennia.coincideContrasennia(contrasenniaEncriptada, contrasenniaValidacion);
			
			assertTrue("La contrasennia de validacion no corresponde con la contrasennia original", coincidenContrasennias);
		} catch (EncripcionExcepcion e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testValidacionContrasenniaIncorrecta() {
		String contrasenniaOriginal = "laquesea123";
		String contrasenniaValidacion = "Laquesea123";
		
		try {
			String contrasenniaEncriptada = codificadorContrasennia.getContrasenniaEncriptada(contrasenniaOriginal);
			boolean coincidenContrasennias = codificadorContrasennia.coincideContrasennia(contrasenniaEncriptada, contrasenniaValidacion);
			
			assertFalse("Ls contrasennia de validacion corresponde con la contrasennia original, esto no debería suceder en esta prueba", coincidenContrasennias);
		} catch (EncripcionExcepcion e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGenerarContrrasennias() {
		String[] contrasenniaOriginal = new String[]{"laquesea123", "savegenerica"};
		
		Arrays.asList(contrasenniaOriginal).stream().forEach(contrasennia -> {
			try {
				String contrasenniaEncriptada = codificadorContrasennia.getContrasenniaEncriptada(contrasennia);
				LOGGER.info("{}\t->\t{}", contrasennia, contrasenniaEncriptada);
			} catch(Exception e) {
				LOGGER.error("{}", e);
			}
		});
	}
}
