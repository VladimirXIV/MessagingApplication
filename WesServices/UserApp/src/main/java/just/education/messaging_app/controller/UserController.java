package just.education.messaging_app.controller;

import just.education.messaging_app.dto.UserReadDto;
import just.education.messaging_app.dto.UserCreateDto;
import just.education.messaging_app.dto.UserUpdateDto;
import just.education.messaging_app.dto.PostReadDto;
import just.education.messaging_app.dto.PostCreateDto;
import just.education.messaging_app.service.UserService;
import just.education.messaging_app.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.Collection;


@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private PostService postService;

    public UserController() {

    }

    @Autowired
    public UserController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }


    @PostMapping
    public UserReadDto createUser(@RequestBody UserCreateDto userCreateDto) {
        return userService.create(userCreateDto);
    }

    @PostMapping(path = "/{id}/posts")
    public PostReadDto createPost(@PathVariable final long id, @RequestBody PostCreateDto postCreateDto) {
        return postService.create(postCreateDto, id);
    }

    @GetMapping(path = "/{id}")
    public UserReadDto findUserById(@PathVariable("id") final long id) {
        return userService.findById(id);
    }

    @GetMapping(path = "/all")
    public Collection<UserReadDto> findAll() {
        return userService.findAll();
    }

    @PutMapping(path = "/{id}")
    public UserReadDto updateUser(@PathVariable final long id, @RequestBody UserUpdateDto userDto) {
        return userService.updateById(id, userDto);
    }

    @DeleteMapping(path = "/{id}")
    public UserReadDto deleteUserById(@PathVariable("id") final int id) {
        return userService.deleteById(id);
    }
}