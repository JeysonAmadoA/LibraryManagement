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
                    auth.requestMatchers(antMatcher("/v3/api-docs/**")).permitAll();
                    auth.requestMatchers(antMatcher("/doc/swagger-ui/**")).permitAll();
                    auth.requestMatchers(antMatcher("/auth/login")).permitAll();
                    auth.requestMatchers(antMatcher("/auth/register-customer")).permitAll();
                    auth.requestMatchers(antMatcher("/actuator/**")).hasAuthority(Permission.MONITOR_APP.name());
                    auth.requestMatchers(antMatcher("/auth/register-admin")).hasAuthority(Permission.CREATE_ADMIN.name());
                    auth.requestMatchers(antMatcher("/auth/register-librarian")).hasAuthority(Permission.CREATE_LIBRARIAN.name());
                    auth.requestMatchers(antMatcher( "/users")).hasAuthority(Permission.GET_ALL_USERS.name());
                    auth.requestMatchers(antMatcher( "/users/{id}")).hasAuthority(Permission.GET_ONE_USER.name());
                    auth.requestMatchers(antMatcher( "/users/filter/**")).hasAuthority(Permission.GET_ALL_USERS.name());
                    auth.requestMatchers(antMatcher( "/users/update/{id}")).hasAuthority(Permission.UPDATE_USERS.name());
                    auth.requestMatchers(antMatcher( "/users/delete/{id}")).hasAuthority(Permission.DELETE_USERS.name());
                    auth.requestMatchers(antMatcher( "/books/store")).hasAuthority(Permission.CREATE_BOOK.name());
                    auth.requestMatchers(antMatcher( "/books/update/{id}")).hasAuthority(Permission.UPDATE_BOOK.name());
                    auth.requestMatchers(antMatcher( "/books/delete/{id}")).hasAuthority(Permission.DELETE_BOOK.name());
                    auth.requestMatchers(antMatcher( "/books/{id}")).hasAuthority(Permission.GET_BOOKS.name());
                    auth.requestMatchers(antMatcher( "/books")).hasAuthority(Permission.GET_BOOKS.name());
                    auth.requestMatchers(antMatcher( "/bookcases/store")).hasAuthority(Permission.CREATE_BOOKCASE.name());
                    auth.requestMatchers(antMatcher( "/bookcases/update/{id}")).hasAuthority(Permission.UPDATE_BOOKCASE.name());
                    auth.requestMatchers(antMatcher( "/bookcases/delete/{id}")).hasAuthority(Permission.DELETE_BOOKCASE.name());
                    auth.requestMatchers(antMatcher( "/bookcases/{id}")).hasAuthority(Permission.GET_BOOKCASES.name());
                    auth.requestMatchers(antMatcher( "/bookcases")).hasAuthority(Permission.GET_BOOKCASES.name());
                    auth.requestMatchers(antMatcher( "/rent/store")).hasAuthority(Permission.CREATE_RENT.name());
                    auth.requestMatchers(antMatcher( "/rent/return/{id}")).hasAuthority(Permission.UPDATE_RENT.name());
                    auth.requestMatchers(antMatcher( "/rent/update/{id}")).hasAuthority(Permission.UPDATE_RENT.name());
                    auth.requestMatchers(antMatcher( "/rent/delete/{id}")).hasAuthority(Permission.DELETE_RENT.name());
                    auth.requestMatchers(antMatcher( "/rent/{id}")).hasAuthority(Permission.GET_RENT.name());
                    auth.requestMatchers(antMatcher( "/rent")).hasAuthority(Permission.GET_RENT.name());
                    auth.anyRequest().authenticated();
                });

        return http.build();
    }
}
