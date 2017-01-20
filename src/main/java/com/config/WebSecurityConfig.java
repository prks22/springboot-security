package com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired
  AuthenticationProvider authenticationProvider;

  @Override
  protected void configure(final HttpSecurity http) throws Exception {
    http.authorizeRequests().antMatchers("/", "/home", "/login").permitAll().anyRequest().authenticated().and()
        .formLogin().defaultSuccessUrl("/dasboard").loginPage("/login").permitAll().and().logout()
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout1")).logoutSuccessUrl("/login");
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
    http.sessionManagement().maximumSessions(5);


  }

  @Autowired
  public void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(authenticationProvider);

  }

  @Bean
  public HttpSessionEventPublisher httpSessionEventPublisher() {
    return new HttpSessionEventPublisher();
  }
}
