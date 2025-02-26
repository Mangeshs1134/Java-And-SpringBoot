package com.myproject.myJournalProject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.myproject.myJournalProject.entity.User;
import com.myproject.myJournalProject.repository.UserRepository;

@Service
@Component
public class UserSecurityService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    @Autowired
    private UserService userService;

    public UserSecurityService(UserRepository userRepository , @Lazy PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        System.out.println("-------------------------------------------------------------------------------------------------------------------------load user name------------------------------------------------------------------------------------------------------------------------------------------------" + username + "---" );
        Optional <User> userOpt = Optional.of(userRepository.findByUsername(username));
       if (userOpt.isEmpty()) {
            
            throw new UsernameNotFoundException("User not Found");
       }
       User user = userOpt.get();
       System.out.println(user.getUsername() +  "------------------------------------"+ user.getPassword());
       return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRoles().toArray(new String[0]))
                .build();


    }


    // @Override
    // public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
    //     return users.stream().filter(user-> user.getUsername().equals(username))
    //         .findFirst()
    //         .orElseThrow(()-> new UsernameNotFoundException("User NOT FOUND"));
    // }
    
    
}
