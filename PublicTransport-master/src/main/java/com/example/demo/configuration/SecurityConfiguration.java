package com.example.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsService userDetailsService()
    {
        BCryptPasswordEncoder bCryptPasswordEncoder= new BCryptPasswordEncoder();

        UserDetails user1 = User.builder().username("user1")
                .password(passwordEncoder().encode("123456"))//123456
                .authorities("USER")
                .roles("USER")
                .build();
        UserDetails admin = User.builder().username("ibrahim")
                .password(passwordEncoder().encode("34567"))//34567
                .authorities("USER","ADMIN")
                .roles("USER","ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user1,admin);
    }
    @Bean
    public AuthenticationManager authenticationManager(
            UserDetailsService userDetailsService, PasswordEncoder passwordEncoder)
    {
        // ProviderManager providerManager = new ProviderManager();
        DaoAuthenticationProvider authenticationConfigurer = new DaoAuthenticationProvider();
        authenticationConfigurer.setUserDetailsService(userDetailsService);
        authenticationConfigurer.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(authenticationConfigurer);
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity
            , AuthenticationManager authenticationManager, UserDetailsService userDetailsService) throws Exception {

        httpSecurity.httpBasic(Customizer.withDefaults())
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .authorizeHttpRequests(authrize -> authrize

                        .requestMatchers(HttpMethod.GET)
                        .hasAnyRole("USER")
                        .requestMatchers(HttpMethod.POST)
                        .hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT)
                        .hasAnyAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE)
                        .hasAnyAuthority("ADMIN")
                        .anyRequest().authenticated()).csrf(csrf -> csrf.disable())
                        .securityMatcher("/api/students/**");



        DefaultSecurityFilterChain build= httpSecurity.build();
        return build;
    }
}
