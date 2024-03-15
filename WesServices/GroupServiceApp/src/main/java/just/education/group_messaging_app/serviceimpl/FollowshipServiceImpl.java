package just.education.group_messaging_app.serviceimpl;

import just.education.group_messaging_app.dto.FollowshipCreateDto;
import just.education.group_messaging_app.dto.FollowshipReadDto;
import just.education.group_messaging_app.dto.FollowshipUpdateDto;
import just.education.group_messaging_app.entity.Followship;
import just.education.group_messaging_app.entity.Group;
import just.education.group_messaging_app.mapper.FollowshipMapper;
import just.education.group_messaging_app.repository.FollowshipRepository;
import just.education.group_messaging_app.repository.GroupRepository;
import just.education.group_messaging_app.service.FollowshipService;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;


public class FollowshipServiceImpl implements FollowshipService {

    private FollowshipRepository followshipRepository;
    private FollowshipMapper followshipMapper;
    private GroupRepository groupRepository;


    public FollowshipServiceImpl() {
    }

    public FollowshipServiceImpl(FollowshipRepository followshipRepository, GroupRepository groupRepository, FollowshipMapper followshipMapper) {
        this.followshipRepository = followshipRepository;
        this.groupRepository = groupRepository;
        this.followshipMapper = followshipMapper;
    }

    @Override
    public FollowshipReadDto create(FollowshipCreateDto followshipCreateDto) {

        final Followship followship = this.followshipMapper.toFollowship(followshipCreateDto);

        // set group (foreign key)
        final Long groupId = followshipCreateDto.getGroupId();
        final Group group = this.groupRepository.getReferenceById(groupId);
        followship.setGroup(group);

        final Timestamp createdAt = Timestamp.from(Instant.now());
        group.setCreatedAt(createdAt);
        group.setUpdatedAt(createdAt);

        final Followship createdFollowship = this.followshipRepository.save(followship);

        return this.followshipMapper.toFollowerReadDto(createdFollowship);
    }

    @Override
    public FollowshipReadDto findById(long id) {

        final Followship followship = this.followshipRepository.getReferenceById(id);

        return this.followshipMapper.toFollowerReadDto(followship);
    }

    @Override
    public FollowshipReadDto updateById(long id, FollowshipUpdateDto followshipUpdateDto) {

        final Followship currentFollowship = this.followshipRepository.getReferenceById(id);

        this.followshipMapper.updateFollowship(followshipUpdateDto, currentFollowship);

        // update group (foreign key)
        final Long groupId = followshipUpdateDto.getGroupId();
        if (Objects.nonNull(groupId)) {
            final Group group = this.groupRepository.getReferenceById(groupId);
            currentFollowship.setGroup(group);
        }

        final Timestamp updatedAt = Timestamp.from(Instant.now());
        currentFollowship.setUpdatedAt(updatedAt);

        final Followship updatedFollowship = this.followshipRepository.save(currentFollowship);

        return this.followshipMapper.toFollowerReadDto(updatedFollowship);
    }

    @Override
    public FollowshipReadDto deleteById(long id) {

        final Followship followship = this.followshipRepository.getReferenceById(id);

        this.followshipRepository.delete(followship);

        return this.followshipMapper.toFollowerReadDto(followship);
    }
}
