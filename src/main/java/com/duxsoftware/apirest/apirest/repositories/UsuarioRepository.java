package com.duxsoftware.apirest.apirest.repositories;

import com.duxsoftware.apirest.apirest.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
