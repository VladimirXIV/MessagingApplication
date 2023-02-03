package just.education.group_messaging_app.serviceimpl;

import just.education.group_messaging_app.dto.PostCreateDto;
import just.education.group_messaging_app.dto.PostReadDto;
import just.education.group_messaging_app.dto.PostUpdateDto;
import just.education.group_messaging_app.entity.Group;
import just.education.group_messaging_app.entity.Post;
import just.education.group_messaging_app.mapper.PostMapper;
import just.education.group_messaging_app.repository.GroupRepository;
import just.education.group_messaging_app.repository.PostRepository;
import just.education.group_messaging_app.service.PostService;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;


public class PostServiceimpl implements PostService {

    private GroupRepository groupRepository;
    private PostRepository postRepository;
    private PostMapper postMapper;


    public PostServiceimpl() {
    }

    public PostServiceimpl(PostRepository postRepository, GroupRepository groupRepository, PostMapper postMapper) {
        this.groupRepository = groupRepository;
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }


    @Override
    public PostReadDto create(PostCreateDto postCreateDto) {

        final Post post = this.postMapper.toPost(postCreateDto);

        // set group (foreign key)
        final Long groupId = postCreateDto.getGroupId();
        final Group group = this.groupRepository.getReferenceById(groupId);
        post.setGroup(group);

        final Timestamp createdAt = Timestamp.from(Instant.now());
        post.setCreatedAt(createdAt);
        post.setUpdatedAt(createdAt);

        final Post createdPost = this.postRepository.save(post);

        return this.postMapper.toPostReadDto(createdPost);
    }

    @Override
    public PostReadDto findById(long id) {

        final Post post = this.postRepository.getReferenceById(id);

        return this.postMapper.toPostReadDto(post);
    }

    @Override
    public PostReadDto updateById(long id, PostUpdateDto postUpdateDto) {

        final Post currentPost = this.postRepository.getReferenceById(id);

        this.postMapper.updatePost(postUpdateDto, currentPost);

        // update group (foreign key)
        final Long groupId = postUpdateDto.getGroupId();
        if (Objects.nonNull(groupId)) {
            final Group group = this.groupRepository.getReferenceById(groupId);
            currentPost.setGroup(group);
        }

        final Timestamp updatedAt = Timestamp.from(Instant.now());
        currentPost.setUpdatedAt(updatedAt);

        final Post updatedPost = this.postRepository.save(currentPost);

        return this.postMapper.toPostReadDto(updatedPost);
    }

    @Override
    public PostReadDto deleteById(long id) {

        final Post post = this.postRepository.getReferenceById(id);

        this.postRepository.delete(post);

        return this.postMapper.toPostReadDto(post);
    }
}