package just.education.messaging_app.controller;

import just.education.messaging_app.dto.PostReadDto;
import just.education.messaging_app.dto.UserReadDto;
import just.education.messaging_app.dto.MessageReadDto;
import just.education.messaging_app.dto.FriendshipReadDto;
import just.education.messaging_app.dto.FollowshipReadDto;

import just.education.messaging_app.dto.UserCreateDto;
import just.education.messaging_app.dto.PostCreateDto;
import just.education.messaging_app.dto.MessageCreateDto;
import just.education.messaging_app.dto.FriendshipCreateDto;
import just.education.messaging_app.dto.FollowshipCreateDto;

import just.education.messaging_app.dto.UserUpdateDto;

import just.education.messaging_app.service.UserService;
import just.education.messaging_app.service.PostService;
import just.education.messaging_app.service.MessageService;
import just.education.messaging_app.service.FriendshipService;
import just.education.messaging_app.service.FollowshipService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private PostService postService;
    private MessageService messageService;
    private FriendshipService friendshipService;
    private FollowshipService followshipService;


    public UserController() {
    }

    @Autowired
    public UserController(UserService userService, PostService postService, MessageService messageService, FriendshipService friendshipService, FollowshipService followshipService) {
        this.userService = userService;
        this.postService = postService;
        this.messageService = messageService;
        this.friendshipService = friendshipService;
        this.followshipService = followshipService;
    }


    @PostMapping
    public UserReadDto createUser(@RequestBody UserCreateDto userCreateDto) {
        return this.userService.create(userCreateDto);
    }

    @PutMapping(path = "/{id}")
    public UserReadDto updateUser(@PathVariable final long id, @RequestBody UserUpdateDto userDto) {
        return this.userService.updateById(id, userDto);
    }

    @GetMapping(path = "/{id}")
    public UserReadDto findUserById(@PathVariable("id") final long id) {
        return this.userService.findById(id);
    }

    @GetMapping
    public List<UserReadDto> findAllUsers() {
        return this.userService.findAll();
    }

    @DeleteMapping(path = "/{id}")
    public UserReadDto deleteUserById(@PathVariable("id") final int id) {
        return this.userService.deleteById(id);
    }

    @PostMapping(path = "/{id}/post")
    public PostReadDto createPost(@PathVariable final long id, @RequestBody PostCreateDto postCreateDto) {
        return this.postService.create(id, postCreateDto);
    }

    @PostMapping(path = "/{id}/message")
    public MessageReadDto createMessage(@PathVariable final long id, @RequestBody MessageCreateDto messageCreateDto) {
        return this.messageService.create(id, messageCreateDto);
    }

    @PostMapping(path = "/{id}/friend")
    public FriendshipReadDto createFriendship(@PathVariable final long id, @RequestBody FriendshipCreateDto friendshipCreateDto) {
        return this.friendshipService.create(id, friendshipCreateDto);
    }

    @PostMapping(path = "/{id}/follower")
    public FollowshipReadDto createFollowship(@PathVariable final long id, @RequestBody FollowshipCreateDto followshipCreateDto) {
        return this.followshipService.create(id, followshipCreateDto);
    }
}