package just.education.messaging_app.serviceimpl;

import just.education.messaging_app.dto.UserReadDto;
import just.education.messaging_app.dto.UserCreateDto;
import just.education.messaging_app.dto.UserUpdateDto;
import just.education.messaging_app.entity.User;
import just.education.messaging_app.repository.UserRepository;
import just.education.messaging_app.service.UserService;
import just.education.messaging_app.mapper.UserMapper;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;


public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;


    public UserServiceImpl() {
    }

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    @Override
    public UserReadDto create(UserCreateDto userCreateDto) {

        final User newUser = userMapper.toUser(userCreateDto);

        final Timestamp registeredAt = Timestamp.from(Instant.now());

        newUser.setRegisteredAt(registeredAt);
        newUser.setLastLogin(registeredAt);

        final User createdUser = userRepository.create(newUser);

        return userMapper.toUserReadDto(createdUser);
    }

    @Override
    public UserReadDto findById(final long id) {

        final User user = userRepository.retrieveById(id);

        return userMapper.toUserReadDto(user);
    }

    @Override
    public List<UserReadDto> findAll() {

        final List<User> users = userRepository.retrieveAll();

        return userMapper.toUserReadDtoList(users);
    }

    @Override
    public UserReadDto updateById(final long id, UserUpdateDto userUpdateDto) {

        final User currentUser = userRepository.retrieveById(id);

        userMapper.toUserNonNullFields(userUpdateDto, currentUser); // map only NON-NULL fields

        final User updatedUser = userRepository.update(currentUser);

        return userMapper.toUserReadDto(updatedUser);
    }

    @Override
    public UserReadDto deleteById(final long id) {

        final User user = userRepository.deleteById(id);

        return userMapper.toUserReadDto(user);
    }
}
