package just.education.group_messaging_app.service;

import just.education.group_messaging_app.dto.MemberCreateDto;
import just.education.group_messaging_app.dto.MemberReadDto;
import just.education.group_messaging_app.dto.MemberUpdateDto;
import just.education.group_messaging_app.dto.MessageUpdateDto;


public interface MemberService {

    public MemberReadDto findById(final long id);

    public MemberReadDto create(MemberCreateDto memberCreateDto);

    public MemberReadDto updateById(final long id, MemberUpdateDto memberUpdateDto);

    public MemberReadDto deleteById(final long id);
}