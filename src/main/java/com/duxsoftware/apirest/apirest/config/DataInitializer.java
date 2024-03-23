package com.duxsoftware.apirest.apirest.config;

import com.duxsoftware.apirest.apirest.DTO.UsuarioRequest;
import com.duxsoftware.apirest.apirest.models.Usuario;
import com.duxsoftware.apirest.apirest.repositories.UsuarioRepository;
import com.duxsoftware.apirest.apirest.services.UsuarioService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DataInitializer {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostConstruct
    public void initialize() {
        Optional<Usuario> existingUser = usuarioRepository.findByUsername("test");
        if (existingUser.isEmpty()) {
            UsuarioRequest usuarioRequest = new UsuarioRequest("test", "12345");
            usuarioService.register(usuarioRequest);
            System.out.println("Usuario 'test' creado automáticamente con contraseña '12345'");
        } else {
            System.out.println("El usuario 'test' ya existe en la base de datos.");
        }
    }
}
