package just.education.security_messaging_app.service;

import org.bson.types.ObjectId;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import just.education.security_messaging_app.model.User;
import just.education.security_messaging_app.util.JwtHelper;
import just.education.security_messaging_app.model.RefreshToken;
import just.education.security_messaging_app.dto.RefreshTokenRequest;
import just.education.security_messaging_app.dto.RefreshTokenResponse;
import just.education.security_messaging_app.repository.UserRepository;
import just.education.security_messaging_app.repository.RefreshTokenRepository;



public class RefreshTokenService {

    private RefreshTokenRepository refreshTokenRepository;
    private UserRepository userRepository;
    private JwtHelper jwtHelper;


    public RefreshTokenService() {
    }

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository, UserRepository userRepository, JwtHelper jwtHelper) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
        this.jwtHelper = jwtHelper;
    }


    public RefreshTokenResponse refresh(RefreshTokenRequest request) throws Exception {

        String refreshTokenString = request.getRefreshToken();

        boolean isValid = this.jwtHelper.validateRefreshToken(refreshTokenString);

        String tokenId = this.jwtHelper.extractId(refreshTokenString);
        boolean tokenExists = refreshTokenRepository.existsById(new ObjectId(tokenId));

        if (isValid && tokenExists) {

            // delete previous
            refreshTokenRepository.deleteById(new ObjectId(tokenId));

            // get user
            String username = jwtHelper.extractUsername(refreshTokenString);
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Could not find user (persisted) by token (request)"));

            // new tokens
            String newAccessTokenString = jwtHelper.generateAccessToken(username);
            String newRefreshTokenString = jwtHelper.generateRefreshToken(username);

            // persist refresh token
            RefreshToken refreshToken = new RefreshToken(newRefreshTokenString);
            refreshToken.setUser(user);
            refreshTokenRepository.save(refreshToken);

            return new RefreshTokenResponse(newAccessTokenString, newRefreshTokenString);
        }

        throw new BadCredentialsException("invalid token");
    }
}
