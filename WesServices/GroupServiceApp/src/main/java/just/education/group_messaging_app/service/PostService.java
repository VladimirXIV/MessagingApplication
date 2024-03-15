package just.education.group_messaging_app.service;

import just.education.group_messaging_app.dto.PostCreateDto;
import just.education.group_messaging_app.dto.PostReadDto;
import just.education.group_messaging_app.dto.PostUpdateDto;


public interface PostService {

    public PostReadDto findById(final long id);

    public PostReadDto create(PostCreateDto postCreateDto);

    public PostReadDto updateById(final long id, PostUpdateDto postUpdateDto);

    public PostReadDto deleteById(final long id);
}
