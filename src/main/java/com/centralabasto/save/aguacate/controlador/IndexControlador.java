package com.centralabasto.save.aguacate.controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.centralabasto.save.aguacate.catalogo.IndexCatalogo;
import com.centralabasto.save.aguacate.dominio.Usuario;
import com.centralabasto.save.aguacate.repositorio.UsuarioRepositorio;

@Controller
public class IndexControlador {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	@RequestMapping("/index.do")
	public ModelAndView index(@RequestParam Optional<String> error) {
		ModelAndView modelo = new ModelAndView("index");
		List<Usuario> usuarios = usuarioRepositorio.findAll();
		
		if(error.isPresent()) {
			modelo.addObject("error", IndexCatalogo.CREDENCIALES_INCORRECTAS.getMensaje());
		}
		
		modelo.addObject("usuarios", usuarios);
		
		return modelo;
	}
	
	@GetMapping("/principal.do")
	public String principal() {
		return "principal";
	}
}
