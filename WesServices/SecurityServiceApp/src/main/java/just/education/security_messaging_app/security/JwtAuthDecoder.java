package just.education.security_messaging_app.security;

import java.text.ParseException;

import com.nimbusds.jwt.JWT;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;

import just.education.security_messaging_app.util.JwtHelper;


public class JwtAuthDecoder implements JwtDecoder {

    private JwtHelper jwtHelper;


    public JwtAuthDecoder(JwtHelper jwtHelper) {
        this.jwtHelper = jwtHelper;
    }


    @Override
    public Jwt decode(String token) throws JwtException {

        try {
            JWT jwt = jwtHelper.parse(token);
            Jwt decodedJwt = jwtHelper.createJwt(token, jwt);

            return decodedJwt;

        } catch (ParseException e) {

            throw new RuntimeException(e);
        }
    }
}