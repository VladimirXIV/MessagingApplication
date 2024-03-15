package just.education.convo_messaging_app.service;

import java.util.Date;
import java.time.Instant;

import just.education.convo_messaging_app.entity.Conversation;
import just.education.convo_messaging_app.dto.ConversationReadDto;
import just.education.convo_messaging_app.entity.Participant;
import just.education.convo_messaging_app.mapper.ConversationMapper;
import just.education.convo_messaging_app.dto.ConversationCreateDto;
import just.education.convo_messaging_app.dto.ConversationUpdateDto;
import just.education.convo_messaging_app.repository.ConversationRepository;


public class ConversationService {

    private ConversationRepository conversationRepository;
    private ConversationMapper conversationMapper;


    public ConversationService() {
    }

    public ConversationService(ConversationRepository conversationRepository, ConversationMapper conversationMapper) {
        this.conversationRepository = conversationRepository;
        this.conversationMapper = conversationMapper;
    }


    public ConversationReadDto create(final ConversationCreateDto conversationCreateDto) {

        final Conversation conversation = this.conversationMapper.toConversation(conversationCreateDto);

        final Date currentDate = Date.from(Instant.now());
        conversation.setCreatedAt(currentDate);
        conversation.setUpdatedAt(currentDate);

        final Conversation createdConversation = conversationRepository.create(conversation);

        return this.conversationMapper.toConversationReadDto(createdConversation);
    }

    public ConversationReadDto findById(final String id) {

        final Conversation conversation = this.conversationRepository.retrieveById(id);

        return this.conversationMapper.toConversationReadDto(conversation);
    }

    public ConversationReadDto findByName(final String name) {

        final Conversation conversation = this.conversationRepository.retrieveByName(name);

        return this.conversationMapper.toConversationReadDto(conversation);
    }

    public ConversationReadDto update(final String id, final ConversationUpdateDto conversationUpdateDto) {

        final Conversation conversation = this.conversationRepository.retrieveById(id);

        this.conversationMapper.updateConversation(conversationUpdateDto, conversation);

        final Date currentDate = Date.from(Instant.now());
        conversation.setUpdatedAt(currentDate);

        final Conversation updatedConversation = this.conversationRepository.update(conversation);

        return this.conversationMapper.toConversationReadDto(updatedConversation);
    }

    public ConversationReadDto addParticipant(final String id, final Participant participant) {

        final Conversation conversation = this.conversationRepository.retrieveById(id);

        conversation.getParticipants().add(participant);
        final Conversation updatedConversation = this.conversationRepository.update(conversation);

        return this.conversationMapper.toConversationReadDto(updatedConversation);
    }

    public ConversationReadDto delete(final String id) {

        final Conversation conversation = this.conversationRepository.deleteById(id);

        return this.conversationMapper.toConversationReadDto(conversation);
    }
}