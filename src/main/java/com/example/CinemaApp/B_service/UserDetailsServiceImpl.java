package com.example.CinemaApp.B_service;

import com.example.CinemaApp.C_repository.UserRepository;
import com.example.CinemaApp.F_exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //imi caut userul in baza de date dupa nume cu ajutorul lui userRepository
        com.example.CinemaApp.D_entities.User user = userRepository.findUserByName(userName).orElseThrow(()-> new ResourceNotFoundException("user not found"));
        //imi returnez un user din interfata UserDetails care este un user din interfata sping security
        //return new org.springframework.security.core.userdetails.User(user.getName(),user.getPassword(), buildSimpleGrantedAuthorities(user));
        return null;
    }

    @Transactional
    public List<SimpleGrantedAuthority> buildSimpleGrantedAuthorities(User user) {
//        return user.getRole.stream()
//                .map(role -> new SimpleGrantedAuthority(role.getRoleType().name()))
//                .collect(Collectors.toList());
//
//    }
        return null;
    }

}
