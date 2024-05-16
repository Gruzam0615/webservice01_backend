package com.gruzam0615.webservice01.sign;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.gruzam0615.webservice01.config.JwtService;
import com.gruzam0615.webservice01.token.Token;
import com.gruzam0615.webservice01.token.TokenRepository;
import com.gruzam0615.webservice01.users.entity.Users;
import com.gruzam0615.webservice01.users.entity.UsersRole;
import com.gruzam0615.webservice01.users.repository.UsersRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class SignService {
    
    private final UsersRepository usersRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public boolean signUpService(SignDTO param) {
        log.info("Called signUpService");

        LocalDateTime ct = LocalDateTime.now();
        String encodedPass = passwordEncoder.encode(param.getPassword());

        Users u = new Users();
            u.setUsersName(param.getAccount());
            u.setUsersPass(encodedPass);
            u.setUsersCreatedTime(ct);
            u.setUsersUpdatedTime(ct);

        if(param.getRole() == null) {
            u.setUsersRole(UsersRole.MEMBER);
        } else {
            u.setUsersRole(UsersRole.valueOf(param.getRole()));
        }
        

        Users data = usersRepository.save(u);
        
        if(data != null) {
            return true;
        }
        else {
            return false;
        }        
    }

    public String signInService(SignDTO param) {
        log.info("Called signInService() Timestamp: {}", new Date(System.currentTimeMillis()).toString());
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                param.getAccount(),
                param.getPassword()
            )
        );
        var u = usersRepository.findByUsersName(param.getAccount()).get();
        var jwtToken = jwtService.generateToken(u);
        // var refreshToken = jwtService.generateRefreshToken(u);
        saveUserToken(u, jwtToken);
        return jwtToken;
    }

    // Users 객체에 token 속성이 포함되었을 때
    // public void saveUserToken(Users user, String jwtToken) {
    //     log.info("Called saveToken() Timestamp: {}", new Date(System.currentTimeMillis()).toString());
    //     var u = usersRepository.findByUsersName(user.getUsersName()).get();
    //     u.setUsersToken(jwtToken);
    //     u.setUsersUpdatedTime(LocalDateTime.now());
    //     usersRepository.save(u);
    // }
    public void saveUserToken(Users user, String jwtToken) {
        log.info("Called saveToken() Timestamp: {}", new Date(System.currentTimeMillis()).toString());
        var u = usersRepository.findByUsersName(user.getUsersName()).get();

        if(u.getTokens() == null) {
            Token t = new Token();
            t.setToken(jwtToken);
            t.setExpired(false);
            t.setRevoked(false);
            t.setUpdatedDate(LocalDateTime.now());
            t.setUser(u);
            tokenRepository.save(t);
        } else {
            Token t = u.getTokens();
            t.setToken(jwtToken);
            t.setExpired(false);
            t.setRevoked(false);
            t.setUpdatedDate(LocalDateTime.now());
            t.setUser(u);
            tokenRepository.save(t);
        }        
    }

}
