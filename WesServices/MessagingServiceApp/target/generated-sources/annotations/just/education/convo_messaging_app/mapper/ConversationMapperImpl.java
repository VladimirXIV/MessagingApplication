package just.education.convo_messaging_app.mapper;

import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import just.education.convo_messaging_app.dto.ConversationCreateDto;
import just.education.convo_messaging_app.dto.ConversationReadDto;
import just.education.convo_messaging_app.dto.ConversationUpdateDto;
import just.education.convo_messaging_app.dto.ParticipantReadDto;
import just.education.convo_messaging_app.entity.Conversation;
import just.education.convo_messaging_app.entity.Participant;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-22T14:59:14+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Eclipse Adoptium)"
)
public class ConversationMapperImpl implements ConversationMapper {

    private final ParticipantMapper participantMapper = ParticipantMapper.INSTANCE;

    @Override
    public Conversation toConversation(ConversationCreateDto conversationCreateDto) {
        if ( conversationCreateDto == null ) {
            return null;
        }

        Conversation conversation = new Conversation();

        conversation.setName( conversationCreateDto.getName() );
        conversation.setDescription( conversationCreateDto.getDescription() );
        conversation.setOwnerId( conversationCreateDto.getOwnerId() );

        return conversation;
    }

    @Override
    public void updateConversation(ConversationUpdateDto conversationUpdateDto, Conversation conversation) {
        if ( conversationUpdateDto == null ) {
            return;
        }

        if ( conversationUpdateDto.getName() != null ) {
            conversation.setName( conversationUpdateDto.getName() );
        }
        if ( conversationUpdateDto.getDescription() != null ) {
            conversation.setDescription( conversationUpdateDto.getDescription() );
        }
        if ( conversationUpdateDto.getOwnerId() != null ) {
            conversation.setOwnerId( conversationUpdateDto.getOwnerId() );
        }
    }

    @Override
    public ConversationReadDto toConversationReadDto(Conversation conversation) {
        if ( conversation == null ) {
            return null;
        }

        ConversationReadDto conversationReadDto = new ConversationReadDto();

        conversationReadDto.setId( conversation.getId() );
        conversationReadDto.setName( conversation.getName() );
        conversationReadDto.setDescription( conversation.getDescription() );
        conversationReadDto.setOwnerId( conversation.getOwnerId() );
        conversationReadDto.setCreatedAt( conversation.getCreatedAt() );
        conversationReadDto.setParticipants( participantSetToParticipantReadDtoSet( conversation.getParticipants() ) );
        Set<String> set1 = conversation.getconvoMessageIds();
        if ( set1 != null ) {
            conversationReadDto.setconvoMessageIds( new LinkedHashSet<String>( set1 ) );
        }

        return conversationReadDto;
    }

    protected Set<ParticipantReadDto> participantSetToParticipantReadDtoSet(Set<Participant> set) {
        if ( set == null ) {
            return null;
        }

        Set<ParticipantReadDto> set1 = new LinkedHashSet<ParticipantReadDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Participant participant : set ) {
            set1.add( participantMapper.toParticipantReadDto( participant ) );
        }

        return set1;
    }
}
