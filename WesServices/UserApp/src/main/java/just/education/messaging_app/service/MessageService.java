package just.education.messaging_app.service;

import just.education.messaging_app.dto.MessageReadDto;
import just.education.messaging_app.dto.MessageCreateDto;
import just.education.messaging_app.dto.MessageUpdateDto;

import java.util.List;


public interface MessageService {

    public MessageReadDto create(final long senderId, MessageCreateDto messageCreateDto);

    public MessageReadDto findById(final long id);

    public List<MessageReadDto> findMessagesBySenderId(final long id);

    public List<MessageReadDto> findMessagesByReceiverId(final long id);

    public MessageReadDto updateById(final long id, MessageUpdateDto messageUpdateDto);

    public MessageReadDto deleteById(final long id);
}
