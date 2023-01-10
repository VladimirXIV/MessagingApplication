package just.education.group_messaging_app.mapper;

import just.education.group_messaging_app.dto.PostCreateDto;
import just.education.group_messaging_app.dto.PostReadDto;
import just.education.group_messaging_app.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface PostMapper {

    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "text", target = "text")
    public Post toPost(PostCreateDto postCreateDto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "groupId", target = "group.id")
    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "text", target = "text")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    public PostReadDto toPostReadDto(Post post);
}
