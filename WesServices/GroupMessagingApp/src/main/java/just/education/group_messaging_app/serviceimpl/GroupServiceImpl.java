package just.education.group_messaging_app.serviceimpl;

import just.education.group_messaging_app.dto.GroupCreateDto;
import just.education.group_messaging_app.dto.GroupReadDto;
import just.education.group_messaging_app.dto.MessageUpdateDto;
import just.education.group_messaging_app.entity.Group;
import just.education.group_messaging_app.entity.GroupStatus;
import just.education.group_messaging_app.mapper.GroupMapper;
import just.education.group_messaging_app.service.GroupService;
import just.education.group_messaging_app.repository.GroupRepository;
import just.education.group_messaging_app.repository.GroupStatusRepository;

import java.sql.Timestamp;
import java.time.Instant;


public class GroupServiceImpl implements GroupService {

    private GroupRepository groupRepository;
    private GroupStatusRepository groupStatusRepository;
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

        long statusCode = groupCreateDto.getStatusCode();

        final GroupStatus groupStatus = groupStatusRepository.findByCode(statusCode);

        final Group group = groupMapper.toGroup(groupCreateDto);
        group.setStatus(groupStatus);

        final Timestamp createdAt = Timestamp.from(Instant.now());
        group.setCreatedAt(createdAt);
        group.setUpdatedAt(createdAt);

        final Group createdGroup = groupRepository.save(group);

        return groupMapper.toGroupReadDto(createdGroup);
    }

    @Override
    public GroupReadDto findById(long id) {

        final Group group = groupRepository.getReferenceById(id);

        return groupMapper.toGroupReadDto(group);
    }

    @Override
    public GroupReadDto updateById(long id, MessageUpdateDto messageUpdateDto) {



        return null;
    }

    @Override
    public GroupReadDto deleteById(long id) {

        final Group group = groupRepository.getReferenceById(id);

        groupRepository.delete(group);

        return groupMapper.toGroupReadDto(group);
    }
}