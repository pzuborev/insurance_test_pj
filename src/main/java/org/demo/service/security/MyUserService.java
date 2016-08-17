package org.demo.service.security;

import org.demo.dao.security.UserDao;
import org.demo.dto.UserDto;
import org.demo.entity.security.MyUser;
import org.demo.entity.security.MyUserRole;
import org.demo.exception.ApiException;
import org.demo.service.security.MyUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MyUserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private MyUserRoleService userRoleService;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    public void create(UserDto userDto) {
        MyUser myUser = new MyUser();
        myUser.setUsername(userDto.getUsername());
        myUser.setPassword(userDto.getPassword());
        if (userDto.getRoles() != null)
            myUser.setMyUserRole(userRoleService.getMyRolesByName(userDto.getRoles()));

        userDao.persist(myUser);
    }

    @Transactional
    public void update(String userName, UserDto userDto) {
        MyUser user = userDao.getByUserName(userName);
        if (user == null) throw new UsernameNotFoundException(userName);

        if (userDto.getUsername() == "") userDto.setUsername(userName);
        else if (!userDto.getUsername().equals(userName))
            throw new ApiException(String.format("You can not change username. Old username '%s'. New username '%s'",
                    userName, userDto.getUsername()));

        //обновляем только то, что задано для обновления
        if (userDto.getRoles() != null)
            user.setMyUserRole(userRoleService.getMyRolesByName(userDto.getRoles()));

        if (userDto.getPassword() != null)
            user.setPassword(userDto.getPassword());

        userDao.update(user);
    }

    @Transactional
    public void delete(String username) {
        MyUser user = new MyUser();
        user.setUsername(username);
        userDao.delete(user);
    }

    @Transactional(readOnly = true)
    public MyUser getByUserName(String username) {
        return userDao.getByUserName(username);
    }

    @Transactional(readOnly = true)
    public UserDetails loadUserByToken(String token) {
        UserDetails userDetails = null;
        MyUser user = userDao.getByToken(token);
        if (user != null) {
            List<GrantedAuthority> authorities = buildUserAuthority(user.getMyUserRole());
            userDetails = buildUserForAuthentication(user, authorities);
        }
        return userDetails;
    }

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String username) {
        MyUser user = userDao.getByUserName(username);

        List<GrantedAuthority> authorities = buildUserAuthority(user.getMyUserRole());
        return buildUserForAuthentication(user, authorities);
    }

    private User buildUserForAuthentication(MyUser user, List<GrantedAuthority> authorities) {
        return new User(user.getUsername(), user.getPassword(), true, true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Set<MyUserRole> myUserRoles) {
        return myUserRoles.stream()
                .map(userRole -> new SimpleGrantedAuthority("ROLE_"+userRole.getName()))
                .collect(Collectors.toList());
    }
}
