package org.demo.service;

import org.demo.dao.security.UserDao;
import org.demo.entity.security.MyUser;
import org.demo.entity.security.MyUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
