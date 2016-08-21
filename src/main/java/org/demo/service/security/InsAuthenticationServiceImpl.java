package org.demo.service.security;

import org.demo.dao.security.TokenDao;
import org.demo.entity.security.InsToken;
import org.demo.exception.TokenNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.UUID;

@Service
@Qualifier("authenticationService")
public class InsAuthenticationServiceImpl implements InsAuthenticationService {

    @Autowired
    TokenDao tokenDao;
    @Autowired
    InsUserService insUserService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    final static Logger logger = Logger.getLogger(InsAuthenticationServiceImpl.class);


    private String newTokenValue () {
        return UUID.randomUUID().toString();
    }

    @Override
    @Transactional
    public TokenDetails authenticate(String username, String password) {
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(username, password);
            authentication = authenticationManager.authenticate(authentication);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            if (authentication.getPrincipal() != null) {
                UserDetails userContext = (UserDetails) authentication.getPrincipal();
                TokenDetails newToken = new TokenDetails(newTokenValue(), userContext);

                if (newToken == null) return null;

                InsToken tokenObj = new InsToken(username, newToken.getToken());
                tokenDao.persist(tokenObj);

                logger.debug("*** authentication successful has finished ");
                return newToken;
            }
        return null;

    }

    @Override
    public boolean checkToken(String token) {
        logger.debug("*** checkToken :: " + token);

        Assert.notNull(tokenDao, "tokenDao is null");

        UserDetails userDetails = insUserService.loadUserByToken(token);
        if (userDetails == null) {
            throw new TokenNotFoundException(token);
        }

        Authentication securityToken =
                new PreAuthenticatedAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(securityToken);
        return true;
    }

    @Override
    @Transactional
    public void logout(String token) {
        logger.debug("*** logout");
        tokenDao.delete(new InsToken(token));
        SecurityContextHolder.clearContext();
    }

    @Override
    public UserDetails currentUser() {
        return null;
    }
}
