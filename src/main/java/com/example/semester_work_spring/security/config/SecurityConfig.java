package com.example.semester_work_spring.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    @Qualifier("customUserDetailsService")
    private UserDetailsService userDetailsService;

    @Override
    protected void configure (AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/signUp").permitAll()
                .antMatchers("/welcome").permitAll()
                .antMatchers("/users").hasAnyAuthority("ADMIN")
                .antMatchers("/profile/admin").hasAnyAuthority("ADMIN")
                .antMatchers("allRecipes").permitAll()
                .antMatchers("/profile").authenticated()
                .antMatchers("/home").authenticated()
                .antMatchers("/recipes/edit").authenticated()
                .antMatchers("/comments/**").authenticated()
                .and()
                .formLogin().loginPage("/logIn")
                .usernameParameter("email")
                .successHandler((request, response, authentication) -> {
                    Set<String> authorities = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
                    if (authorities.contains("ADMIN")) {
                        response.sendRedirect("/profile/admin");
                    } else {
                        response.sendRedirect("/profile/user");
                    }
                })
                .failureUrl("/logIn?error")
                .permitAll();
    }
}
