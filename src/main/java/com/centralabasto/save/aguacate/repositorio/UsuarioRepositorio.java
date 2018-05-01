package com.centralabasto.save.aguacate.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.centralabasto.save.aguacate.dominio.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Short> {

}
