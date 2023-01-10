package just.education.group_messaging_app.mapper;

import just.education.group_messaging_app.entity.Follower;
import just.education.group_messaging_app.dto.FollowerReadDto;
import just.education.group_messaging_app.dto.FollowerCreateDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface FollowerMapper {

    FollowerMapper INSTANCE = Mappers.getMapper(FollowerMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "groupId", target = "group.id")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    public FollowerReadDto toFollowerReadDto(Follower follower);

    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "groupId", target = "groupId")
    public Follower toFollower(FollowerCreateDto followerCreateDto);
}