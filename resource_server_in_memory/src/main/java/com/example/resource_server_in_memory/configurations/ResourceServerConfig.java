package com.example.resource_server_in_memory.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ResourceServerConfig {

      @Bean
      public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http
                  .securityMatcher("/messages/**")
                  .authorizeHttpRequests(
                        (authorize) ->
                              authorize
                                    .requestMatchers("/messages/**")
                                    .hasAuthority("SCOPE_message.read")
                                    .anyRequest().denyAll()
                  )
                  .oauth2ResourceServer(oauth2 -> oauth2.jwt());
            return http.build();
      }
}