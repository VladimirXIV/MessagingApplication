package just.education.group_messaging_app.service;

import just.education.group_messaging_app.dto.GroupReadDto;
import just.education.group_messaging_app.dto.GroupCreateDto;
import just.education.group_messaging_app.dto.GroupUpdateDto;

public interface GroupService {

    public GroupReadDto findById(final long id);

    public GroupReadDto create(GroupCreateDto groupCreateDto);

    public GroupReadDto updateById(final long id, GroupUpdateDto groupUpdateDto);

    public GroupReadDto deleteById(final long id);
}