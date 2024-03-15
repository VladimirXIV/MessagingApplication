package just.education.convo_messaging_app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.mapstruct.NullValuePropertyMappingStrategy;

import just.education.convo_messaging_app.entity.Participant;
import just.education.convo_messaging_app.dto.ParticipantReadDto;
import just.education.convo_messaging_app.dto.ParticipantUpdateDto;
import just.education.convo_messaging_app.dto.ParticipantCreateDto;


@Mapper(uses = {ConversationMapper.class})
public interface ParticipantMapper {

    ParticipantMapper INSTANCE = Mappers.getMapper(ParticipantMapper.class);

    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "nickname", target = "nickname")
    public Participant toParticipant(final ParticipantCreateDto participantCreateDto);

    @Mapping(source = "userId", target = "userId", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "nickname", target = "nickname", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void update(final ParticipantUpdateDto participantUpdateDto, final @MappingTarget Participant participant);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "nickname", target = "nickname")
    @Mapping(source = "enteredAt", target = "enteredAt")
    @Mapping(source = "convoMessageIds", target = "convoMessageIds")
    @Mapping(source = "conversations", target = "conversations")
    public ParticipantReadDto toParticipantReadDto(final Participant participant);
}