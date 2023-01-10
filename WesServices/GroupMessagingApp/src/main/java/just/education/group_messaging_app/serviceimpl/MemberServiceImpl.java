package just.education.group_messaging_app.serviceimpl;

import just.education.group_messaging_app.dto.MemberCreateDto;
import just.education.group_messaging_app.dto.MemberReadDto;
import just.education.group_messaging_app.dto.MemberUpdateDto;
import just.education.group_messaging_app.entity.Member;
import just.education.group_messaging_app.mapper.MemberMapper;
import just.education.group_messaging_app.repository.MemberRepository;
import just.education.group_messaging_app.service.MemberService;

import java.sql.Timestamp;
import java.time.Instant;


public class MemberServiceImpl implements MemberService {

    private MemberRepository memberRepository;
    private MemberMapper memberMapper;


    public MemberServiceImpl() {
    }

    public MemberServiceImpl(MemberRepository memberRepository, MemberMapper memberMapper) {
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
    }


    @Override
    public MemberReadDto create(MemberCreateDto memberCreateDto) {

        final Member member = memberMapper.toMember(memberCreateDto);

        final Timestamp createdAt = Timestamp.from(Instant.now());
        member.setCreatedAt(createdAt);
        member.setUpdatedAt(createdAt);

        final Member createdMember = memberRepository.save(member);

        return memberMapper.toMemberReadDto(createdMember);
    }

    @Override
    public MemberReadDto findById(long id) {

        final Member member = memberRepository.getReferenceById(id);

        return memberMapper.toMemberReadDto(member);
    }

    @Override
    public MemberReadDto updateById(long id, MemberUpdateDto memberUpdateDto) {
        return null;
    }

    @Override
    public MemberReadDto deleteById(long id) {

        final Member member = memberRepository.getReferenceById(id);

        memberRepository.delete(member);

        return memberMapper.toMemberReadDto(member);
    }
}