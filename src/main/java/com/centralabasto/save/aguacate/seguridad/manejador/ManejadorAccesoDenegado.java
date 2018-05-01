package com.centralabasto.save.aguacate.seguridad.manejador;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class ManejadorAccesoDenegado implements AccessDeniedHandler {

	private static final String PAGINA_ACCESO_DENEGADO_PETICION_ASINCRONA = "/accesodenegado.ajax";
	private static final String PAGINA_ACCESO_DENEGADO_PETICION_SINCRONA = "/accesodenegado.do";
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		
		String paginaRedireccion = "";
		ServletContext servletContext = request.getServletContext();
		String recursoSolicitado = request.getRequestURL().toString();
		request.setAttribute("recursoSolicitado", recursoSolicitado);
		
		if(recursoSolicitado.endsWith(".do")) {
			paginaRedireccion = PAGINA_ACCESO_DENEGADO_PETICION_SINCRONA;
		} else if(recursoSolicitado.endsWith(".ajax")) {
			paginaRedireccion = PAGINA_ACCESO_DENEGADO_PETICION_ASINCRONA;
		}
		
		response.sendRedirect(servletContext.getContextPath() + paginaRedireccion);
	}

}
