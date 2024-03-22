package com.duxsoftware.apirest.apirest.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    private String SECRET_KEY="nicolasmamaniduxsoftware1E32S1L2W12323284355782983452834280943289023404932";

    public String getToken(UserDetails usuario){
        return getToken(new HashMap<>(), usuario);
    }
    private String getToken(Map<String, Object> extractClaims, UserDetails usuario){
        return Jwts
                .builder()
                .setClaims(extractClaims)
                .setSubject(usuario.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
