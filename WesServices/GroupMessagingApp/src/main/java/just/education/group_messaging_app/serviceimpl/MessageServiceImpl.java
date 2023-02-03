package just.education.group_messaging_app.serviceimpl;

import just.education.group_messaging_app.dto.MessageCreateDto;
import just.education.group_messaging_app.dto.MessageReadDto;
import just.education.group_messaging_app.dto.MessageUpdateDto;
import just.education.group_messaging_app.entity.Group;
import just.education.group_messaging_app.entity.Message;
import just.education.group_messaging_app.mapper.MessageMapper;
import just.education.group_messaging_app.repository.GroupRepository;
import just.education.group_messaging_app.repository.MessageRepository;
import just.education.group_messaging_app.service.MessageService;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;


public class MessageServiceImpl implements MessageService {

    private MessageRepository messageRepository;
    private GroupRepository groupRepository;
    private MessageMapper messageMapper;


    public MessageServiceImpl() {
    }

    public MessageServiceImpl(MessageRepository messageRepository, GroupRepository groupRepository, MessageMapper messageMapper) {
        this.messageRepository = messageRepository;
        this.groupRepository = groupRepository;
        this.messageMapper = messageMapper;
    }


    @Override
    public MessageReadDto create(MessageCreateDto messageCreateDto) {

        final Message message = this.messageMapper.toMessage(messageCreateDto);

        // set group (foreign key)
        final Long groupId = messageCreateDto.getGroupId();
        final Group group = this.groupRepository.getReferenceById(groupId);
        message.setGroup(group);

        final Timestamp createdAt = Timestamp.from(Instant.now());
        message.setCreatedAt(createdAt);
        message.setUpdatedAt(createdAt);

        final Message createdMessage = this.messageRepository.save(message);

        return this.messageMapper.toMessageReadDto(createdMessage);
    }
    
    @Override
    public MessageReadDto findById(long id) {

        final Message message = this.messageRepository.getReferenceById(id);

        return this.messageMapper.toMessageReadDto(message);
    }

    @Override
    public MessageReadDto updateById(long id, MessageUpdateDto messageUpdateDto) {

        final Message currentMessage = this.messageRepository.getReferenceById(id);

        this.messageMapper.updateMessage(messageUpdateDto, currentMessage);

        // update group (foreign key)
        final Long groupId = messageUpdateDto.getGroupId();
        if (Objects.nonNull(groupId)) {
            final Group group = this.groupRepository.getReferenceById(groupId);
            currentMessage.setGroup(group);
        }

        final Timestamp updatedAt = Timestamp.from(Instant.now());
        currentMessage.setUpdatedAt(updatedAt);

        final Message updatedMessage  = this.messageRepository.save(currentMessage);

        return this.messageMapper.toMessageReadDto(updatedMessage);
    }

    @Override
    public MessageReadDto deleteById(long id) {

        final Message message = this.messageRepository.getReferenceById(id);

        this.messageRepository.delete(message);

        return this.messageMapper.toMessageReadDto(message);
    }
}