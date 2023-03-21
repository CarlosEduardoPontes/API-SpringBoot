package com.ITA.Agil.demo.config;

import com.ITA.Agil.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Lazy
    private UsuarioService usuarioService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(usuarioService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                // Autorizações Endpoints API Usuários
                .antMatchers(HttpMethod.POST, "/api/usuarios/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/usuarios/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/usuarios/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/usuarios/**").hasRole("ADMIN")
                // Fim lista de autorizações API Usuários
                .and()
                    .httpBasic();
    }
}
