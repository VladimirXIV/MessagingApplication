package just.education.user_messaging_app.service;

import just.education.user_messaging_app.dto.MessageReadDto;
import just.education.user_messaging_app.dto.MessageCreateDto;
import just.education.user_messaging_app.dto.MessageUpdateDto;

import java.util.List;


public interface MessageService {

    public MessageReadDto create(final long senderId, MessageCreateDto messageCreateDto);

    public MessageReadDto findById(final long id);

    public MessageReadDto updateById(final long id, MessageUpdateDto messageUpdateDto);

    public MessageReadDto deleteById(final long id);
}
