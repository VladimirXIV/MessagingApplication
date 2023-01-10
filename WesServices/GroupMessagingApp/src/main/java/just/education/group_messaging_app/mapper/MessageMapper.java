package just.education.group_messaging_app.mapper;

import just.education.group_messaging_app.dto.MessageCreateDto;
import just.education.group_messaging_app.dto.MessageReadDto;
import just.education.group_messaging_app.entity.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


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
}