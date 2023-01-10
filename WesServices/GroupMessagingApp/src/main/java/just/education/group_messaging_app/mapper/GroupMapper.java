package just.education.group_messaging_app.mapper;

import just.education.group_messaging_app.entity.Group;
import just.education.group_messaging_app.dto.GroupReadDto;
import just.education.group_messaging_app.dto.GroupCreateDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;


@Mapper
public interface GroupMapper {

    GroupMapper INSTANCE = Mappers.getMapper(GroupMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "metaTitle", target = "metaTitle")
    @Mapping(source = "summary", target = "summary")
    @Mapping(source = "info", target = "info")
    @Mapping(source = "status.code", target = "status.code")
    @Mapping(source = "status.name", target = "status.name")
    @Mapping(source = "createdBy", target = "createdBy")
    @Mapping(source = "updatedBy", target = "updatedBy")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    public GroupReadDto toGroupReadDto(Group group);


    @Mapping(source = "title", target = "title")
    @Mapping(source = "metaTitle", target = "metaTitle")
    @Mapping(source = "summary", target = "summary")
    @Mapping(source = "info", target = "info")
    public Group toGroup(GroupCreateDto createDto);
}