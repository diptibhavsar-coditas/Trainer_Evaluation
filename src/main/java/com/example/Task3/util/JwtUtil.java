package com.example.Task3.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.util.Date;

@Component
public class JwtUtil {

    private final String secret_key="secretsecretsecretsecretsecretsecretsecretsecretsecretsecret";

    public String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS256,secret_key)
                .compact();

    }
}
