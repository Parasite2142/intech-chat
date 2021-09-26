package com.example.intechchat.security.services;

import com.example.intechchat.domain.User;
import com.example.intechchat.security.MyUserPrincipal;
import com.example.intechchat.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository
                .findByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException(userName));

        return MyUserPrincipal.create(user);
    }
}