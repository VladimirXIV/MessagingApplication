package just.education.messaging_app.controller;

import just.education.messaging_app.dto.FriendshipReadDto;
import just.education.messaging_app.service.FriendshipService;

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
@RequestMapping("/friendship")
public class FriendshipController {

    private FriendshipService friendshipService;


    public FriendshipController() {
    }

    @Autowired
    public FriendshipController(FriendshipService friendshipService) {
        this.friendshipService = friendshipService;
    }


    @GetMapping(path = "/{id}")
    public FriendshipReadDto findById(@PathVariable("id") final long id) {
        return this.friendshipService.findById(id);
    }

    @DeleteMapping(path = "/{id}")
    public FriendshipReadDto deleteById(@PathVariable("id") final int id) {
        return this.friendshipService.deleteById(id);
    }
}