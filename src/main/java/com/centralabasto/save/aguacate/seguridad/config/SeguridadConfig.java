package com.centralabasto.save.aguacate.seguridad.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.centralabasto.save.aguacate.seguridad.manejador.ManejadorAutenticacionExitosa;

@Configuration
@EnableWebSecurity
public class SeguridadConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    private AuthenticationProvider proveedorAutenticacion;
	
	@Autowired
	private ManejadorAutenticacionExitosa manejadorAutenticacionExitosa;
	
	@Override
	protected void configure(AuthenticationManagerBuilder authentication) throws Exception {
		authentication.authenticationProvider(proveedorAutenticacion);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
        .csrf().disable()
        .headers()
        .disable()
        .authorizeRequests()
        .antMatchers("/css/**", "/js/**", "/img/**", "/taglibs/**", "/index**", "/fonts/**"
      		  , "**.json").permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin().loginPage("/index.do").usernameParameter("claveUsuario").permitAll()
        //.failureHandler(manejadorAutenticacionErronea)
        .successHandler(manejadorAutenticacionExitosa)
        //.and()
        //.exceptionHandling().accessDeniedHandler(manejadorAccesoDenegado)
        ;
	}
}
