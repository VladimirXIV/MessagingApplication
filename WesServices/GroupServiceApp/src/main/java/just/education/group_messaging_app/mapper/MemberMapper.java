package just.education.group_messaging_app.mapper;

import just.education.group_messaging_app.dto.MemberUpdateDto;
import just.education.group_messaging_app.entity.Member;
import just.education.group_messaging_app.dto.MemberReadDto;
import just.education.group_messaging_app.dto.MemberCreateDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;


@Mapper
public interface MemberMapper {

    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "notes", target = "notes")
    public Member toMember(MemberCreateDto memberCreateDto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "group.id", target = "groupId")
    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "role.id", target = "roleId")
    @Mapping(source = "status.code", target = "statusCode")
    @Mapping(source = "notes", target = "notes")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    public MemberReadDto toMemberReadDto(Member member);

    @Mapping(source = "userId", target = "userId", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "notes", target = "notes", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void updateMember(MemberUpdateDto memberUpdateDto, @MappingTarget Member member);
}