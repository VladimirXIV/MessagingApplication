package just.education.messaging_app.controller;

import just.education.messaging_app.dto.PostReadDto;
import just.education.messaging_app.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/post")
public class PostController {

    private PostService userPostService;


    public PostController() {

    }

    @Autowired
    public PostController(PostService userPostService) {
        this.userPostService = userPostService;
    }


    @GetMapping(path = "/{id}")
    public PostReadDto findUserPostById(@PathVariable("id") final long id) {
        return userPostService.findById(id);
    }

    @PatchMapping
    public void updateUserPost() {

    }

    @DeleteMapping(path = "/{id}")
    public PostReadDto deleteUserPostById(@PathVariable("id") final int id) {
        return userPostService.deleteById(id);
    }
}