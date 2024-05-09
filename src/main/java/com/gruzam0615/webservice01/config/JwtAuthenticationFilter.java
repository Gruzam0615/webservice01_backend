package com.gruzam0615.webservice01.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.gruzam0615.webservice01.users.entity.Users;
import com.gruzam0615.webservice01.users.repository.UsersRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final TokenRepository tokenRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getServletPath().contains("/api/sign")) {
            log.debug("doFilterInternal");
            filterChain.doFilter(request, response);
            return;
        }

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        String username;

        if(authHeader == null || authHeader.startsWith("Bearer ")) {
            log.debug("Token doesn't exist");
            filterChain.doFilter(request, response);
            return;
        }

        // Bearer [Token]
        jwt = authHeader.substring(7);
        // username = jwtService.extractUsername(jwt);
        username = jwtService.extractUsername(authHeader);

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            log.debug("username: {}", username);
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            log.debug("userDetails: {}", userDetails.toString());
            Users u = tokenRepository.findByUsersToken(authHeader).get();
            log.debug("Users: {}", u.toString());

            var isTokenValid = jwtService.isTokenValid(authHeader, u);
            log.debug("isTokenValid result: {}", isTokenValid);
            if(isTokenValid) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails.getUsername(),
                    userDetails.getPassword(),
                    userDetails.getAuthorities()
                );
                authenticationToken.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
