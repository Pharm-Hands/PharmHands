package com.pharmhands;

import com.pharmhands.services.UserDetailsLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private UserDetailsLoader usersLoader;

    public SecurityConfiguration(UserDetailsLoader usersLoader) {
        this.usersLoader = usersLoader;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(usersLoader) // How to find users by their username
                .passwordEncoder(passwordEncoder()) // How to encode and verify passwords
        ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                /* Login configuration */
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .permitAll() // Anyone can go to the login page
                /* Logout configuration */
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout") // append a query string value
                /* Pages that can be viewed without having to log in */
                .and()
                .authorizeRequests()
                .antMatchers("/", "/about") // anyone can see the home page
                .permitAll()
                /* Pages that require authentication */
//              Pages for doctors
                .and()
                .authorizeRequests()
                .antMatchers("/doctorProfile/?", "/doctorProfile/?/*", "/prescription/?*")
                .hasRole("DOCTOR")
//              Pages for Patients
                .and()
                .authorizeRequests()
                .antMatchers("/patientProfile/?", "/patientProfile/?/*", "/prescription-request/?")
                .hasRole("PATIENT")
//              Pages for Pharmacists
                .and()
                .authorizeRequests()
                .antMatchers("/pharmacistProfile/?", "/pharmacistProfile/?/*", "/prescription/?*", "/prescription/*")
                .hasRole("PHARMACIST")
//              Prescription View
                .and()
                .authorizeRequests()
                .antMatchers("/prescription/?*")
                .hasAnyRole("PHARMACIST", "DOCTOR")
        ;
    }
}
