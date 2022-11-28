package just.education.messaging_app.serviceimpl;

import just.education.messaging_app.dto.PostUpdateDto;
import just.education.messaging_app.entity.Post;
import just.education.messaging_app.dto.PostReadDto;
import just.education.messaging_app.dto.PostCreateDto;
import just.education.messaging_app.mapper.PostMapper;
import just.education.messaging_app.repository.PostRepository;
import just.education.messaging_app.service.PostService;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;


public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private PostMapper postMapper;


    public PostServiceImpl() {

    }

    public PostServiceImpl(PostRepository postRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }


    public PostReadDto create(final long userId, PostCreateDto postCreateDto) {

        final Post newPost = postMapper.toPost(postCreateDto);

        final Timestamp createdAt = Timestamp.from(Instant.now());

        newPost.setCreatedAt(createdAt);
        newPost.setUpdatedAt(createdAt);

        final Post createdPost = postRepository.create(newPost, userId);

        return postMapper.toPostReadDto(createdPost);
    }

    public PostReadDto findById(final long id) {

        final Post post = postRepository.retrieveById(id);

        return postMapper.toPostReadDto(post);
    }

    public List<PostReadDto> findPostsByUser(final long id) {

        final List<Post> posts = postRepository.retrieveByUserId(id);

        return postMapper.toPostReadDtoList(posts);
    }

    public PostReadDto updateById(final long id, PostUpdateDto postUpdateDto) {

        final Post currentPost = postRepository.retrieveById(id);

        postMapper.toPostNonNullFields(postUpdateDto, currentPost);

        final Post updatedPost = postRepository.update(currentPost);

        return postMapper.toPostReadDto(updatedPost);
    }

    public PostReadDto deleteById(final long id) {

        final Post post = postRepository.deleteById(id);

        return postMapper.toPostReadDto(post);
    }
}
