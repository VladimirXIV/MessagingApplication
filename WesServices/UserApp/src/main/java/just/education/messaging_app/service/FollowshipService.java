package just.education.messaging_app.service;

import just.education.messaging_app.dto.FollowshipCreateDto;
import just.education.messaging_app.dto.FollowshipReadDto;
import just.education.messaging_app.dto.FollowshipUpdateDto;
import just.education.messaging_app.entity.Followship;


public interface FollowshipService {

    public FollowshipReadDto create(final long followerId, FollowshipCreateDto followshipCreateDto);

    public FollowshipReadDto findById(final long id);

    public FollowshipReadDto updateById(final long id, FollowshipUpdateDto followshipUpdateDto);

    public FollowshipReadDto deleteById(final long id);
}
