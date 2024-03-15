package just.education.user_messaging_app.mapper;

import just.education.user_messaging_app.entity.User;
import just.education.user_messaging_app.dto.UserCreateDto;
import just.education.user_messaging_app.dto.UserUpdateDto;
import just.education.user_messaging_app.dto.UserReadDto;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.Collection;
import java.util.ArrayList;
import java.util.List;


public class UserMapper {

    private final ModelMapper mapper;


    public UserMapper() {

        this.mapper = new ModelMapper();

        this.mapper.createTypeMap(UserCreateDto.class, User.class, "userCreateDtoToUser")
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
                        map().setProfileInfo(source.getProfileInfo());
                    }
                });

        this.mapper.createTypeMap(UserUpdateDto.class, User.class, "userUpdateDtoToUser")
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

        this.mapper.createTypeMap(UserUpdateDto.class, User.class, "userUpdateDtoNotNullFieldsToUser")
                .addMappings(new PropertyMap<UserUpdateDto, User>() {

                    @Override
                    protected void configure() {

                        when(Conditions.isNull()).skip().setFirstName(source.getFirstName());
                        when(Conditions.isNull()).skip().setMiddleName(source.getMiddleName());
                        when(Conditions.isNull()).skip().setLastName(source.getLastName());
                        when(Conditions.isNull()).skip().setUsername(source.getUsername());
                        when(Conditions.isNull()).skip().setMobile(source.getMobile());
                        when(Conditions.isNull()).skip().setEmail(source.getEmail());
                        when(Conditions.isNull()).skip().setIntro(source.getIntro());
                        when(Conditions.isNull()).skip().setProfileInfo(source.getProfileInfo());
                    }
                });

        this.mapper.createTypeMap(User.class, UserReadDto.class, "userToUserReadDto")
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
        return this.mapper.map(userCreateDto, User.class, "userCreateDtoToUser");
    }

    public User toUser(UserUpdateDto userUpdateDto) {
        return this.mapper.map(userUpdateDto, User.class, "userUpdateDtoToUser");
    }

    public void toUserNonNullFields(UserUpdateDto userUpdateDto, User user) {
        this.mapper.map(userUpdateDto, user, "userUpdateDtoNotNullFieldsToUser");
    }

    public UserReadDto toUserReadDto(User user) {
        return this.mapper.map(user, UserReadDto.class, "userToUserReadDto");
    }

    public List<UserReadDto> toUserReadDtoList(Collection<User> users) {

        List<UserReadDto> userReadDtoSet = new ArrayList<>();

        if (users != null) {

            for (User user : users) {
                userReadDtoSet.add(this.toUserReadDto(user));
            }
        }

        return userReadDtoSet;
    }
}
