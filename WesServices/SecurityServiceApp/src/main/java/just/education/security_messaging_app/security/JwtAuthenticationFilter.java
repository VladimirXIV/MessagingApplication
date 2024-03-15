package just.education.security_messaging_app.security;

import java.io.IOException;
import java.text.ParseException;
import java.util.Collection;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import just.education.security_messaging_app.util.JwtHelper;


public class JwtAuthenticationFilter extends OncePerRequestFilter {


    private final UserDetailsService domainUserDetailsService;
    private final JwtHelper jwtHelper;


    public JwtAuthenticationFilter(UserDetailsService domainUserDetailsService, JwtHelper jwtHelper) {
        this.domainUserDetailsService = domainUserDetailsService;
        this.jwtHelper = jwtHelper;
    }


    @Override
    protected void doFilterInternal(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            FilterChain filterChain) throws ServletException, IOException {

        String token = this.getJWTFromRequest(httpServletRequest);

        if (StringUtils.hasText(token) && jwtHelper.validateAccessToken(token)) {

            String username = null;
            try {
                username = jwtHelper.extractUsername(token);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            UserDetails userDetails = this.domainUserDetailsService.loadUserByUsername(username);
            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

            //
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private String getJWTFromRequest(HttpServletRequest request) {

        final String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }
}