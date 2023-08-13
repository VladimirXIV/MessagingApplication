package just.education.security_messaging_app.service;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import just.education.security_messaging_app.model.User;
import just.education.security_messaging_app.util.JWTGenerator;
import just.education.security_messaging_app.mapper.UserMapper;
import just.education.security_messaging_app.dto.UserLoginRequest;
import just.education.security_messaging_app.dto.UserLoginResponse;
import just.education.security_messaging_app.exception.AppException;
import just.education.security_messaging_app.dto.UserRegisterRequest;
import just.education.security_messaging_app.dto.UserRegisterResponse;
import just.education.security_messaging_app.repository.RoleRepository;
import just.education.security_messaging_app.repository.UserRepository;


public class UserService implements UserDetailsService {

    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private JWTGenerator jwtGenerator;
    private UserMapper userMapper;


    public UserService() {
    }

    public UserService(
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            UserRepository userRepository,
            RoleRepository roleRepository,
            JWTGenerator jwtGenerator,
            UserMapper userMapper
    ) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.jwtGenerator = jwtGenerator;
        this.userMapper = userMapper;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userOpt = userRepository.findByUsername(username);
        if (!userOpt.isPresent()) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new UserPrincipal(userOpt.get());
    }

    public UserRegisterResponse register(UserRegisterRequest request) {

        User newUser = userMapper.toUser(request);

        String rawPassword = request.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        newUser.setPassword(encodedPassword);

        User savedUser = userRepository.save(newUser);

        UserRegisterResponse response = userMapper.toUserRegisterResponse(savedUser);

        return response;
    }

    public UserLoginResponse login(UserLoginRequest request) throws AppException {

        String username = request.getUsername();
        String password = request.getPassword();

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (BadCredentialsException bcexp) {
            throw new AppException(HttpStatus.UNAUTHORIZED.value(), "Login/password is incorrect");
        }

        UserDetails userDetails = this.loadUserByUsername(username);
        String token = jwtGenerator.generateToken(userDetails);

        UserLoginResponse response = new UserLoginResponse(username, token);

        return response;
    }
}