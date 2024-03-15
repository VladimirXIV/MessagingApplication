package just.education.group_messaging_app.mapper;

import just.education.group_messaging_app.dto.FollowshipUpdateDto;
import just.education.group_messaging_app.entity.Followship;
import just.education.group_messaging_app.dto.FollowshipReadDto;
import just.education.group_messaging_app.dto.FollowshipCreateDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;


@Mapper
public interface FollowshipMapper {

    FollowshipMapper INSTANCE = Mappers.getMapper(FollowshipMapper.class);

    @Mapping(source = "userId", target = "userId")
    public Followship toFollowship(FollowshipCreateDto followshipCreateDto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "group.id", target = "groupId")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    public FollowshipReadDto toFollowerReadDto(Followship followship);

    @Mapping(source = "userId", target = "userId", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void updateFollowship(FollowshipUpdateDto followshipUpdateDto, @MappingTarget Followship followship);
}