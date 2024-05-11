package com.example.demo;

import com.example.demo.security.repository.JpaRegisteredClientRepository;
import jakarta.persistence.Access;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;

import java.util.UUID;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    JpaRegisteredClientRepository clientRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void registerClientToMyDatabase(){
        RegisteredClient oidcClient = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId("postman-client")
                .clientSecret("$2a$12$80oHo8Dq1DZlrps7AcVyKe0fzAhr7TFQPBEY2Ci6QuiT6EaHieqOu")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .redirectUri("https://oauth.pstmn.io/v1/callback")
                .postLogoutRedirectUri("https://oauth.pstmn.io/v1/callback")
                .scope(OidcScopes.OPENID)
                .scope(OidcScopes.PROFILE)
                .scope("admin")
                .scope("student")
                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
                .build();

        clientRepository.save(oidcClient);

    }

}
