package com.example.client_server_in_memory.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

      @Bean
      public WebSecurityCustomizer webSecurityCustomizer() {
            return (web) -> web.debug(true)
                  .ignoring().requestMatchers("/webjars/**");

      }

      @Bean
      public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http
                  .authorizeHttpRequests(
                        (authorize) ->
                              authorize
                                    .anyRequest().permitAll()
                  )
                  .oauth2Client();
            return http.build();
      }
}
