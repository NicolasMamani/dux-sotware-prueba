package com.duxsoftware.apirest.apirest.controllers;

import com.duxsoftware.apirest.apirest.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;



}