package just.education.messaging_app.controller;

import just.education.messaging_app.dto.PostReadDto;
import just.education.messaging_app.dto.PostUpdateDto;
import just.education.messaging_app.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;


@RestController
@RequestMapping("/posts")
public class PostController {

    private PostService userPostService;


    public PostController() {

    }

    @Autowired
    public PostController(PostService userPostService) {
        this.userPostService = userPostService;
    }


    @GetMapping(path = "/{id}")
    public PostReadDto findPostById(@PathVariable("id") final long id) {
        return userPostService.findById(id);
    }

    @PutMapping(path = "/{id}")
    public PostReadDto updateUserPost(@PathVariable final long id, @RequestBody PostUpdateDto postUpdateDto) {
        return userPostService.updateById(id, postUpdateDto);
    }

    @DeleteMapping(path = "/{id}")
    public PostReadDto deleteUserPostById(@PathVariable("id") final int id) {
        return userPostService.deleteById(id);
    }
}