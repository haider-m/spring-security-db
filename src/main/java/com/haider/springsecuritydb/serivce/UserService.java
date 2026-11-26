package com.haider.springsecuritydb.serivce;

import com.haider.springsecuritydb.repository.api.UserDetail;
import com.haider.springsecuritydb.repository.api.UserDetailRepository;
import lombok.Data;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Data
@Service
public class UserService implements UserDetailsService {

    private final UserDetailRepository userDetailRepository;

    public UserDetail findByUsername(String username) {

        return Optional.of(userDetailRepository)
                .map(repo -> repo.findByUsernameAndIsActive(username, true))
                .orElseThrow(() -> new UsernameNotFoundException("UserDetailRepository not found or user not active"));

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetail byUsername = findByUsername(username);

        return User
                .builder()
                .username(byUsername.getUsername())
                .password(byUsername.getPassword())
                .authorities(Collections.emptyList())
                .build();
    }
}
