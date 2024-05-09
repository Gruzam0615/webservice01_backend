package com.gruzam0615.webservice01.sign;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.gruzam0615.webservice01.users.entity.Users;
import com.gruzam0615.webservice01.users.repository.UsersRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class SignOutService {
    
    private final UsersRepository usersRepository;

    public void revokeTokens(Users user) {
        log.info("Called revokeToken() Timestamp: {}", new Date(System.currentTimeMillis()).toString());
        var u = usersRepository.findByUsersName(user.getUsersName()).get();
        // if(u.getUsersToken().isEmpty() || u.getUsersToken2().isEmpty()) return;
        u.setUsersToken(null);
        u.setUsersToken2(null);
        u.setUsersUpdatedTime(LocalDateTime.now());
        usersRepository.save(u);
    }

}
