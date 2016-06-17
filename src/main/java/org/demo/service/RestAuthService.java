package org.demo.service;

import org.demo.dao.UserDao;
import org.demo.dto.TokenDto;
import org.demo.dto.UserDto;

import org.demo.entity.MyUser;
import org.demo.entity.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RestAuthService {

    @Autowired
    private UserDao userDao;

    public TokenDto getToken (UserDto userDto) throws Exception {
        //проверка пользователя
        MyUser user = userDao.getByUserName(userDto.getUsername());
        if (user == null) {
            throw new Exception ("user not found");
        }
        if (user.getPassword() != userDto.getPassword()) {
            throw new Exception ("invalid password");
        }
        //формирование токена
        TokenDto tokenDto = new TokenDto();
        tokenDto.setToken(UUID.randomUUID().toString());

        System.out.println(tokenDto.toString());
        //сохранение токена в БД
        Token token = new Token();
        token.setToken(tokenDto.getToken());
        token.setUser(user);

        //возвращаем токен
        return tokenDto;
    }
}
