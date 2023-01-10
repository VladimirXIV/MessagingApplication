package just.education.group_messaging_app.controller;

import just.education.group_messaging_app.dto.GroupReadDto;
import just.education.group_messaging_app.dto.MessageUpdateDto;
import just.education.group_messaging_app.service.GroupService;

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
@RequestMapping("/groups")
public class GroupController {

    private GroupService groupService;


    public GroupController() {
    }

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }


    @GetMapping(path = "/{id}")
    public GroupReadDto findMessageById(@PathVariable("id") final long id) {
        return this.groupService.findById(id);
    }

    @PutMapping(path = "/{id}")
    public GroupReadDto updateMessage(@PathVariable("id") final long id, @RequestBody MessageUpdateDto messageUpdateDto) {
        return this.groupService.updateById(id, messageUpdateDto);
    }

    @DeleteMapping(path = "/{id}")
    public GroupReadDto deletePostById(@PathVariable("id") final int id) {
        return this.groupService.deleteById(id);
    }
}