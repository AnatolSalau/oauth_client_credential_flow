package com.example.authorization_server_in_memory.configuration;


import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.net.ssl.KeyManager;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

@EnableWebSecurity
@Import(OAuth2AuthorizationServerConfiguration.class)
public class AuthorizationServerConfig {

      @Bean
      public RegisteredClientRepository registeredClientRepository() {
            RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
                  .clientId("messaging-client")
                  .clientSecret("secret")
                  .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                  //.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                  .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                  //.redirectUri("http://localhost:8080/authorized")
                  .scope("message.read")
                  .scope("message.write")
                  .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
                  .build();
            return new InMemoryRegisteredClientRepository(registeredClient);
      }
      // @formatter:on


      @Bean
      public KeyManager keyManager() {
            return new StaticKeyGeneratingKeyManager();
      }

      // @formatter:off
      @Bean
      public UserDetailsService users() {
            UserDetails user = User.withDefaultPasswordEncoder()
                  .username("user1")
                  .password("password")
                  .roles("USER")
                  .build();
            return new InMemoryUserDetailsManager(user);
      }
      // @formatter:on

}
