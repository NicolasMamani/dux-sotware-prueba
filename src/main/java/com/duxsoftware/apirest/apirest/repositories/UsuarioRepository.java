package com.duxsoftware.apirest.apirest.repositories;

import com.duxsoftware.apirest.apirest.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);
}
