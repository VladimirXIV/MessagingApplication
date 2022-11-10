package just.education.messaging_app.controller;

import just.education.messaging_app.model.User;
import just.education.messaging_app.service.UserService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController {


    private UserService userService;


    public UserController() {

    }

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    public void createUser() {

    }

    @GetMapping(path = "/{id}")
    public User findUserById(@PathVariable("id") final long id) {
        return userService.findById(id);
    }

    @PatchMapping
    public void updateUser() {

    }

    @DeleteMapping
    public void deleteUserById(@PathVariable("id") final int id) {

    }
}