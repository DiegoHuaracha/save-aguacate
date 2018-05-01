package com.centralabasto.save.aguacate.seguridad.proveedor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.centralabasto.save.aguacate.catalogo.AutenticacionErrorCatalogo;
import com.centralabasto.save.aguacate.dominio.Permiso;
import com.centralabasto.save.aguacate.dominio.Usuario;
import com.centralabasto.save.aguacate.excepcion.EncripcionExcepcion;
import com.centralabasto.save.aguacate.repositorio.UsuarioRepositorio;
import com.centralabasto.save.aguacate.seguridad.CodificadorContrasennia;

@Component
public class ProveedorAutenticacion implements AuthenticationProvider {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	@Autowired
	private CodificadorContrasennia codificadorContrasennia;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProveedorAutenticacion.class);
	
	@Override
	public Authentication authenticate(Authentication autenticacion) throws AuthenticationException {
		Short claveUsuario = autenticacion.getName() == null ? -1 : Short.parseShort(autenticacion.getName());
		String contrasenniaTextual = autenticacion.getCredentials().toString();
		
		LOGGER.info("contrasenniaTextual: {}", contrasenniaTextual);
		
		Optional<Usuario> usuario = usuarioRepositorio.findById(claveUsuario);
		
		validarExistenciaUsuario(usuario);
		validarContrasennia(usuario.get(), contrasenniaTextual);
		validarUsuarioActivo(usuario.get());
		   
		List<GrantedAuthority> permisos = getPermisosPorUsuario(usuario.get().getPerfil().getPermisos());
		  
		return new UsernamePasswordAuthenticationToken(usuario.get(), usuario.get().toString(), permisos);
	}

	@Override
	public boolean supports(Class<?> autenticacion) {
		return autenticacion.equals(UsernamePasswordAuthenticationToken.class);
	}

	private void validarExistenciaUsuario(Optional<Usuario> usuario) {
		if(!usuario.isPresent()) {
			throw new AuthenticationCredentialsNotFoundException(AutenticacionErrorCatalogo.CREDENCIALES_INCORRECTAS.getMensaje());
		}
	}
	
	private void validarContrasennia(Usuario usuario, String contrasenniaTextual) {
		boolean coincidenContrasennias;
		
		try {
			coincidenContrasennias = codificadorContrasennia.coincideContrasennia(usuario.getContrasennia(), contrasenniaTextual);
			
			if(!coincidenContrasennias) {
				throw new AuthenticationCredentialsNotFoundException(AutenticacionErrorCatalogo.CREDENCIALES_INCORRECTAS.getMensaje());
			}
		} catch (EncripcionExcepcion e) {
			throw new AuthenticationServiceException(e.getMensaje());
		}
	}
	
	private void validarUsuarioActivo(Usuario usuario) {
		if(usuario.getActivo() != 1) {
			throw new DisabledException(AutenticacionErrorCatalogo.CUENTA_DESHABILITADA.getMensaje());
		}
	}
	
	public List<GrantedAuthority> getPermisosPorUsuario(List<Permiso> permisosUsuario) {
		return permisosUsuario.stream()
				.map(permisoUsuario -> new SimpleGrantedAuthority(permisoUsuario.getClaveapp()))
				.collect(Collectors.toList());
    }
}
