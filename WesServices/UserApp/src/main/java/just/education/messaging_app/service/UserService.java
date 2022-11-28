package just.education.messaging_app.service;

import just.education.messaging_app.dto.UserReadDto;
import just.education.messaging_app.dto.UserCreateDto;
import just.education.messaging_app.dto.UserUpdateDto;

import java.util.List;

public interface UserService {

    public UserReadDto create(UserCreateDto userCreateDto);

    public UserReadDto findById(final long id);

    public List<UserReadDto> findAll();

    public UserReadDto updateById(long id, UserUpdateDto userUpdateDto);

    public UserReadDto deleteById(final long id);
}
