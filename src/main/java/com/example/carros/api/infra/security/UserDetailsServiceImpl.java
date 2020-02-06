package com.example.carros.api.infra.security;

import com.example.carros.api.users.UserAccount;
import com.example.carros.api.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        UserAccount user = userRepository.findByLogin(userName);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }
}
