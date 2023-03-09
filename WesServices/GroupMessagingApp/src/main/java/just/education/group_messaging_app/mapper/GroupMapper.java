package just.education.group_messaging_app.mapper;

import just.education.group_messaging_app.entity.Group;
import just.education.group_messaging_app.dto.GroupUpdateDto;
import just.education.group_messaging_app.dto.GroupReadDto;
import just.education.group_messaging_app.dto.GroupCreateDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper
public interface GroupMapper {

    GroupMapper INSTANCE = Mappers.getMapper(GroupMapper.class);

    @Mapping(source = "title", target = "title")
    @Mapping(source = "metaTitle", target = "metaTitle")
    @Mapping(source = "description", target = "description")
    public Group toGroup(GroupCreateDto createDto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "metaTitle", target = "metaTitle")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "type.id", target = "type.id")
    @Mapping(source = "type.name", target = "type.name")
    @Mapping(source = "status.code", target = "status.code")
    @Mapping(source = "status.name", target = "status.name")
    @Mapping(source = "createdBy", target = "createdBy")
    @Mapping(source = "updatedBy", target = "updatedBy")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    public GroupReadDto toGroupReadDto(Group group);

    @Mapping(source = "title", target = "title", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "metaTitle", target = "metaTitle", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "description", target = "description", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void updateGroup(GroupUpdateDto groupUpdateDto, @MappingTarget Group group);
}