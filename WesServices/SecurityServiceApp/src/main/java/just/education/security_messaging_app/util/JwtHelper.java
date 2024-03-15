package just.education.security_messaging_app.util;

import java.util.Map;
import java.util.List;
import java.util.Date;
import java.util.HashMap;
import java.time.Instant;
import java.text.ParseException;
import java.util.stream.Collectors;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.SignedJWT;
import com.nimbusds.jwt.JWTParser;
import com.nimbusds.jwt.JWTClaimsSet;

import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JOSEObjectType;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.crypto.MACVerifier;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class JwtHelper {

    private String accessTokenSecretKey;
    private String refreshTokenSecretKey;
    private long accessTokenExpiration;
    private long refreshTokenExpiration;


    public JwtHelper(String accessTokenSecretKey, String refreshTokenSecretKey, long accessTokenExpiration, long refreshTokenExpiration
    ) {
        this.accessTokenSecretKey = accessTokenSecretKey;
        this.refreshTokenSecretKey = refreshTokenSecretKey;
        this.accessTokenExpiration = accessTokenExpiration;
        this.refreshTokenExpiration = refreshTokenExpiration;
    }


    public String generateRefreshToken(String username) throws ParseException, JOSEException {

        return this.buildToken(new HashMap<>(), username, this.refreshTokenSecretKey, this.refreshTokenExpiration);
    }

    public String generateAccessToken(String username) throws ParseException, JOSEException {

        return this.buildToken(new HashMap<>(), username, this.accessTokenSecretKey, this.accessTokenExpiration);
    }

    public String generateAccessToken(UserDetails userDetails) throws ParseException, JOSEException {

        String username = userDetails.getUsername();

        List<String> authorities  = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        Map<String, Object> claims = new HashMap<>();
        claims.put("permissions", authorities);

        return this.buildToken(claims, username, this.accessTokenSecretKey, this.accessTokenExpiration);
    }

    private String buildToken(Map<String, Object> claims, String subject, String secretKey, long expiration) throws ParseException, JOSEException {

        JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.HS256)
                .type(JOSEObjectType.JWT)
                .build();

        JWTClaimsSet jwtClaimsSet = JWTClaimsSet.parse(claims);

        JWTClaimsSet payload = new JWTClaimsSet.Builder(jwtClaimsSet)
                .subject(subject)
                .expirationTime(new Date(System.currentTimeMillis() + expiration))
                .build();

        SignedJWT jwtToken = new SignedJWT(header, payload);

        jwtToken.sign(this.getSigner(secretKey));

        String token = jwtToken.serialize();

        return token;
    }

    public boolean validateRefreshToken(String token) {

        return this.validateToken(token, this.refreshTokenSecretKey);
    }

    public boolean validateAccessToken(String token) {

        return this.validateToken(token, this.accessTokenSecretKey);
    }

    private boolean validateToken(String token, String secretKey) {

        try {

            boolean isExpired = this.isTokenExpired(token);
            boolean isVerified = this.parseToken(token).verify(this.getVerifier(this.accessTokenSecretKey));

            return isExpired && isVerified;

        } catch (ParseException | JOSEException exception) {

            /* exceptions need to be logged */
            return false;
        }
    }

    public boolean isTokenExpired(String token) throws ParseException {

        return this.extractExpiration(token).before(Date.from(Instant.now()));
    }

    public Date extractExpiration(String token) throws ParseException {

        return this.getJwtClaims(token).getExpirationTime();
    }

    public String extractId(String token) throws ParseException {

        return this.getJwtClaims(token).getJWTID();
    }

    public String extractUsername(String token) throws ParseException {

        return this.getJwtClaims(token).getSubject();
    }

    public String extractIssuer(String token) throws ParseException {

        return this.getJwtClaims(token).getIssuer();
    }

    public Date extractIssuedAt(String token) throws ParseException {

        return this.getJwtClaims(token).getIssueTime();
    }

    public JWTClaimsSet getJwtClaims(String token) throws ParseException {

        return this.parseToken(token).getJWTClaimsSet();
    }

    private SignedJWT parseToken(String token) throws ParseException {

        return SignedJWT.parse(token);
    }

    private JWSSigner getSigner(String secretKey) throws KeyLengthException {

        return new MACSigner(secretKey);
    }

    private JWSVerifier getVerifier(String secretKey) throws JOSEException {

        return new MACVerifier(secretKey);
    }

    public JWT parse(String token) throws ParseException {

        return JWTParser.parse(token);
    }

    public Jwt createJwt(String token, JWT parsedJwt) throws ParseException {

        JWTClaimsSet jwtClaimsSet = this.getJwtClaims(token);

        Map<String, Object> headers = new HashMap<>(parsedJwt.getHeader().toJSONObject());
        Map<String, Object> claims = new HashMap<>(jwtClaimsSet.getClaims());

        Instant expiresAt = (Instant) claims.get(JwtClaimNames.EXP);
        Instant issuedAt = (Instant) claims.get(JwtClaimNames.IAT);

        return new Jwt(token, issuedAt, expiresAt, headers, claims);
    }
}