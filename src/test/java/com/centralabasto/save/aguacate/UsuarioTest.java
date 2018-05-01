package com.centralabasto.save.aguacate;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.centralabasto.save.aguacate.dominio.Usuario;
import com.centralabasto.save.aguacate.repositorio.UsuarioRepositorio;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsuarioTest {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioTest.class);
	
	@Test
	public void testGetUsuario() {
		LOGGER.info("***** testGetUsuario *****");
		short claveUsuario = 1;
		Usuario usuario = usuarioRepositorio.findById(claveUsuario).get();
		
		assertNotNull("El objeto usuario es nulo", usuario);
		
		LOGGER.info("{}", usuario);
		LOGGER.info("\t{}", usuario.getPerfil());
		
		usuario.getPerfil().getPermisos().stream().forEach(permiso -> {
			LOGGER.info("\t\t{}", permiso);
		});
	}
	
	@Test
	public void testGetAllUsuarios() {
		LOGGER.info("***** testGetAllUsuarios *****");
		List<Usuario> usuarios = usuarioRepositorio.findAll();
		
		assertNotNull("El objeto usuarios es nulo", usuarios);
		
		LOGGER.info("Usuarios:{}", usuarios.size());
		
		usuarios.stream().forEach(usuario -> {
			LOGGER.info("{}", usuario);
			LOGGER.info("\t{}", usuario.getPerfil());
			
			usuario.getPerfil().getPermisos().stream().forEach(permiso -> {
				LOGGER.info("\t\t{}", permiso);
			});
		});
	}
}
