package com.centralabasto.save.aguacate.controlador;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.centralabasto.save.aguacate.catalogo.IndexCatalogo;
import com.centralabasto.save.aguacate.dominio.Usuario;
import com.centralabasto.save.aguacate.repositorio.UsuarioRepositorio;

@Controller
public class IndexControlador {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	private static final String PAGINA_ACCESO_DENEGADO = "accesodenegado";
	private static final String ULTIMA_EXCEPCION_LOGIN = "SPRING_SECURITY_LAST_EXCEPTION";
	
	private static final Logger LOGGER = LoggerFactory.getLogger(IndexControlador.class);
	
	@RequestMapping("/index.do")
	public ModelAndView index(HttpSession sesion) {
		ModelAndView modelo = new ModelAndView("index");
		List<Usuario> usuarios = usuarioRepositorio.findAll();
		Exception excepcion = (Exception) sesion.getAttribute(ULTIMA_EXCEPCION_LOGIN);
		    	
    	if (excepcion != null) {
    		LOGGER.error(excepcion.getMessage());
    		modelo.addObject("error", IndexCatalogo.CREDENCIALES_INCORRECTAS.getMensaje());
    	}
		
		modelo.addObject("usuarios", usuarios);
		
		return modelo;
	}
	
	@GetMapping("/principal.do")
	public String principal() {
		return "principal";
	}
	
	@RequestMapping(value="/accesodenegado.ajax")
	public String accesodenegadoasincrona() {
		return PAGINA_ACCESO_DENEGADO;
	}
	
	@RequestMapping(value="/accesodenegado.do")
	public String accesodenegadosincrona() {
		return PAGINA_ACCESO_DENEGADO;
	}
}
