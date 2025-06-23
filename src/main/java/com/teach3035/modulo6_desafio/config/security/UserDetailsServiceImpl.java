package com.teach3035.modulo6_desafio.config.security;

import com.teach3035.modulo6_desafio.model.UserModel;
import com.teach3035.modulo6_desafio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserModel> optionalUser = userRepository.findByUsername(username);
        if(optionalUser.isEmpty())
            throw new UsernameNotFoundException("User not found with username: " + username);
        return optionalUser.get();
    }
}
