package just.education.group_messaging_app.service;

import just.education.group_messaging_app.dto.FollowshipCreateDto;
import just.education.group_messaging_app.dto.FollowshipReadDto;
import just.education.group_messaging_app.dto.FollowshipUpdateDto;

public interface FollowshipService {

    public FollowshipReadDto findById(final long id);

    public FollowshipReadDto create(FollowshipCreateDto followshipCreateDto);

    public FollowshipReadDto updateById(final long id, FollowshipUpdateDto followshipUpdateDto);

    public FollowshipReadDto deleteById(final long id);
}