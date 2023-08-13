package just.education.security_messaging_app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;

import just.education.security_messaging_app.service.UserService;
import just.education.security_messaging_app.dto.UserLoginRequest;
import just.education.security_messaging_app.exception.AppException;
import just.education.security_messaging_app.dto.UserRegisterRequest;
import just.education.security_messaging_app.dto.UserRegisterResponse;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private UserService userService;


    @Autowired
    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping(path = "/register")
    public ResponseEntity<UserRegisterResponse> register(@RequestBody UserRegisterRequest request) {

        return new ResponseEntity<>(userService.register(request), HttpStatus.OK);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequest request) {

        try {
            return new ResponseEntity<>(userService.login(request), HttpStatus.OK);
        } catch (AppException appException) {
            return new ResponseEntity<>(appException, HttpStatus.UNAUTHORIZED);
        }
    }
}