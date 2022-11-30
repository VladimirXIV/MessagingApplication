package just.education.messaging_app.controller;

import just.education.messaging_app.dto.MessageReadDto;
import just.education.messaging_app.dto.MessageUpdateDto;
import just.education.messaging_app.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return messageService.findById(id);
    }

    @GetMapping(path = "/posts")
    public List<MessageReadDto> findMessagesBySender(@RequestParam("senderId") final long id) {
        return messageService.findMessagesBySenderId(id);
    }

    @GetMapping(path = "/posts")
    public List<MessageReadDto> findMessagesByReceiver(@RequestParam("receiverId") final long id) {
        return messageService.findMessagesByReceiverId(id);
    }

    @PutMapping(path = "/{id}")
    public MessageReadDto updateMessage(@PathVariable("id") final long id, @RequestBody MessageUpdateDto messageUpdateDto) {
        return messageService.updateById(id, messageUpdateDto);
    }

    @DeleteMapping(path = "/{id}")
    public MessageReadDto deletePostById(@PathVariable("id") final int id) {
        return messageService.deleteById(id);
    }
}