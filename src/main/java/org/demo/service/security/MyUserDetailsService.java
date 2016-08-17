package org.demo.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Qualifier("userDetailsService")
public class MyUserDetailsService implements UserDetailsService{
    @Autowired
    private MyUserService myUserService;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return myUserService.loadUserByUsername(s);
    }
}
