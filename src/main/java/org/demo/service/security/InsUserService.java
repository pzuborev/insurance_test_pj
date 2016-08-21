package org.demo.service.security;

import org.demo.dao.security.UserDao;
import org.demo.dto.UserDto;
import org.demo.entity.security.InsUser;
import org.demo.entity.security.InsUserRole;
import org.demo.exception.ApiException;
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
public class InsUserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private InsUserRoleService userRoleService;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    public void create(UserDto userDto) {
        InsUser insUser = new InsUser();
        insUser.setUsername(userDto.getUsername());
        insUser.setPassword(userDto.getPassword());
        if (userDto.getRoles() != null)
            insUser.setInsUserRole(userRoleService.getInsRolesByName(userDto.getRoles()));

        userDao.persist(insUser);
    }

    @Transactional
    public void update(String userName, UserDto userDto) {
        InsUser user = userDao.getByUserName(userName);
        if (user == null) throw new UsernameNotFoundException(userName);

        if (userDto.getUsername() == "") userDto.setUsername(userName);
        else if (!userDto.getUsername().equals(userName))
            throw new ApiException(String.format("You can not change username. Old username '%s'. New username '%s'",
                    userName, userDto.getUsername()));

        //обновляем только то, что задано для обновления
        if (userDto.getRoles() != null)
            user.setInsUserRole(userRoleService.getInsRolesByName(userDto.getRoles()));

        if (userDto.getPassword() != null)
            user.setPassword(userDto.getPassword());

        userDao.update(user);
    }

    @Transactional
    public void delete(String username) {
        InsUser user = new InsUser();
        user.setUsername(username);
        userDao.delete(user);
    }

    @Transactional(readOnly = true)
    public InsUser getByUserName(String username) {
        return userDao.getByUserName(username);
    }

    @Transactional(readOnly = true)
    public UserDetails loadUserByToken(String token) {
        UserDetails userDetails = null;
        InsUser user = userDao.getByToken(token);
        if (user != null) {
            List<GrantedAuthority> authorities = buildUserAuthority(user.getInsUserRole());
            userDetails = buildUserForAuthentication(user, authorities);
        }
        return userDetails;
    }

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String username) {
        InsUser user = userDao.getByUserName(username);

        List<GrantedAuthority> authorities = buildUserAuthority(user.getInsUserRole());
        return buildUserForAuthentication(user, authorities);
    }

    private User buildUserForAuthentication(InsUser user, List<GrantedAuthority> authorities) {
        return new User(user.getUsername(), user.getPassword(), true, true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Set<InsUserRole> insUserRoles) {
        return insUserRoles.stream()
                .map(userRole -> new SimpleGrantedAuthority("ROLE_"+userRole.getName()))
                .collect(Collectors.toList());
    }
}
