package com.ITA.Agil.demo.config;

import com.ITA.Agil.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    @Lazy
    private UsuarioService usuarioService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder managerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        managerBuilder
                .userDetailsService(usuarioService)
                .passwordEncoder(passwordEncoder());
        return managerBuilder.build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                    .authorizeRequests()
                    .antMatchers(HttpMethod.POST, "/api/usuarios/**").permitAll()
                    .antMatchers(HttpMethod.GET, "/api/usuarios/**").hasRole("ADMIN")
                    .antMatchers(HttpMethod.PUT, "/api/usuarios/**").hasRole("ADMIN")
                    .antMatchers(HttpMethod.DELETE, "/api/usuarios/**").hasRole("ADMIN")
                    // começo habilitação do uso do banco de dados H2
                    .antMatchers("/h2-console/**").permitAll()
                .and()
                    .headers().frameOptions().sameOrigin()
                    // fim habilitação do uso do banco de dados H2
                .and()
                    .httpBasic();
        return http.build();
    }
}
