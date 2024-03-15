package just.education.group_messaging_app.mapper;

import just.education.group_messaging_app.entity.Message;
import just.education.group_messaging_app.dto.MessageCreateDto;
import just.education.group_messaging_app.dto.MessageReadDto;
import just.education.group_messaging_app.dto.MessageUpdateDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper
public interface MessageMapper {

    MessageMapper INSTANCE = Mappers.getMapper(MessageMapper.class);

    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "text", target = "text")
    public Message toMessage(MessageCreateDto messageCreateDto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "group.id", target = "groupId")
    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "text", target = "text")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    public MessageReadDto toMessageReadDto(Message message);

    @Mapping(source = "userId", target = "userId", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "text", target = "text", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void updateMessage(MessageUpdateDto messageUpdateDto, @MappingTarget Message message);
}