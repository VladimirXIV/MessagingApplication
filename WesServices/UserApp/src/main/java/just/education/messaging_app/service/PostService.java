package just.education.messaging_app.service;

import just.education.messaging_app.dto.PostCreateDto;
import just.education.messaging_app.dto.PostReadDto;
import just.education.messaging_app.entity.Post;

public interface PostService {

    public PostReadDto create(PostCreateDto postCreateDto, final long userId);

    public PostReadDto findById(final long id);

    public void update();

    public PostReadDto deleteById(final long id);
}
