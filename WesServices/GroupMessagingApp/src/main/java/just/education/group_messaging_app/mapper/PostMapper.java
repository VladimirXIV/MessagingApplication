package just.education.group_messaging_app.mapper;

import just.education.group_messaging_app.entity.Post;
import just.education.group_messaging_app.dto.PostCreateDto;
import just.education.group_messaging_app.dto.PostReadDto;
import just.education.group_messaging_app.dto.PostUpdateDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper
public interface PostMapper {

    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "text", target = "text")
    public Post toPost(PostCreateDto postCreateDto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "group.id", target = "groupId")
    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "text", target = "text")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    public PostReadDto toPostReadDto(Post post);

    @Mapping(source = "userId", target = "userId", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "text", target = "text", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void updatePost(PostUpdateDto postUpdateDto, @MappingTarget Post post);
}