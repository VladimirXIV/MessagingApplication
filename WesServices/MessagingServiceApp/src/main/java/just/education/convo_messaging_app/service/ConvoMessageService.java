package just.education.convo_messaging_app.service;

import java.util.Date;
import java.time.Instant;

import just.education.convo_messaging_app.entity.ConvoMessage;
import just.education.convo_messaging_app.dto.ConvoMessageReadDto;
import just.education.convo_messaging_app.enums.ConversationAction;
import just.education.convo_messaging_app.mapper.ConvoMessageMapper;
import just.education.convo_messaging_app.dto.ConvoMessageCreateDto;
import just.education.convo_messaging_app.dto.ConvoMessageUpdateDto;
import just.education.convo_messaging_app.repository.ConvoMessageRepository;


public class ConvoMessageService {

    private ConvoMessageRepository convoMessageRepository;
    private ConvoMessageMapper convoMessageMapper;


    public ConvoMessageService() {
    }

    public ConvoMessageService(ConvoMessageRepository convoMessageRepository, ConvoMessageMapper convoMessageMapper) {
        this.convoMessageRepository = convoMessageRepository;
        this.convoMessageMapper = convoMessageMapper;
    }


    public ConvoMessageReadDto create(final ConvoMessageCreateDto convoMessageCreateDto) {

        final ConvoMessage convoMessage = this.convoMessageMapper.toConvoMessage(convoMessageCreateDto);

        // set conversation action
        final String action = convoMessageCreateDto.getAction();
        final ConversationAction conversationAction = ConversationAction.valueOf(action);
        convoMessage.setAction(conversationAction);

        final Date currentDate = Date.from(Instant.now());
        convoMessage.setCreatedAt(currentDate);
        convoMessage.setUpdatedAt(currentDate);

        final ConvoMessage createdconvoMessage = this.convoMessageRepository.create(convoMessage);

        return this.convoMessageMapper.toConvoMessageReadDto(createdconvoMessage);
    }

    public ConvoMessageReadDto findById(final String id) {

        final ConvoMessage convoMessage = this.convoMessageRepository.retrieveById(id);

        return this.convoMessageMapper.toConvoMessageReadDto(convoMessage);
    }

    public ConvoMessageReadDto update(final String id, final ConvoMessageUpdateDto convoMessageUpdateDto) {

        final ConvoMessage convoMessage = this.convoMessageRepository.retrieveById(id);

        this.convoMessageMapper.update(convoMessageUpdateDto, convoMessage);
        final ConvoMessage updatedconvoMessage = this.convoMessageRepository.update(convoMessage);

        return this.convoMessageMapper.toConvoMessageReadDto(updatedconvoMessage);
    }

    public ConvoMessageReadDto delete(final String id) {

        final ConvoMessage convoMessage = this.convoMessageRepository.deleteById(id);

        return this.convoMessageMapper.toConvoMessageReadDto(convoMessage);
    }

}