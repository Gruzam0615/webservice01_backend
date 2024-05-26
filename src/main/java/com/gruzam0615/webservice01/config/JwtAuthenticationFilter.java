package com.gruzam0615.webservice01.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.gruzam0615.webservice01.token.Token;
import com.gruzam0615.webservice01.token.TokenRepository;
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
    protected void doFilterInternal(
        @NonNull HttpServletRequest request, 
        @NonNull HttpServletResponse response, 
        @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        List<String> noTokenUrl = Arrays.asList(
            "/api/sign/signUp",
            "/api/sign/signIn"
        );
        if(noTokenUrl.contains(request.getServletPath())) {
            log.info("Requested noTokenUrl url: {}", request.getServletPath());
            filterChain.doFilter(request, response);
        } else {
            log.info("Requested not noTokenUrl url: {}", request.getServletPath());
        }

        String jwt = request.getHeader("Authorization");
        String userName;
        if(jwt == null) {
            filterChain.doFilter(request, response);
            return;
        }
        userName = jwtService.extractUsername(jwt);
        if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
            Token t = tokenRepository.findByToken(jwt).get();
            var isTokenValid = jwtService.isTokenValid(jwt, t);

            if(isTokenValid && !t.isExpired() && !t.isRevoked()) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
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
