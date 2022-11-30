package just.education.messaging_app.service;

import just.education.messaging_app.dto.PostReadDto;
import just.education.messaging_app.dto.PostCreateDto;
import just.education.messaging_app.dto.PostUpdateDto;

import java.util.List;


public interface PostService {

    public PostReadDto create(final long senderId, PostCreateDto postCreateDto);

    public PostReadDto findById(final long id);

    public List<PostReadDto> findPostsBySenderId(final long id);

    public List<PostReadDto> findPostsByReceiverId(final long id);

    public PostReadDto updateById(final long id, PostUpdateDto postUpdateDto);

    public PostReadDto deleteById(final long id);
}
