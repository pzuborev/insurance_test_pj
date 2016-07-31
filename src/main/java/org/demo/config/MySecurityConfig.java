package org.demo.config;

import org.demo.filters.RestTokenAuthenticationFilter;
import org.demo.service.MyUserDetailsService;
import org.demo.service.security.MyAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("me").password("123456").roles("USER");
//        auth.inMemoryAuthentication().withUser("admin").password("123456").roles("ADMIN");
//    }

//    @Autowired
//    private DataSource dataSource;
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication().dataSource(dataSource)
//                .usersByUsernameQuery(
//                        "select username, password, 1 enabled  from users where username=?")
//                .authoritiesByUsernameQuery(
//                        "select l.username, r.name as role from userroles r join userrolelink l where l.userroleid = r.id and l.username=?");
//    }

    @Autowired
    @Qualifier("userDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    @Qualifier("authenticationService")
    private MyAuthenticationService authenticationService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
//                .csrf().disable()
//                .headers().frameOptions().sameOrigin()
//                .and()
                .authorizeRequests()
                .antMatchers("/secured/**").access("hasRole('ROLE_ADMIN')")
                .and().formLogin()
                .and().addFilterAfter(restTokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//        http
//                .headers().frameOptions().sameOrigin()
//                .and()
//       //         .addFilterBefore(restTokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
//                .addFilterAfter(restTokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
//                .authorizeRequests()
//                .antMatchers("/rest/*").authenticated();

    }

    @Bean(name = "restTokenAuthenticationFilter")
    public RestTokenAuthenticationFilter restTokenAuthenticationFilter() {
        RestTokenAuthenticationFilter restTokenAuthenticationFilter = new RestTokenAuthenticationFilter();
       // tokenAuthenticationManager.setUserDetailsService(userDetailsService);
        restTokenAuthenticationFilter.setAuthenticationService(authenticationService);// setAuthenticationManager(tokenAuthenticationManager);
        return restTokenAuthenticationFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}