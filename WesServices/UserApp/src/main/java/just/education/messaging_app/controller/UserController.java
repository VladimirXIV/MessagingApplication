package just.education.messaging_app.controller;

import lombok.RequiredArgsConstructor;

import just.education.messaging_app.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public void createUser() {

    }

    @GetMapping
    public void findUserById(@PathVariable("id") final int id) {

    }

    @PatchMapping
    public void updateUser() {

    }

    @DeleteMapping
    public void deleteUserById(@PathVariable("id") final int id) {

    }
}