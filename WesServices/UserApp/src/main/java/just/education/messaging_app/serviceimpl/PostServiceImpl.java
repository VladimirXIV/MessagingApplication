package just.education.messaging_app.serviceimpl;

import just.education.messaging_app.entity.Post;
import just.education.messaging_app.dto.PostReadDto;
import just.education.messaging_app.dto.PostCreateDto;
import just.education.messaging_app.dto.PostUpdateDto;
import just.education.messaging_app.mapper.PostMapper;
import just.education.messaging_app.service.PostService;
import just.education.messaging_app.repository.PostRepository;

import java.sql.Timestamp;
import java.time.Instant;


public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private PostMapper postMapper;


    public PostServiceImpl() {

    }

    public PostServiceImpl(PostRepository postRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }


    @Override
    public PostReadDto create(final long senderId, PostCreateDto postCreateDto) {

        final long receiverId = postCreateDto.getReceiverId();

        final Post newPost = postMapper.toPost(postCreateDto);

        final Timestamp createdAt = Timestamp.from(Instant.now());

        newPost.setCreatedAt(createdAt);
        newPost.setUpdatedAt(createdAt);

        final Post createdPost = postRepository.create(senderId, receiverId, newPost);

        return postMapper.toPostReadDto(createdPost);
    }

    @Override
    public PostReadDto findById(final long id) {

        final Post post = postRepository.retrieveById(id);

        return postMapper.toPostReadDto(post);
    }

    @Override
    public PostReadDto updateById(final long id, PostUpdateDto postUpdateDto) {

        final Post currentPost = postRepository.retrieveById(id);

        postMapper.toPostNonNullFields(postUpdateDto, currentPost);

        final Timestamp updatedAt = Timestamp.from(Instant.now());
        currentPost.setUpdatedAt(updatedAt);

        final Post updatedPost = postRepository.update(currentPost);

        return postMapper.toPostReadDto(updatedPost);
    }

    @Override
    public PostReadDto deleteById(final long id) {

        final Post post = postRepository.deleteById(id);

        return postMapper.toPostReadDto(post);
    }
}
