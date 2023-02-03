package just.education.group_messaging_app.controller;

import just.education.group_messaging_app.dto.FollowshipReadDto;
import just.education.group_messaging_app.service.FollowshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
    public FollowshipReadDto findFollowerById(@PathVariable("id") final long id) {
        return null;
    }
}