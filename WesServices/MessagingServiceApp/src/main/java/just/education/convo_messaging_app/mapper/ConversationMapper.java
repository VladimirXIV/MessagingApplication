package just.education.convo_messaging_app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.mapstruct.NullValuePropertyMappingStrategy;

import just.education.convo_messaging_app.entity.Conversation;
import just.education.convo_messaging_app.dto.ConversationReadDto;
import just.education.convo_messaging_app.dto.ConversationUpdateDto;
import just.education.convo_messaging_app.dto.ConversationCreateDto;


@Mapper(uses = {ParticipantMapper.class})
public interface ConversationMapper {

    ConversationMapper INSTANCE = Mappers.getMapper(ConversationMapper.class);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "ownerId", target = "ownerId")
    public Conversation toConversation(final ConversationCreateDto conversationCreateDto);

    @Mapping(source = "name", target = "name", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "description", target = "description", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "ownerId", target = "ownerId", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void updateConversation(final ConversationUpdateDto conversationUpdateDto, final @MappingTarget Conversation conversation);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "ownerId", target = "ownerId")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "participants", target = "participants")
    @Mapping(source = "convoMessageIds", target = "convoMessageIds")
    public ConversationReadDto toConversationReadDto(final Conversation conversation);
}