package just.education.messaging_app.serviceimpl;

import just.education.messaging_app.entity.Post;
import just.education.messaging_app.dto.PostReadDto;
import just.education.messaging_app.dto.PostCreateDto;
import just.education.messaging_app.repository.PostRepository;
import just.education.messaging_app.service.PostService;
import org.modelmapper.ModelMapper;

import java.sql.Timestamp;
import java.time.Instant;

public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private ModelMapper modelMapper;


    public PostServiceImpl() {

    }

    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }


    public PostReadDto create(PostCreateDto postCreateDto, long userId) {

        final Post newPost = modelMapper.map(postCreateDto, Post.class);

        final Timestamp createdAt = Timestamp.from(Instant.now());

        newPost.setCreatedAt(createdAt);
        newPost.setUpdatedAt(createdAt);

        final Post createdPost = postRepository.create(newPost, userId);

        return modelMapper.map(createdPost, PostReadDto.class);
    }

    public PostReadDto findById(long id) {

        final Post post = postRepository.retrieveById(id);

        return modelMapper.map(post, PostReadDto.class);
    }

    public void update() {

    }

    public PostReadDto deleteById(long id) {

        final Post post = postRepository.deleteById(id);

        return modelMapper.map(post, PostReadDto.class);
    }
}
