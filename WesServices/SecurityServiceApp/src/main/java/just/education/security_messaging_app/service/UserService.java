package just.education.security_messaging_app.service;

import java.text.ParseException;
import java.util.UUID;

import com.nimbusds.jose.JOSEException;
import just.education.security_messaging_app.dto.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import just.education.security_messaging_app.model.User;
import just.education.security_messaging_app.util.JwtHelper;
import just.education.security_messaging_app.mapper.UserMapper;
import just.education.security_messaging_app.exception.AppException;
import just.education.security_messaging_app.repository.UserRepository;


public class UserService {

    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private JwtHelper jwtHelper;
    private UserMapper userMapper;


    public UserService() {
    }

    public UserService(
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            UserRepository userRepository,
            JwtHelper jwtHelper,
            UserMapper userMapper
    ) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.jwtHelper = jwtHelper;
        this.userMapper = userMapper;
    }

    public UserRegisterResponse signUp(UserRegisterRequest request) {

        String rawPassword = request.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);

        User newUser = userMapper.toUser(request);
        newUser.setId(UUID.randomUUID());
        newUser.setPassword(encodedPassword);

        User savedUser = userRepository.save(newUser);

        UserRegisterResponse response = userMapper.toUserRegisterResponse(savedUser);

        return response;
    }

    public UserLoginResponse signIn(UserLoginRequest request) throws AppException, ParseException, JOSEException {

        String username = request.getUsername();
        String password = request.getPassword();

        Authentication authenticate = null;
        try {
            authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (BadCredentialsException bcexp) {
            throw new AppException(HttpStatus.UNAUTHORIZED.value(), "Login/password is incorrect");
        }

        String accessTokenString = jwtHelper.generateAccessToken(username);
        String refreshTokenString = jwtHelper.generateRefreshToken(username);

        UserLoginResponse response = new UserLoginResponse(username, accessTokenString, refreshTokenString);

        return response;
    }
}