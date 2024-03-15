package just.education.convo_messaging_app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.mapstruct.NullValuePropertyMappingStrategy;

import just.education.convo_messaging_app.entity.ConvoMessage;
import just.education.convo_messaging_app.dto.ConvoMessageReadDto;
import just.education.convo_messaging_app.dto.ConvoMessageCreateDto;
import just.education.convo_messaging_app.dto.ConvoMessageUpdateDto;


@Mapper(uses = {ConversationMapper.class})
public interface ConvoMessageMapper {

    ConvoMessageMapper INSTANCE = Mappers.getMapper(ConvoMessageMapper.class);

    //@Mapping(source = "conversationId", target = "conversationId")
    @Mapping(source = "senderId", target = "senderId")
    @Mapping(source = "text", target = "text")
    @Mapping(source = "action", target = "action")
    public ConvoMessage toConvoMessage(final ConvoMessageCreateDto convoMessageCreateDto);

    //@Mapping(source = "conversationId", target = "conversationId", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "senderId", target = "senderId", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "text", target = "text", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "action", target = "action", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void update(final ConvoMessageUpdateDto convoMessageUpdateDto, final @MappingTarget ConvoMessage convoMessage);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "conversation", target = "conversation")
    @Mapping(source = "senderId", target = "senderId")
    @Mapping(source = "text", target = "text")
    @Mapping(source = "action", target = "action")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    public ConvoMessageReadDto toConvoMessageReadDto(final ConvoMessage convoMessage);
}