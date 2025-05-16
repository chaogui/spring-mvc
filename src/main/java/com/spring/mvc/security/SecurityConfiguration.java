package com.spring.mvc.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;
import java.util.List;

@Configuration
public class SecurityConfiguration {

    // once defined the userDetailsManager here, user/pass in properties file will be ignored
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        UserDetails userDetails1 = User.builder()
                .username("employee")
                .password("{noop}password")
                .roles("employee")
                .build();
        UserDetails userDetails2 = User.builder()
                .username("student")
                .password("{noop}password")
                .roles("student")
                .build();
        UserDetails userDetails3 = User.builder()
                .username("customer")
                .password("{noop}password")
                .roles("customer")
                .build();
        UserDetails userDetails4 = User.builder()
                .username("manager")
                .password("{noop}password")
                .roles("Manager")
                .build();
        return new InMemoryUserDetailsManager(userDetails1, userDetails2, userDetails3, userDetails4);
    }

    @Bean
    public UserDetailsManager jdbcUserDetailsManager(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public UserDetailsManager jdbcCustomUserDetailsManager(DataSource dataSource){
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
        userDetailsManager.setUsersByUsernameQuery("select user_id, pw, active from members where user_id = ?");
        userDetailsManager.setAuthoritiesByUsernameQuery("select user_id, role from roles where user_id = ?");
        return userDetailsManager;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, DataSource dataSource) throws Exception {
        DaoAuthenticationProvider inMemoryProvider = new DaoAuthenticationProvider();
        inMemoryProvider.setUserDetailsService(inMemoryUserDetailsManager());

        DaoAuthenticationProvider jdbcProvider = new DaoAuthenticationProvider();
        jdbcProvider.setUserDetailsService(jdbcUserDetailsManager(dataSource));

        DaoAuthenticationProvider customProvider = new DaoAuthenticationProvider();
        jdbcProvider.setUserDetailsService(jdbcCustomUserDetailsManager(dataSource));

        return new ProviderManager(List.of(inMemoryProvider, jdbcProvider, customProvider));
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(
                        configurer
                                -> configurer
                                .requestMatchers("/employees/**").hasAnyRole("employee", "Manager")
                                .requestMatchers("/students/**").hasRole("student")
                                .requestMatchers("/customers/**").hasRole("customer")
                                .anyRequest()
                                .authenticated()
                )

                .formLogin(
                        form
                        -> form.loginPage("/login")
                        .loginProcessingUrl("/authenticateTheUser")
                        .permitAll()
                )
                .logout(
                        logout -> logout.permitAll()
                )
                .exceptionHandling(
                        configurer -> configurer.accessDeniedPage("/access-denied")
                );
        return httpSecurity.build();
    }
}
