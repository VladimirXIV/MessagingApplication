package just.education.group_messaging_app.controller;

import just.education.group_messaging_app.dto.FollowerReadDto;
import just.education.group_messaging_app.service.FollowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/followers")
public class FollowerController {

    private FollowerService followerService;


    public FollowerController() {
    }

    @Autowired
    public FollowerController(FollowerService followerService) {
        this.followerService = followerService;
    }


    @GetMapping(path = "/{id}")
    public FollowerReadDto findFollowerById(@PathVariable("id") final long id) {
        return this.followerService
    }
}
