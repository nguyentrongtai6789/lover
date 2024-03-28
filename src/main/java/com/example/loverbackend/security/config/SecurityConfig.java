package com.example.loverbackend.security.config;

import com.example.loverbackend.security.CustomAccessDeniedHandler;
import com.example.loverbackend.security.RestAuthenticationEntryPoint;
import com.example.loverbackend.security.jwt.JwtAuthenticationFilter;
import com.example.loverbackend.service.extend.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

    @Autowired
    private AccountService accountService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public RestAuthenticationEntryPoint restServicesEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(accountService).passwordEncoder(new BCryptPasswordEncoder(10));
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
        return http.csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(auth -> auth
                                .antMatchers("/**").permitAll()
                                .antMatchers( "/api/login").permitAll()
                                .antMatchers( "/api/findAll").permitAll()
                                .antMatchers( "/api/sendCodeToEmail/**").permitAll()
                                .antMatchers( "/api/sendCodeToEmail2/**").permitAll()
                                .antMatchers( "/api/createNewAccount/**").permitAll()
//                        .requestMatchers(HttpMethod.GET).hasAnyRole("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
//                        .requestMatchers(HttpMethod.DELETE, "/categories",
//                                "/typeOfQuestions",
//                                "/questions",
//                                "/answers",
//                                "/quizzes",
//                                "/hello").hasAnyAuthority("ROLE_ADMIN")
//                        .requestMatchers(HttpMethod.PUT, "/users").hasAnyAuthority("ROLE_USER")
                )
                .exceptionHandling(customizer -> customizer.accessDeniedHandler(customAccessDeniedHandler()))
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}