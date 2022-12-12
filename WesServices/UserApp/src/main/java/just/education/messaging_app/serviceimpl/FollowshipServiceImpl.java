package just.education.messaging_app.serviceimpl;

import just.education.messaging_app.dto.FollowshipCreateDto;
import just.education.messaging_app.dto.FollowshipReadDto;
import just.education.messaging_app.dto.FollowshipUpdateDto;
import just.education.messaging_app.entity.Followship;
import just.education.messaging_app.mapper.FollowshipMapper;
import just.education.messaging_app.repository.FollowshipRepository;
import just.education.messaging_app.service.FollowshipService;

import java.sql.Timestamp;
import java.time.Instant;

public class FollowshipServiceImpl implements FollowshipService {

    private FollowshipRepository followshipRepository;
    private FollowshipMapper followshipMapper;


    public FollowshipServiceImpl() {
    }

    public FollowshipServiceImpl(FollowshipRepository followshipRepository, FollowshipMapper followshipMapper) {
        this.followshipRepository = followshipRepository;
        this.followshipMapper = followshipMapper;
    }


    @Override
    public FollowshipReadDto create(final long followerId, FollowshipCreateDto followshipCreateDto) {

        final Followship followship = followshipMapper.toFollowship(followshipCreateDto);

        final long followedId = followshipCreateDto.getFollowedId();
        final long typeId = followshipCreateDto.getTypeId();

        final Timestamp createdAt = Timestamp.from(Instant.now());
        followship.setCreatedAt(createdAt);
        followship.setUpdatedAt(createdAt);

        final Followship createdFollowship = followshipRepository.create(followerId, followedId, typeId, followship);

        return followshipMapper.toFollowshipReadDto(createdFollowship);
    }

    @Override
    public FollowshipReadDto findById(long id) {

        final Followship followship = followshipRepository.retrieveById(id);

        return followshipMapper.toFollowshipReadDto(followship);
    }

    @Override
    public FollowshipReadDto updateById(final long id, FollowshipUpdateDto followshipUpdateDto) {

        final Followship currentFollowship = followshipRepository.retrieveById(id);

        followshipMapper.toFollowshipNonNullFields(followshipUpdateDto, currentFollowship);

        final Timestamp updatedAt = Timestamp.from(Instant.now());
        currentFollowship.setUpdatedAt(updatedAt);

        final Followship updatedFollowship = followshipRepository.update(currentFollowship);

        return followshipMapper.toFollowshipReadDto(updatedFollowship);
    }

    @Override
    public FollowshipReadDto deleteById(long id) {

        final Followship followship = followshipRepository.deleteById(id);

        return followshipMapper.toFollowshipReadDto(followship);
    }
}