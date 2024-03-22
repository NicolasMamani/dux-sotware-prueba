package com.duxsoftware.apirest.apirest.auth;

import com.duxsoftware.apirest.apirest.DTO.UsuarioRequest;
import com.duxsoftware.apirest.apirest.models.Rol;
import com.duxsoftware.apirest.apirest.models.Usuario;
import com.duxsoftware.apirest.apirest.repositories.UsuarioRepository;
import com.duxsoftware.apirest.apirest.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping( path = "/login")
    public String login(@RequestBody UsuarioRequest user){

        return "login";
    }

    @PostMapping( value = "/register")
    public ResponseEntity<String> register(@RequestBody UsuarioRequest usuarioRequest){

        return ResponseEntity.ok(usuarioService.register(usuarioRequest));
    }
}
