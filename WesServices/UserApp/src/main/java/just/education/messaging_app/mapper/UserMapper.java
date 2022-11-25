package just.education.messaging_app.mapper;

import just.education.messaging_app.dto.UserCreateDto;
import just.education.messaging_app.dto.UserUpdateDto;
import just.education.messaging_app.dto.UserReadDto;
import just.education.messaging_app.entity.User;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


public class UserMapper {

    private final ModelMapper mapper;


    public UserMapper() {

        mapper = new ModelMapper();

        mapper.createTypeMap(UserCreateDto.class, User.class, "userCreateDtoToUser")
                .addMappings(new PropertyMap<UserCreateDto, User>() {

                    @Override
                    protected void configure() {
                        map().setFirstName(source.getFirstName());
                        map().setMiddleName(source.getMiddleName());
                        map().setLastName(source.getLastName());
                        map().setUsername(source.getUsername());
                        map().setMobile(source.getMobile());
                        map().setEmail(source.getEmail());
                        map().setIntro(source.getIntro());
                        map().setProfileInfo(source.getProfileInfo());}
                });

        mapper.createTypeMap(UserUpdateDto.class, User.class, "userUpdateDtoToUser")
                .addMappings(new PropertyMap<UserUpdateDto, User>() {

                    @Override
                    protected void configure() {
                        map().setFirstName(source.getFirstName());
                        map().setMiddleName(source.getMiddleName());
                        map().setLastName(source.getLastName());
                        map().setUsername(source.getUsername());
                        map().setMobile(source.getMobile());
                        map().setEmail(source.getEmail());
                        map().setIntro(source.getIntro());
                        map().setProfileInfo(source.getProfileInfo());
                    }
                });

        mapper.createTypeMap(UserUpdateDto.class, User.class, "userUpdateDtoNotNullFieldsToUser")
                .addMappings(new PropertyMap<UserUpdateDto, User>() {

                    @Override
                    protected void configure() {

                        if (source.getFirstName() != null) {
                            map().setFirstName(source.getFirstName());
                        }

                        if (source.getMiddleName() != null) {
                            map().setMiddleName(source.getMiddleName());
                        }

                        if (source.getLastName() != null) {
                            map().setLastName(source.getLastName());
                        }

                        if (source.getUsername() != null) {
                            map().setUsername(source.getUsername());
                        }

                        if (source.getMobile() != null) {
                            map().setMobile(source.getMobile());
                        }

                        if (source.getEmail() != null) {
                            map().setEmail(source.getEmail());
                        }

                        if (source.getIntro() != null) {
                            map().setIntro(source.getIntro());
                        }

                        if (source.getProfileInfo() != null) {
                            map().setProfileInfo(source.getProfileInfo());
                        }
                    }
                });

        mapper.createTypeMap(User.class, UserReadDto.class, "userToUserReadDto")
                .addMappings(new PropertyMap<User, UserReadDto>() {

                    @Override
                    protected void configure() {

                        map().setId(source.getId());
                        map().setFirstName(source.getFirstName());
                        map().setMiddleName(source.getMiddleName());
                        map().setLastName(source.getLastName());
                        map().setUsername(source.getUsername());
                        map().setMobile(source.getMobile());
                        map().setEmail(source.getEmail());
                        map().setRegisteredAt(source.getRegisteredAt());
                        map().setLastLogin(source.getLastLogin());
                        map().setIntro(source.getIntro());
                        map().setProfileInfo(source.getProfileInfo());
                    }
                });
    }

    public User toUser(UserCreateDto userCreateDto) {
        return mapper.map(userCreateDto, User.class, "userCreateDtoToUser");
    }

    public User toUser(UserUpdateDto userUpdateDto) {
        return mapper.map(userUpdateDto, User.class, "userUpdateDtoToUser");
    }

    public User toUserNonNullFields(UserUpdateDto userUpdateDto) {
        return mapper.map(userUpdateDto, User.class, "userUpdateDtoNotNullFieldsToUser");
    }

    public UserReadDto toUserReadDto(User user) {
        return mapper.map(user, UserReadDto.class, "userToUserReadDto");
    }

    public Set<UserReadDto> toUserReadDtoSet(Collection<User> users) {

        Set<UserReadDto> userReadDtoSet = new HashSet<>();

        for (User user : users) {
            userReadDtoSet.add(this.toUserReadDto(user));
        }

        return userReadDtoSet;
    }
}
