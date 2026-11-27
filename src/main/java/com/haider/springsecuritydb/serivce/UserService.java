package com.haider.springsecuritydb.serivce;

import com.haider.springsecuritydb.repository.api.UserDetailDO;
import com.haider.springsecuritydb.repository.UserDetailRepository;
import lombok.Data;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Data
@Service
public class UserService implements UserDetailsService {

    private final UserDetailRepository userDetailRepository;

    private final PasswordEncoder passwordEncoder;

    public void addUser(String username, String password) {
        UserDetailDO userDetailDO = new UserDetailDO();
        userDetailDO.setUsername(username);
        userDetailDO.setPassword(passwordEncoder.encode(password));
        userDetailDO.setIsActive(true);
        userDetailRepository.save(userDetailDO);
    }

    public UserDetailDO findByUsername(String username) {

        return Optional.of(userDetailRepository)
                .map(repo -> repo.findByUsernameAndIsActive(username, true))
                .orElseThrow(() -> new UsernameNotFoundException("UserDetailRepository not found or user not active"));

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetailDO byUsername = findByUsername(username);

        return User
                .builder()
                .username(byUsername.getUsername())
                .password(byUsername.getPassword())
                .authorities(Collections.emptyList())
                .build();
    }
}
