package just.education.group_messaging_app.controller;

import just.education.group_messaging_app.dto.MessageReadDto;
import just.education.group_messaging_app.dto.MessageUpdateDto;
import just.education.group_messaging_app.service.MessageService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/messages")
public class MessageController {

    private MessageService messageService;


    public MessageController() {
    }

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }


    @GetMapping(path = "/{id}")
    public MessageReadDto findMemberById(@PathVariable("id") final long id) {
        return this.messageService.findById(id);
    }

    @PutMapping(path = "/{id}")
    public MessageReadDto updateMessage(@PathVariable("id") final long id, @RequestBody MessageUpdateDto messageUpdateDto) {
        return this.messageService.updateById(id, messageUpdateDto);
    }

    @DeleteMapping(path = "/{id}")
    public MessageReadDto deleteMessageById(@PathVariable("id") final long id) {
        return this.messageService.deleteById(id);
    }
}
