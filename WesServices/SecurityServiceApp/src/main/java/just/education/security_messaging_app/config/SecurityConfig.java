package just.education.security_messaging_app.config;

import com.nimbusds.jwt.proc.JWTProcessor;
import org.springframework.http.HttpMethod;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;

import just.education.security_messaging_app.util.JwtHelper;
import just.education.security_messaging_app.repository.UserRepository;
import just.education.security_messaging_app.security.JwtAuthenticationFilter;
import just.education.security_messaging_app.security.DomainUserDetailsService;
import just.education.security_messaging_app.repository.MongoRegisteredClientRepository;
import just.education.security_messaging_app.repository.MongoDbRegisteredClientRepository;


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

    /* DAO Auth Provider by Spring */
    @Bean
    public AuthenticationProvider daoAuthenticationProvider(
            @Qualifier(value = "domainUserDetailService") UserDetailsService userDetailsService
    ) {

        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(this.passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);

        return daoAuthenticationProvider;
    }

    /* JWTProcessor by Nimbus */
    @Bean
    public JWTProcessor<SecurityContext> jwtProcessor() {

        return new DefaultJWTProcessor<>();
    }

    /* JWTProcessor by Nimbus */
    @Bean
    public JwtDecoder jwtDecoder() {

        return new NimbusJwtDecoder(this.jwtProcessor());
    }

    /* JWT Auth Provider by Spring */
    @Bean
    public AuthenticationProvider jwtAuthenticationProvider() {

        return new JwtAuthenticationProvider(this.jwtDecoder());
    }

    /* Multiple Auth manager */
    @Bean
    public AuthenticationManager authenticationManager(DaoAuthenticationProvider daoAuthenticationProvider) {

        return new ProviderManager(daoAuthenticationProvider, jwtAuthenticationProvider());
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
    public RegisteredClientRepository registeredClientRepository(MongoDbRegisteredClientRepository mongoDbRegisteredClientRepository) {

        return new MongoRegisteredClientRepository(mongoDbRegisteredClientRepository);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .httpBasic(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .oauth2Client(oauth2ClientCustomizer -> oauth2ClientCustomizer
                        .authorizedClientService())
                .authorizeHttpRequests(authorizeHttpRequestsCustomizer -> authorizeHttpRequestsCustomizer
                        .requestMatchers(HttpMethod.GET, "/signin**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/test/secured").authenticated()
                        .requestMatchers(HttpMethod.GET, "/test/unsecured").permitAll()
                        .anyRequest().authenticated()
                );

        return httpSecurity.build();
    }
}