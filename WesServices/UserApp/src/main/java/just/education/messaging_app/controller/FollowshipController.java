package just.education.messaging_app.controller;

import just.education.messaging_app.dto.FollowshipReadDto;
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


@RestController
@RequestMapping("/followship")
public class FollowshipController {

    private FollowshipService followshipService;


    public FollowshipController() {
    }

    @Autowired
    public FollowshipController(FollowshipService followshipService) {
        this.followshipService = followshipService;
    }


    @GetMapping(path = "/{id}")
    public FollowshipReadDto findById(@PathVariable("id") final long id) {
        return this.followshipService.findById(id);
    }

    @DeleteMapping(path = "/{id}")
    public FollowshipReadDto deleteById(@PathVariable("id") final int id) {
        return this.followshipService.deleteById(id);
    }
}
