package com.jeyson.Users.Infrastructure.Config;

import com.jeyson.Users.Domain.Constants.Security.Permission;
import com.jeyson.Users.Infrastructure.Utilities.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthenticationProvider authenticationProvider;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(AuthenticationProvider authenticationProvider,
                          JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.authenticationProvider = authenticationProvider;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionMan -> sessionMan.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .headers(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests( auth -> {
                    auth.requestMatchers(antMatcher("/auth/login")).permitAll();
                    auth.requestMatchers(antMatcher("/auth/register-customer")).permitAll();
                    auth.requestMatchers(antMatcher("/auth/register-admin")).hasAuthority(Permission.CREATE_ADMIN.name());
                    auth.requestMatchers(antMatcher("/auth/register-librarian")).hasAuthority(Permission.CREATE_LIBRARIAN.name());
                    auth.requestMatchers(antMatcher( "/users")).hasAuthority(Permission.GET_ALL_USERS.name());
                    auth.requestMatchers(antMatcher( "/users/{id}")).hasAuthority(Permission.GET_ONE_USER.name());
                    auth.requestMatchers(antMatcher( "/users/filter/**")).hasAuthority(Permission.GET_ALL_USERS.name());
                    auth.requestMatchers(antMatcher( "/users/update/{id}")).hasAuthority(Permission.UPDATE_USERS.name());
                    auth.anyRequest().authenticated();
                });

        return http.build();
    }
}
