package just.education.group_messaging_app.controller;

import just.education.group_messaging_app.dto.PostReadDto;
import just.education.group_messaging_app.dto.PostUpdateDto;
import just.education.group_messaging_app.service.PostService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/posts")
public class PostController {

    private PostService postService;


    public PostController() {
    }

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