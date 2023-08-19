package com.dzhanrafetov.melifera.configuration.security_configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity

public class  SecurityConfig {

    private final UserDetailsService userDetailsServiceSecurity;

    public SecurityConfig(UserDetailsService userDetailsServiceSecurity) {
        this.userDetailsServiceSecurity = userDetailsServiceSecurity;
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsServiceSecurity);

        return authenticationManagerBuilder.build();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        // .antMatchers(HttpMethod.GET, "/v1/advertisement/**").permitAll()  ?
        httpSecurity.csrf().disable()
                .httpBasic()
                .and()
                //from most to less

                .authorizeRequests()
//This is for developing period///
//                .antMatchers("/v1/user/admin/**")
//                .hasRole("ADMIN")

                .antMatchers("/v1/user/")
                .permitAll()

//                .antMatchers("/v1/user/**")
//                .hasAnyRole("ADMIN","USER")
//

                .antMatchers("/v1/advertisement/admin/**")
                .hasRole("ADMIN")

                .antMatchers("/v1/advertisement/all/**")
                .permitAll()

                .antMatchers("/v1/advertisement/**")
                .hasAnyRole("ADMIN","USER")


                .antMatchers("/v1/category/admin/**")
                .hasRole("ADMIN")

                .antMatchers("/v1/category/categories/**")
                .permitAll()


                .antMatchers(HttpMethod.GET,"/v1/image/**")
                .permitAll()


                .antMatchers("/v1/image/**")
                .hasAnyRole("ADMIN","USER")
                .antMatchers("/v1/email/sendmail/")
                .hasRole("ADMIN")


                .antMatchers("/v1/userdetails/**")
                .hasAnyRole("ADMIN","USER")

                .anyRequest()
                .authenticated()
                .and().formLogin();


        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}