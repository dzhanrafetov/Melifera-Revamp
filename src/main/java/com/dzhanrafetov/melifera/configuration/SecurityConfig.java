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

                // Advertisement Controller
                .antMatchers("/v1/advertisement/admin/**").hasRole("ADMIN")
                .antMatchers("/v1/advertisement/all/**").permitAll()
                .antMatchers("/v1/advertisement/**").hasAnyRole("ADMIN", "USER")

                // Advertisement Document Controller
                .antMatchers("/v1/advertisement-documents/**").permitAll()

                // Authentication Controller
                .antMatchers(HttpMethod.POST, "/authenticate").permitAll()
                .antMatchers(HttpMethod.POST, "/logout").permitAll()

                //Category Controller
                .antMatchers("/v1/category/admin/**").hasRole("ADMIN")
                .antMatchers("/v1/category/**").permitAll()

                //CityApi Controller
                .antMatchers("/v1/api/**").permitAll()

                //Email Controller
                .antMatchers("/v1/email/**").hasRole("ADMIN")

                //Image Controller
                .antMatchers(HttpMethod.GET,"/v1/image/**").permitAll()
                .antMatchers("/v1/image/**").hasAnyRole("ADMIN","USER")

                //User Controller
                .antMatchers("/v1/user/admin/**").hasRole("ADMIN")
                .antMatchers("/v1/user/**").permitAll()

                //UserDetails Controller
                .antMatchers("/v1/userdetails/**")
                .hasAnyRole("ADMIN","USER")

                //Others

                .antMatchers(HttpMethod.GET, "/verifyToken").permitAll()

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
