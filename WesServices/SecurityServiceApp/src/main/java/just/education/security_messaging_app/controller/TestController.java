package just.education.security_messaging_app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping(path = "/unsecured")
    public ResponseEntity<String> unsecured() {

        return new ResponseEntity<>("This is not unsecured end point", HttpStatus.OK);
    }

    @GetMapping(path = "/secured")
    public ResponseEntity<String> secured() {

        return new ResponseEntity<>("This is secured end point", HttpStatus.OK);
    }

    @GetMapping(path = "/rolesecured")
    public ResponseEntity<String> roleSecured() {

        return new ResponseEntity<>("This is role secured end point", HttpStatus.OK);
    }

    @GetMapping(path = "/permissionSecured")
    public ResponseEntity<String> permissionSecured() {

        return new ResponseEntity<>("This is permission secured end point", HttpStatus.OK);
    }
}