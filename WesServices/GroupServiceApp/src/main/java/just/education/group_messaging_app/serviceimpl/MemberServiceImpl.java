package just.education.group_messaging_app.serviceimpl;

import just.education.group_messaging_app.dto.MemberCreateDto;
import just.education.group_messaging_app.dto.MemberReadDto;
import just.education.group_messaging_app.dto.MemberUpdateDto;
import just.education.group_messaging_app.entity.*;
import just.education.group_messaging_app.mapper.MemberMapper;
import just.education.group_messaging_app.repository.MemberStatusRepository;
import just.education.group_messaging_app.repository.GroupRoleRepository;
import just.education.group_messaging_app.repository.MemberRepository;
import just.education.group_messaging_app.repository.GroupRepository;
import just.education.group_messaging_app.service.MemberService;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;


public class MemberServiceImpl implements MemberService {

    private MemberStatusRepository memberStatusRepository;
    private GroupRoleRepository groupRoleRepository;
    private MemberRepository memberRepository;
    private GroupRepository groupRepository;
    private MemberMapper memberMapper;


    public MemberServiceImpl() {
    }

    public MemberServiceImpl(MemberRepository memberRepository,
                             MemberStatusRepository memberStatusRepository,
                             GroupRoleRepository groupRoleRepository,
                             GroupRepository groupRepository,
                             MemberMapper memberMapper) {
        this.memberRepository = memberRepository;
        this.memberStatusRepository = memberStatusRepository;
        this.groupRoleRepository = groupRoleRepository;
        this.groupRepository = groupRepository;
        this.memberMapper = memberMapper;
    }


    @Override
    public MemberReadDto create(MemberCreateDto memberCreateDto) {

        final Member member = this.memberMapper.toMember(memberCreateDto);

        // set status (foreign key)
        final Long statusCode = memberCreateDto.getStatusCode();
        final MemberStatus status = this.memberStatusRepository.getMemberStatusByCode(statusCode);
        member.setStatus(status);

        // set group (foreign key)
        final Long groupId = memberCreateDto.getGroupId();
        final Group group = this.groupRepository.getReferenceById(groupId);
        member.setGroup(group);

        // set role (foreign key)
        final Long roleId = memberCreateDto.getRoleId();
        final GroupRole role = this.groupRoleRepository.getReferenceById(roleId);
        member.setRole(role);

        final Timestamp createdAt = Timestamp.from(Instant.now());
        member.setCreatedAt(createdAt);
        member.setUpdatedAt(createdAt);

        final Member createdMember = this.memberRepository.save(member);

        return this.memberMapper.toMemberReadDto(createdMember);
    }

    @Override
    public MemberReadDto findById(long id) {

        final Member member = this.memberRepository.getReferenceById(id);

        return this.memberMapper.toMemberReadDto(member);
    }

    @Override
    public MemberReadDto updateById(long id, MemberUpdateDto memberUpdateDto) {

        final Member currentMember = this.memberRepository.getReferenceById(id);

        this.memberMapper.updateMember(memberUpdateDto, currentMember);

        // update group (foreign key)
        final Long groupId = memberUpdateDto.getGroupId();
        if (Objects.nonNull(groupId)) {
            final Group group = this.groupRepository.getReferenceById(groupId);
            currentMember.setGroup(group);
        }

        // update group role (foreign key)
        final Long roleId = memberUpdateDto.getRoleId();
        if (Objects.nonNull(roleId)) {
            final GroupRole role = this.groupRoleRepository.getReferenceById(roleId);
            currentMember.setRole(role);
        }

        //  update status code (foreign key)
        final Long statusCode = memberUpdateDto.getStatusCode();
        if (Objects.nonNull(statusCode)) {
            final MemberStatus status = this.memberStatusRepository.getMemberStatusByCode(statusCode);
            currentMember.setStatus(status);
        }

        final Timestamp updatedAt = Timestamp.from(Instant.now());
        currentMember.setUpdatedAt(updatedAt);

        final Member updatedMember = this.memberRepository.save(currentMember);

        return this.memberMapper.toMemberReadDto(updatedMember);
    }

    @Override
    public MemberReadDto deleteById(long id) {

        final Member member = this.memberRepository.getReferenceById(id);

        this.memberRepository.delete(member);

        return this.memberMapper.toMemberReadDto(member);
    }
}