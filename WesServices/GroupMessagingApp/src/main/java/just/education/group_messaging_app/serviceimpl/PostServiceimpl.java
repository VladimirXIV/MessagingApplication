package just.education.group_messaging_app.serviceimpl;

import just.education.group_messaging_app.mapper.PostMapper;
import just.education.group_messaging_app.repository.PostRepository;
import just.education.group_messaging_app.service.PostService;


public class PostServiceimpl implements PostService {

    private PostRepository postRepository;
    private PostMapper postMapper;


    public PostServiceimpl() {
    }

    public PostServiceimpl(PostRepository postRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }
}