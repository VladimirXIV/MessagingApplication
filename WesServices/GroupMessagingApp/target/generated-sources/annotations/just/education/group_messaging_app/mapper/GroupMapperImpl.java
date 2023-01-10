package just.education.group_messaging_app.mapper;

import javax.annotation.Generated;
import just.education.group_messaging_app.dto.GroupCreateDto;
import just.education.group_messaging_app.dto.GroupReadDto;
import just.education.group_messaging_app.dto.GroupStatusDto;
import just.education.group_messaging_app.entity.Group;
import just.education.group_messaging_app.entity.GroupStatus;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-19T20:33:18+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_341 (Oracle Corporation)"
)
public class GroupMapperImpl implements GroupMapper {

    @Override
    public GroupReadDto toGroupReadDto(Group group) {
        if ( group == null ) {
            return null;
        }

        GroupReadDto groupReadDto = new GroupReadDto();

        groupReadDto.setStatus( groupStatusToGroupStatusDto( group.getStatus() ) );
        groupReadDto.setId( group.getId() );
        groupReadDto.setTitle( group.getTitle() );
        groupReadDto.setMetaTitle( group.getMetaTitle() );
        groupReadDto.setSummary( group.getSummary() );
        groupReadDto.setInfo( group.getInfo() );
        groupReadDto.setCreatedBy( group.getCreatedBy() );
        groupReadDto.setUpdatedBy( group.getUpdatedBy() );
        groupReadDto.setCreatedAt( group.getCreatedAt() );
        groupReadDto.setUpdatedAt( group.getUpdatedAt() );

        return groupReadDto;
    }

    @Override
    public Group toGroup(GroupCreateDto createDto) {
        if ( createDto == null ) {
            return null;
        }

        Group group = new Group();

        group.setTitle( createDto.getTitle() );
        group.setMetaTitle( createDto.getMetaTitle() );
        group.setSummary( createDto.getSummary() );
        group.setInfo( createDto.getInfo() );

        return group;
    }

    protected GroupStatusDto groupStatusToGroupStatusDto(GroupStatus groupStatus) {
        if ( groupStatus == null ) {
            return null;
        }

        GroupStatusDto groupStatusDto = new GroupStatusDto();

        groupStatusDto.setCode( groupStatus.getCode() );
        groupStatusDto.setName( groupStatus.getName() );

        return groupStatusDto;
    }
}
