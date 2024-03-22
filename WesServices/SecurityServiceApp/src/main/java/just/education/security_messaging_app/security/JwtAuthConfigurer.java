package just.education.security_messaging_app.security;

import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;


import just.education.security_messaging_app.util.JwtHelper;


public class JwtAuthConfigurer extends AbstractHttpConfigurer<JwtAuthConfigurer, HttpSecurity> {

    private AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> tokenAuthenticationUserDetailsService;
    private JwtHelper jwtHelper;


    public JwtAuthConfigurer(JwtHelper jwtHelper) {
        this.jwtHelper = jwtHelper;
    }


    public JwtAuthConfigurer(
            AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> tokenAuthenticationUserDetailsService,
            JwtHelper jwtHelper
    ) {

        this.tokenAuthenticationUserDetailsService = tokenAuthenticationUserDetailsService;
        this.jwtHelper = jwtHelper;
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {


        AuthenticationManager authenticationManager = httpSecurity.getSharedObject(AuthenticationManager.class);

        AuthenticationConverter authenticationConverter = new JwtAuthConverter(jwtHelper);
        AuthenticationFilter jwtAuthenticationFilter = new AuthenticationFilter(authenticationManager, authenticationConverter);


        PreAuthenticatedAuthenticationProvider preAuthenticatedAuthenticationProvider = new PreAuthenticatedAuthenticationProvider();
        preAuthenticatedAuthenticationProvider.setPreAuthenticatedUserDetailsService(tokenAuthenticationUserDetailsService);

        httpSecurity
                .addFilterBefore(jwtAuthenticationFilter, CsrfFilter.class)
                .authenticationProvider(preAuthenticatedAuthenticationProvider)
    }
}
