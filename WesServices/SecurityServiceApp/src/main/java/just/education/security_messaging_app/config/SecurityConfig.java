package just.education.security_messaging_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.http.HttpMethod;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;


import just.education.security_messaging_app.util.JwtHelper;
import just.education.security_messaging_app.security.JwtAuthDecoder;
import just.education.security_messaging_app.repository.UserRepository;
import just.education.security_messaging_app.security.JwtAuthConverter;
import just.education.security_messaging_app.security.JwtAuthenticationFilter;
import just.education.security_messaging_app.security.DomainUserDetailsService;



@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean(name = "domainUserDetailService")
    public UserDetailsService domainUserDetailsService(UserRepository userRepository) {

        return new DomainUserDetailsService(userRepository);
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(
            @Qualifier(value = "domainUserDetailService") UserDetailsService userDetailsService
    ) {

        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(this.passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);

        return daoAuthenticationProvider;
    }

    @Bean
    public ProviderManager authenticationManager(DaoAuthenticationProvider daoAuthenticationProvider) {

        return new ProviderManager(daoAuthenticationProvider);
    }

    @Bean
    public JwtHelper jwtHelper(
            @Value("${application.security.jwt.access-token.secret-key}") String accessTokenSecretKey,
            @Value("${application.security.jwt.refresh-token.secret-key}") String refreshTokenSecretKey,
            @Value("${application.security.jwt.access-token.expiration}") long accessTokenExpiration,
            @Value("${application.security.jwt.refresh-token.expiration}") long refreshTokenExpiration
    ) {

        return new JwtHelper(accessTokenSecretKey, refreshTokenSecretKey, accessTokenExpiration, refreshTokenExpiration);
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(
            @Qualifier(value = "domainUserDetailService") UserDetailsService domainUserDetailsService,
            JwtHelper jwtHelper)
    {

        return new JwtAuthenticationFilter(domainUserDetailsService, jwtHelper);
    }

    @Bean
    public JwtAuthDecoder jwtAuthDecoder(JwtHelper jwtHelper) {

        return new JwtAuthDecoder(jwtHelper);
    }

    @Bean
    public JwtAuthConverter jwtAuthConverter() {

        return new JwtAuthConverter();
    }

    @Bean
    public JwtAuthenticationProvider refreshTokenAuthenticationProvider(JwtAuthDecoder jwtAuthDecoder) {

        JwtAuthenticationProvider jwtAuthenticationProvider = new JwtAuthenticationProvider(jwtAuthDecoder);
        jwtAuthenticationProvider.setJwtAuthenticationConverter(this.jwtAuthConverter());

        return jwtAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeHttpRequestsCustomizer -> authorizeHttpRequestsCustomizer
                        .requestMatchers(HttpMethod.GET, "/signin**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/test/secured").authenticated()
                        .requestMatchers(HttpMethod.GET, "/test/unsecured").permitAll()
                        .anyRequest().authenticated()
                );

        return httpSecurity.build();
    }
}