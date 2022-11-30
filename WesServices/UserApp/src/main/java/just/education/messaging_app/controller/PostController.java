package just.education.messaging_app.controller;

import just.education.messaging_app.dto.PostReadDto;
import just.education.messaging_app.dto.PostUpdateDto;
import just.education.messaging_app.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
        return postService.findById(id);
    }

    @GetMapping(path = "/posts")
    public List<PostReadDto> findPostsBySender(@RequestParam("senderId") final long id) {
        return postService.findPostsBySenderId(id);
    }

    @GetMapping(path = "/posts")
    public List<PostReadDto> findPostsByReceiver(@RequestParam("receiverId") final long id) {
        return postService.findPostsByReceiverId(id);
    }

    @PutMapping(path = "/{id}")
    public PostReadDto updatePost(@PathVariable final long id, @RequestBody PostUpdateDto postUpdateDto) {
        return postService.updateById(id, postUpdateDto);
    }

    @DeleteMapping(path = "/{id}")
    public PostReadDto deletePostById(@PathVariable("id") final int id) {
        return postService.deleteById(id);
    }
}