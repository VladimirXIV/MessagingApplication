package just.education.security_messaging_app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import just.education.security_messaging_app.model.User;
import just.education.security_messaging_app.dto.UserRegisterRequest;
import just.education.security_messaging_app.dto.UserRegisterResponse;


@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "username", target = "username")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    public User toUser(UserRegisterRequest userRegisterRequest);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    public UserRegisterResponse toUserRegisterResponse(User user);
}