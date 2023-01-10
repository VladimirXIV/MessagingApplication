package just.education.group_messaging_app.mapper;

import just.education.group_messaging_app.entity.Member;
import just.education.group_messaging_app.dto.MemberReadDto;
import just.education.group_messaging_app.dto.MemberCreateDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface MemberMapper {

    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "notes", target = "notes")
    public Member toMember(MemberCreateDto memberCreateDto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "groupId", target = "group.id")
    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "roleId", target = "role.id")
    @Mapping(source = "statusCode", target = "status.code")
    @Mapping(source = "notes", target = "notes")
    @Mapping(source = "id", target = "createdAt")
    @Mapping(source = "id", target = "updatedAt")
    public MemberReadDto toMemberReadDto(Member member);
}