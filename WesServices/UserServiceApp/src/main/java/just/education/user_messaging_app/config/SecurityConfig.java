package just.education.user_messaging_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import just.education.user_messaging_app.converter.UserInfoJwtAuthenticationConverter;


@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .authorizeHttpRequests(authorizeHttpRequestsCustomizer -> authorizeHttpRequestsCustomizer
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(
                        httpSecurityOAuth2ResourceServerConfigurer -> httpSecurityOAuth2ResourceServerConfigurer
                        .jwt(
                                jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(new UserInfoJwtAuthenticationConverter())
                        )
                        .opaqueToken(
                                opaqueTokenConfigurer -> opaqueTokenConfigurer
                                        .introspectionUri("")
                                        .introspectionClientCredentials("", "")
                        )

                );

        return httpSecurity.build();
    }
}