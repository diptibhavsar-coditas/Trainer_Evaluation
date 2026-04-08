package com.example.Task3.controller;

import com.example.Task3.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TokenController {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/token-generate")
    public String generateToken(@RequestParam String username){
        String token = jwtUtil.generateToken(username);
        return  token;
    }

}
