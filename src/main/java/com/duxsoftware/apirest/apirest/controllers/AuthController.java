package com.duxsoftware.apirest.apirest.controllers;

import com.duxsoftware.apirest.apirest.DTO.TokenResponse;
import com.duxsoftware.apirest.apirest.DTO.UsuarioRequest;
import com.duxsoftware.apirest.apirest.models.Usuario;
import com.duxsoftware.apirest.apirest.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping( path = "/login")
    public ResponseEntity<TokenResponse> login(@RequestBody UsuarioRequest usuarioRequest){
        String token = usuarioService.login(usuarioRequest);
        TokenResponse tokenResponse = new TokenResponse(token);
        return ResponseEntity.ok(tokenResponse);
    }

    @PostMapping( path = "/register")
    public ResponseEntity<TokenResponse> register(@RequestBody UsuarioRequest usuarioRequest){
        String token = usuarioService.register(usuarioRequest);
        TokenResponse response = new TokenResponse(token);
        return ResponseEntity.ok(response);
    }

    @GetMapping( path = "/users")
    public List<Usuario> findAll(){
        return usuarioService.findAll();
    }
}
