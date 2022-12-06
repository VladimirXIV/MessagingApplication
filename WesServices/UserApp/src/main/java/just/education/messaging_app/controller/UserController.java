package just.education.messaging_app.controller;

import just.education.messaging_app.dto.UserReadDto;
import just.education.messaging_app.dto.UserCreateDto;
import just.education.messaging_app.dto.UserUpdateDto;
import just.education.messaging_app.dto.MessageCreateDto;
import just.education.messaging_app.dto.MessageReadDto;
import just.education.messaging_app.dto.PostReadDto;
import just.education.messaging_app.dto.PostCreateDto;
import just.education.messaging_app.service.UserService;
import just.education.messaging_app.service.PostService;
import just.education.messaging_app.service.MessageService;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private PostService postService;
    private MessageService messageService;


    public UserController() {

    }

    @Autowired
    public UserController(UserService userService, PostService postService, MessageService messageService) {
        this.userService = userService;
        this.postService = postService;
        this.messageService = messageService;
    }


    @PostMapping
    public UserReadDto createUser(@RequestBody UserCreateDto userCreateDto) {
        return userService.create(userCreateDto);
    }

    @PutMapping(path = "/{id}")
    public UserReadDto updateUser(@PathVariable final long id, @RequestBody UserUpdateDto userDto) {
        return userService.updateById(id, userDto);
    }

    @GetMapping(path = "/{id}")
    public UserReadDto findUserById(@PathVariable("id") final long id) {
        return userService.findById(id);
    }

    @GetMapping
    public List<UserReadDto> findAllUsers() {
        return userService.findAll();
    }

    @DeleteMapping(path = "/{id}")
    public UserReadDto deleteUserById(@PathVariable("id") final int id) {
        return userService.deleteById(id);
    }

    @PostMapping(path = "/{id}/post")
    public PostReadDto createPost(@PathVariable final long id, @RequestBody PostCreateDto postCreateDto) {
        return postService.create(id, postCreateDto);
    }

    @PostMapping(path = "/{id}/message")
    public MessageReadDto createMessage(@PathVariable final long id, @RequestBody MessageCreateDto messageCreateDto) {
        return messageService.create(id, messageCreateDto);
    }
}