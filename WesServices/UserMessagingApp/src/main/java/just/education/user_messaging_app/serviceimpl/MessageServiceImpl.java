package just.education.user_messaging_app.serviceimpl;

import just.education.user_messaging_app.dto.MessageCreateDto;
import just.education.user_messaging_app.dto.MessageReadDto;
import just.education.user_messaging_app.dto.MessageUpdateDto;
import just.education.user_messaging_app.entity.Message;
import just.education.user_messaging_app.mapper.MessageMapper;
import just.education.user_messaging_app.repository.MessageRepository;
import just.education.user_messaging_app.service.MessageService;

import java.sql.Timestamp;
import java.time.Instant;


public class MessageServiceImpl implements MessageService {

    private MessageRepository messageRepository;
    private MessageMapper messageMapper;


    public MessageServiceImpl() {
    }

    public MessageServiceImpl(MessageRepository messageRepository, MessageMapper messageMapper) {
        this.messageRepository = messageRepository;
        this.messageMapper = messageMapper;
    }


    @Override
    public MessageReadDto create(long senderId, MessageCreateDto messageCreateDto) {

        final long receiverId =  messageCreateDto.getReceiverId();

        final Message newMessage = messageMapper.toMessage(messageCreateDto);

        final Timestamp createdAt = Timestamp.from(Instant.now());

        newMessage.setCreatedAt(createdAt);
        newMessage.setUpdatedAt(createdAt);

        final Message createdMessage = messageRepository.create(senderId, receiverId, newMessage);

        return messageMapper.toMessageReadDto(createdMessage);
    }

    @Override
    public MessageReadDto findById(final long id) {

        final Message message = messageRepository.retrieveById(id);

        return messageMapper.toMessageReadDto(message);
    }

    @Override
    public MessageReadDto updateById(final long id, MessageUpdateDto messageUpdateDto) {

        final Message currentMessage = messageRepository.retrieveById(id);

        messageMapper.toMessageNonNullFields(messageUpdateDto, currentMessage);

        final Timestamp updatedAt = Timestamp.from(Instant.now());
        currentMessage.setUpdatedAt(updatedAt);

        final Message updatedMessage = messageRepository.update(currentMessage);

        return messageMapper.toMessageReadDto(updatedMessage);
    }

    @Override
    public MessageReadDto deleteById(final long id) {

        final Message message = messageRepository.deleteById(id);

        return messageMapper.toMessageReadDto(message);
    }
}
