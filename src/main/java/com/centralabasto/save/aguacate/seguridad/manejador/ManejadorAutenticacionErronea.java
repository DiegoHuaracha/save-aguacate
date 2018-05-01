package com.centralabasto.save.aguacate.seguridad.manejador;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class ManejadorAutenticacionErronea extends SimpleUrlAuthenticationFailureHandler {

	private static final String HOME = "/index.do";
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		setDefaultFailureUrl(HOME);
		super.onAuthenticationFailure(request, response, exception);
	}
}
