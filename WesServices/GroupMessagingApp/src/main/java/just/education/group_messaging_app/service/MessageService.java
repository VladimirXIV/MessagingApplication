package just.education.group_messaging_app.service;

import just.education.group_messaging_app.dto.MessageCreateDto;
import just.education.group_messaging_app.dto.MessageReadDto;
import just.education.group_messaging_app.dto.MessageUpdateDto;


public interface MessageService {

    public MessageReadDto findById(final long id);

    public MessageReadDto create(MessageCreateDto  messageCreateDto);

    public MessageReadDto updateById(final long id, MessageUpdateDto messageUpdateDto);

    public MessageReadDto deleteById(final long id);
}
