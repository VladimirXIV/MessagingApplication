package just.education.convo_messaging_app.mapper;

import javax.annotation.processing.Generated;
import just.education.convo_messaging_app.dto.ConvoMessageCreateDto;
import just.education.convo_messaging_app.dto.ConvoMessageReadDto;
import just.education.convo_messaging_app.dto.ConvoMessageUpdateDto;
import just.education.convo_messaging_app.entity.ConvoMessage;
import just.education.convo_messaging_app.enums.ConversationAction;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-22T14:59:14+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Eclipse Adoptium)"
)
public class ConvoMessageMapperImpl implements ConvoMessageMapper {

    private final ConversationMapper conversationMapper = ConversationMapper.INSTANCE;

    @Override
    public ConvoMessage toConvoMessage(ConvoMessageCreateDto convoMessageCreateDto) {
        if ( convoMessageCreateDto == null ) {
            return null;
        }

        ConvoMessage convoMessage = new ConvoMessage();

        convoMessage.setSenderId( convoMessageCreateDto.getSenderId() );
        convoMessage.setText( convoMessageCreateDto.getText() );
        if ( convoMessageCreateDto.getAction() != null ) {
            convoMessage.setAction( Enum.valueOf( ConversationAction.class, convoMessageCreateDto.getAction() ) );
        }

        return convoMessage;
    }

    @Override
    public void update(ConvoMessageUpdateDto convoMessageUpdateDto, ConvoMessage convoMessage) {
        if ( convoMessageUpdateDto == null ) {
            return;
        }

        if ( convoMessageUpdateDto.getSenderId() != null ) {
            convoMessage.setSenderId( convoMessageUpdateDto.getSenderId() );
        }
        if ( convoMessageUpdateDto.getText() != null ) {
            convoMessage.setText( convoMessageUpdateDto.getText() );
        }
        if ( convoMessageUpdateDto.getAction() != null ) {
            convoMessage.setAction( Enum.valueOf( ConversationAction.class, convoMessageUpdateDto.getAction() ) );
        }
    }

    @Override
    public ConvoMessageReadDto toConvoMessageReadDto(ConvoMessage convoMessage) {
        if ( convoMessage == null ) {
            return null;
        }

        ConvoMessageReadDto convoMessageReadDto = new ConvoMessageReadDto();

        convoMessageReadDto.setId( convoMessage.getId() );
        convoMessageReadDto.setConversation( conversationMapper.toConversationReadDto( convoMessage.getConversation() ) );
        convoMessageReadDto.setSenderId( convoMessage.getSenderId() );
        convoMessageReadDto.setText( convoMessage.getText() );
        if ( convoMessage.getAction() != null ) {
            convoMessageReadDto.setAction( convoMessage.getAction().name() );
        }
        convoMessageReadDto.setCreatedAt( convoMessage.getCreatedAt() );
        convoMessageReadDto.setUpdatedAt( convoMessage.getUpdatedAt() );

        return convoMessageReadDto;
    }
}
