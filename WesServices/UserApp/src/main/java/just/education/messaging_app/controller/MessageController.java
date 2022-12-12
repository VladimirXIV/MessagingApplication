package just.education.messaging_app.controller;

import just.education.messaging_app.dto.MessageReadDto;
import just.education.messaging_app.dto.MessageUpdateDto;
import just.education.messaging_app.service.MessageService;

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
@RequestMapping("/messages")
public class MessageController {

    private MessageService messageService;


    public MessageController() {
    }

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }


    @GetMapping(path = "/{id}")
    public MessageReadDto findMessageById(@PathVariable("id") final long id) {
        return this.messageService.findById(id);
    }

    @PutMapping(path = "/{id}")
    public MessageReadDto updateMessage(@PathVariable("id") final long id, @RequestBody MessageUpdateDto messageUpdateDto) {
        return this.messageService.updateById(id, messageUpdateDto);
    }

    @DeleteMapping(path = "/{id}")
    public MessageReadDto deletePostById(@PathVariable("id") final int id) {
        return this.messageService.deleteById(id);
    }
}