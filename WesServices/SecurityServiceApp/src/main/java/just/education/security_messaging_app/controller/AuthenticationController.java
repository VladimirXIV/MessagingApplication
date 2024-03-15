package just.education.security_messaging_app.controller;

import java.text.ParseException;

import com.nimbusds.jose.JOSEException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;

import just.education.security_messaging_app.service.UserService;
import just.education.security_messaging_app.dto.UserLoginRequest;
import just.education.security_messaging_app.exception.AppException;
import just.education.security_messaging_app.dto.RefreshTokenRequest;
import just.education.security_messaging_app.dto.UserRegisterRequest;
import just.education.security_messaging_app.dto.RefreshTokenResponse;
import just.education.security_messaging_app.dto.UserRegisterResponse;
import just.education.security_messaging_app.service.RefreshTokenService;


@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private UserService userService;
    private RefreshTokenService refreshTokenService;


    @Autowired
    public AuthenticationController(UserService userService, RefreshTokenService refreshTokenService) {

        this.userService = userService;
        this.refreshTokenService = refreshTokenService;
    }


    @PostMapping(path = "/signup")
    public ResponseEntity<UserRegisterResponse> signUp(@RequestBody UserRegisterRequest request) {

        return new ResponseEntity<>(userService.signUp(request), HttpStatus.OK);
    }

    @PostMapping(path = "/signin")
    public ResponseEntity<?> signIn(@RequestBody UserLoginRequest request) throws ParseException, JOSEException {

        try {
            return new ResponseEntity<>(userService.signIn(request), HttpStatus.OK);
        } catch (AppException appException) {
            return new ResponseEntity<>(appException, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping(path = "/signout")
    public void signOut() {

    }

    @PostMapping(path = "/token/revoke")
    public ResponseEntity<RefreshTokenResponse> refresh(@RequestBody RefreshTokenRequest request) throws Exception {

        return new ResponseEntity<>(refreshTokenService.refresh(request), HttpStatus.OK);
    }
}