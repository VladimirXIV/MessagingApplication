package just.education.group_messaging_app.serviceimpl;

import just.education.group_messaging_app.dto.GroupReadDto;
import just.education.group_messaging_app.dto.GroupCreateDto;
import just.education.group_messaging_app.dto.GroupUpdateDto;
import just.education.group_messaging_app.entity.Group;
import just.education.group_messaging_app.entity.GroupStatus;
import just.education.group_messaging_app.mapper.GroupMapper;
import just.education.group_messaging_app.service.GroupService;
import just.education.group_messaging_app.repository.GroupRepository;
import just.education.group_messaging_app.repository.GroupStatusRepository;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;


public class GroupServiceImpl implements GroupService {

    private GroupStatusRepository groupStatusRepository;
    private GroupRepository groupRepository;
    private GroupMapper groupMapper;


    public GroupServiceImpl() {
    }

    public GroupServiceImpl(GroupRepository groupRepository, GroupStatusRepository groupStatusRepository, GroupMapper groupMapper) {
        this.groupRepository = groupRepository;
        this.groupStatusRepository = groupStatusRepository;
        this.groupMapper = groupMapper;
    }


    @Override
    public GroupReadDto create(GroupCreateDto groupCreateDto) {

        final Group group = this.groupMapper.toGroup(groupCreateDto);

        // set status (foreign key)
        final Long statusCode = groupCreateDto.getStatusCode();
        final GroupStatus status = this.groupStatusRepository.getReferenceById(statusCode);
        group.setStatus(status);

        final Timestamp createdAt = Timestamp.from(Instant.now());
        group.setCreatedAt(createdAt);
        group.setUpdatedAt(createdAt);

        final Group createdGroup = this.groupRepository.save(group);

        return this.groupMapper.toGroupReadDto(createdGroup);
    }

    @Override
    public GroupReadDto findById(long id) {

        final Group group = this.groupRepository.getReferenceById(id);

        return this.groupMapper.toGroupReadDto(group);
    }

    @Override
    public GroupReadDto updateById(long id, GroupUpdateDto groupUpdateDto) {

        final Group currentGroup = this.groupRepository.getReferenceById(id);

        this.groupMapper.updateGroup(groupUpdateDto, currentGroup);

        // update status (foreign key)
        final Long statusCode = groupUpdateDto.getStatusCode();
        if (Objects.nonNull(statusCode)) {
            final GroupStatus groupStatus = this.groupStatusRepository.getGroupStatusByCode(statusCode);
            currentGroup.setStatus(groupStatus);
        }

        final Timestamp updatedAt = Timestamp.from(Instant.now());
        currentGroup.setUpdatedAt(updatedAt);

        final Group updatedGroup = this.groupRepository.save(currentGroup);

        return this.groupMapper.toGroupReadDto(updatedGroup);
    }

    @Override
    public GroupReadDto deleteById(long id) {

        final Group group = this.groupRepository.getReferenceById(id);

        this.groupRepository.delete(group);

        return this.groupMapper.toGroupReadDto(group);
    }
}