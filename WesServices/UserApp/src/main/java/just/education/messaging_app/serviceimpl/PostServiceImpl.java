package just.education.messaging_app.serviceimpl;

import just.education.messaging_app.model.Post;
import just.education.messaging_app.repository.PostRepository;
import just.education.messaging_app.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;

public class PostServiceImpl implements PostService {

    private PostRepository postRepository;


    public PostServiceImpl() {

    }

    public PostServiceImpl(PostRepository userPostRepository) {
        this.postRepository = postRepository;
    }


    public void create() {

    }

    public Post findById(long id) {
        return postRepository.retrieveById(id);
    }

    public void update() {

    }

    public void delete(int id) {

    }
}
