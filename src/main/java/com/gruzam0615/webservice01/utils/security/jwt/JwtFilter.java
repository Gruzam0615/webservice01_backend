package com.gruzam0615.webservice01.utils.security.jwt;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // throw new UnsupportedOperationException("Unimplemented method 'doFilterInternal'");
        String requestToken = request.getHeader("Authorization");

        // if(!requestToken.equals(null) && jwtProvider.verifyToken(requestToken)) {
        //     Authentication authentication = 
        // } 

    }

}
