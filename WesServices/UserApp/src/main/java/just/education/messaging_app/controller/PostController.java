package just.education.messaging_app.controller;

import just.education.messaging_app.model.Post;
import just.education.messaging_app.service.PostService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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


    @PostMapping
    public void createPost() {

    }

    @GetMapping(path = "/{id}")
    public Post findUserPostById(@PathVariable("id") final long id) {
        return userPostService.findById(id);
    }

    @PatchMapping
    public void updateUserPost() {

    }

    @DeleteMapping
    public void deleteUserPostById(@PathVariable("id") final int id) {

    }
}