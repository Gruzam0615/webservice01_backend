package com.gruzam0615.webservice01.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import com.gruzam0615.webservice01.sign.SignOutService;
import com.gruzam0615.webservice01.sign.SignService;
import com.gruzam0615.webservice01.users.service.UsersService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class LogoutService implements LogoutHandler {
    
    private final TokenRepository tokenRepository;
    private final SignOutService signOutService;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        log.info("Called signOut()");
        final String authHeader = request.getHeader("Authorization");
        final String jwt;

        if(authHeader == null || authHeader.startsWith("Bearer ")) {
            return;
        }
        jwt = authHeader.substring(7);
        var stored = tokenRepository.findByUsersToken(authHeader).get();
        
        if(stored != null) {
            log.debug("stored: {}", stored.getUsername());
            signOutService.revokeTokens(stored);
            SecurityContextHolder.clearContext();
        }
        else {
            log.debug("stored is null");
        }
    }
    
}
