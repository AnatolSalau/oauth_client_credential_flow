package com.example.authorization_server_in_memory.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;

import javax.net.ssl.KeyManager;
import java.util.UUID;

@EnableWebSecurity
@Import(OAuth2AuthorizationServerConfiguration.class)
public class AuthorizationServerConfig {

/*      @Bean
      public RegisteredClientRepository registeredClientRepository() {
            RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
                  .clientId("messaging-client")
                  .clientSecret("{noop}secret")
                  .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                  .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                  .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                  .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                  .redirectUri("http://127.0.0.1:8080/login/oauth2/code/messaging-client-oidc")
                  .redirectUri("http://127.0.0.1:8080/authorized")
                  .scope(OidcScopes.OPENID)
                  .scope(OidcScopes.PROFILE)
                  .scope("message.read")
                  .scope("message.write")
                  .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
                  .build();

            return new InMemoryRegisteredClientRepository(registeredClient);
      }*/
      // @formatter:off
      @Bean
      public RegisteredClientRepository registeredClientRepository() {
            RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
                  .clientId("messaging-client")
                  .clientSecret("secret")
                  .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                  .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                  .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                  .redirectUri("http://localhost:8080/authorized")
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
