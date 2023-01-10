package just.education.group_messaging_app.serviceimpl;

import just.education.group_messaging_app.dto.MessageCreateDto;
import just.education.group_messaging_app.dto.MessageReadDto;
import just.education.group_messaging_app.dto.MessageUpdateDto;
import just.education.group_messaging_app.entity.Message;
import just.education.group_messaging_app.mapper.MessageMapper;
import just.education.group_messaging_app.repository.MessageRepository;
import just.education.group_messaging_app.service.MessageService;

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
    public MessageReadDto create(MessageCreateDto messageCreateDto) {

        final Message message = messageMapper.toMessage(messageCreateDto);

        final Timestamp createdAt = Timestamp.from(Instant.now());
        message.setCreatedAt(createdAt);
        message.setUpdatedAt(createdAt);

        final Message createdMessage = messageRepository.save(message);

        return messageMapper.toMessageReadDto(createdMessage);
    }
    
    @Override
    public MessageReadDto findById(long id) {

        final Message message = messageRepository.getReferenceById(id);

        return messageMapper.toMessageReadDto(message);
    }

    @Override
    public MessageReadDto updateById(long id, MessageUpdateDto messageUpdateDto) {
        return null;
    }

    @Override
    public MessageReadDto deleteById(long id) {

        final Message message = messageRepository.getReferenceById(id);

        messageRepository.delete(message);

        return messageMapper.toMessageReadDto(message);
    }
}