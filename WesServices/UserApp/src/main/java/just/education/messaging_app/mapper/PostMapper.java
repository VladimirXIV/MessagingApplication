package just.education.messaging_app.mapper;

import just.education.messaging_app.entity.Post;
import just.education.messaging_app.dto.PostReadDto;
import just.education.messaging_app.dto.PostCreateDto;
import just.education.messaging_app.dto.PostUpdateDto;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;

import java.util.Collection;
import java.util.ArrayList;
import java.util.List;


public class PostMapper {

    private final ModelMapper mapper;


    public PostMapper() {

        this.mapper = new ModelMapper();

        this.mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        this.mapper.createTypeMap(PostCreateDto.class, Post.class, "postCreateDtoToPost")
                .addMappings(new PropertyMap<PostCreateDto, Post>() {

                    @Override
                    protected void configure() {

                        map().setSenderId(source.getSenderId());
                        map().setMessage(source.getMessage());
                    }
                });

        this.mapper.createTypeMap(PostUpdateDto.class, Post.class, "postUpdateDtoToPostNonNullFields")
                .addMappings(new PropertyMap<PostUpdateDto, Post>() {

                    @Override
                    protected void configure() {

                        when(Conditions.isNull()).skip().setSenderId(source.getSenderId());
                        when(Conditions.isNull()).skip().setMessage(source.getMessage());
                    }
                });

        this.mapper.createTypeMap(Post.class, PostReadDto.class, "postToPostReadDto")
                .addMappings(new PropertyMap<Post, PostReadDto>() {

                    @Override
                    protected void configure() {

                        map().setId(source.getId());
                        map().setSenderId(source.getSenderId());
                        map().setMessage(source.getMessage());
                        map().setCreatedAt(source.getCreatedAt());
                        map().setUpdatedAt(source.getUpdatedAt());
                    }
                });
    }

    public Post toPost(PostCreateDto postCreateDto) {
        return this.mapper.map(postCreateDto, Post.class, "postCreateDtoToPost");
    }

    public void toPostNonNullFields(PostUpdateDto postUpdateDto, Post post) {
        this.mapper.map(postUpdateDto, post, "postUpdateDtoToPostNonNullFields");
    }

    public PostReadDto toPostReadDto(Post post) {
        return this.mapper.map(post, PostReadDto.class, "postToPostReadDto");
    }

    public List<PostReadDto> toPostReadDtoList(Collection<Post> posts) {

        List<PostReadDto> postReadDtoList = new ArrayList<>();

        for (Post post : posts) {
            postReadDtoList.add(this.toPostReadDto(post));
        }

        return postReadDtoList;
    }
}
