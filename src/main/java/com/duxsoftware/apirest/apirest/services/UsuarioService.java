package com.duxsoftware.apirest.apirest.services;

import com.duxsoftware.apirest.apirest.DTO.UsuarioRequest;
import com.duxsoftware.apirest.apirest.models.Rol;
import com.duxsoftware.apirest.apirest.models.Usuario;
import com.duxsoftware.apirest.apirest.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;


    public String register(UsuarioRequest usuarioRequest){
        Usuario usuario = new Usuario();
        usuario.setUsername(usuarioRequest.getUsername());
        usuario.setPassword(passwordEncoder.encode(usuarioRequest.getPassword()));
        usuario.setRol(Rol.USER);
        usuarioRepository.save(usuario);
        return jwtService.getToken(usuario);
    }

    public String login(UsuarioRequest usuarioRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usuarioRequest.getUsername(), usuarioRequest.getPassword()));
        UserDetails usuario = usuarioRepository.findByUsername(usuarioRequest.getUsername()).orElseThrow();
        return jwtService.getToken(usuario);
    }

    @Transactional(readOnly = true)
    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Usuario> findById(Long id){
        return usuarioRepository.findById(id);
    }

    public Usuario save(Usuario usuario){
        return usuarioRepository.save(usuario);
    }
}
