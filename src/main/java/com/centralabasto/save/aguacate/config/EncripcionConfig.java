package com.centralabasto.save.aguacate.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:properties/encripcion.properties")
public class EncripcionConfig {

	@Value("${encripcion.algoritmo}")
	private String algoritmo;
	
	public String getAlgoritmo() {
		return algoritmo;
	}
}
