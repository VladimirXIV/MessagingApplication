package just.education.group_messaging_app.controller;

import just.education.group_messaging_app.dto.PostReadDto;
import just.education.group_messaging_app.dto.PostUpdateDto;
import just.education.group_messaging_app.service.PostService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping("/posts")
public class PostController {

    private PostService postService;


    public PostController() {
    }

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }


    @GetMapping(path = "/{id}")
    public PostReadDto findPostById(@PathVariable("id") final long id) {
        return this.postService.findById(id);
    }

    @PostMapping(path = "/{id}")
    public PostReadDto updatePost(@PathVariable("id") final long id, @RequestBody PostUpdateDto postUpdateDto) {
        return this.postService.updateById(id, postUpdateDto);
    }

    @DeleteMapping(path = "/{id}")
    public PostReadDto deletePostById(@PathVariable("id") final long id) {
        return this.postService.deleteById(id);
    }
}