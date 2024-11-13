package com.imaginnovate.employee.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class JwtService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    public String authenticate(String userName, String password){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
        return jwtUtil.generateToken(userName);
    }

}
