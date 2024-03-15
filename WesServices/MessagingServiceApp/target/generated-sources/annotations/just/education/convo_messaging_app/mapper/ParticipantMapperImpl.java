package just.education.convo_messaging_app.mapper;

import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import just.education.convo_messaging_app.dto.ConversationReadDto;
import just.education.convo_messaging_app.dto.ParticipantCreateDto;
import just.education.convo_messaging_app.dto.ParticipantReadDto;
import just.education.convo_messaging_app.dto.ParticipantUpdateDto;
import just.education.convo_messaging_app.entity.Conversation;
import just.education.convo_messaging_app.entity.Participant;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-22T14:59:14+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Eclipse Adoptium)"
)
public class ParticipantMapperImpl implements ParticipantMapper {

    private final ConversationMapper conversationMapper = ConversationMapper.INSTANCE;

    @Override
    public Participant toParticipant(ParticipantCreateDto participantCreateDto) {
        if ( participantCreateDto == null ) {
            return null;
        }

        Participant participant = new Participant();

        participant.setUserId( participantCreateDto.getUserId() );
        participant.setNickname( participantCreateDto.getNickname() );

        return participant;
    }

    @Override
    public void update(ParticipantUpdateDto participantUpdateDto, Participant participant) {
        if ( participantUpdateDto == null ) {
            return;
        }

        if ( participantUpdateDto.getUserId() != null ) {
            participant.setUserId( participantUpdateDto.getUserId() );
        }
        if ( participantUpdateDto.getNickname() != null ) {
            participant.setNickname( participantUpdateDto.getNickname() );
        }
    }

    @Override
    public ParticipantReadDto toParticipantReadDto(Participant participant) {
        if ( participant == null ) {
            return null;
        }

        ParticipantReadDto participantReadDto = new ParticipantReadDto();

        participantReadDto.setId( participant.getId() );
        participantReadDto.setUserId( participant.getUserId() );
        participantReadDto.setNickname( participant.getNickname() );
        participantReadDto.setEnteredAt( participant.getEnteredAt() );
        Set<String> set = participant.getconvoMessageIds();
        if ( set != null ) {
            participantReadDto.setconvoMessageIds( new LinkedHashSet<String>( set ) );
        }
        participantReadDto.setConversations( conversationSetToConversationReadDtoSet( participant.getConversations() ) );

        return participantReadDto;
    }

    protected Set<ConversationReadDto> conversationSetToConversationReadDtoSet(Set<Conversation> set) {
        if ( set == null ) {
            return null;
        }

        Set<ConversationReadDto> set1 = new LinkedHashSet<ConversationReadDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Conversation conversation : set ) {
            set1.add( conversationMapper.toConversationReadDto( conversation ) );
        }

        return set1;
    }
}
