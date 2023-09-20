package com.dzhanrafetov.melifera.configuration;

import com.dzhanrafetov.melifera.security.JwtRequestFilter;
import com.dzhanrafetov.melifera.security.JwtUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtUserDetailsService jwtUserDetailsService;
    private final JwtRequestFilter jwtRequestFilter;

    public SecurityConfig(JwtUserDetailsService jwtUserDetailsService, JwtRequestFilter jwtRequestFilter) {
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/verifyToken").permitAll() // Allow authentication endpoint


                .antMatchers(HttpMethod.POST, "/authenticate").permitAll() // Allow authentication endpoint
                .antMatchers("/v1/user/").permitAll() // Allow registration endpoint

                .antMatchers("/city").permitAll() // Allow registration endpoint
                .antMatchers("/api/v1/user/**").permitAll() // Allow registration endpoint


                // Other antMatchers for endpoints (configure as needed)
                .antMatchers("/v1/advertisement/admin/**").hasRole("ADMIN") // Restrict to ADMIN role

                .antMatchers("/v1/advertisement/**")
                .hasAnyRole("ADMIN", "USER")


                .anyRequest().authenticated() // Require authentication for other endpoints
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // No session management
                .and()
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
