//package org.demo.service;
//
//import org.demo.dao.security.UserDao;
//import org.demo.dto.TokenDto;
//import org.demo.dto.UserDto;
//import org.demo.entity.security.MyToken;
//import org.demo.entity.security.MyUser;
//import org.demo.exception.ApiException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.UUID;
//
//@Service
//public class RestAuthService {
//
//    @Autowired
//    private UserDao userDao;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    public void setUserDao(UserDao userDao) {
//        this.userDao = userDao;
//    }
//
//    @Transactional
//    public TokenDto getToken(UserDto userDto) throws ApiException {
//        //проверка пользователя
//        MyUser user = userDao.getByUserName(userDto.getUsername());
//        if (user == null) throw new UsernameNotFoundException(userDto.getUsername());
//
//        System.out.println("****************************************");
//        if (!passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
//            throw new ApiException(
//                    String.format("Invalid password %s. %s",
//                            userDto.getPassword(),
//                            passwordEncoder.encode(userDto.getPassword())
//                    ));
//        }
//        //формирование токена
//        TokenDto tokenDto = new TokenDto();
//        tokenDto.setToken(UUID.randomUUID().toString());
//
//        System.out.println(tokenDto.toString());
//        //сохранение токена в БД
//        MyToken token = new MyToken(user.getUsername(), tokenDto.getToken());
//
//        //возвращаем токен
//        return tokenDto;
//
//    }
//}
