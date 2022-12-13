package just.education.user_messaging_app.mapper;

import just.education.user_messaging_app.dto.MessageCreateDto;
import just.education.user_messaging_app.dto.MessageReadDto;
import just.education.user_messaging_app.dto.MessageUpdateDto;
import just.education.user_messaging_app.dto.PostReadDto;
import just.education.user_messaging_app.entity.Message;

import just.education.user_messaging_app.entity.Post;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class MessageMapper {

    private final ModelMapper mapper;


    public MessageMapper() {

        this.mapper = new ModelMapper();

        this.mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        this.mapper.createTypeMap(MessageCreateDto.class, Message.class, "messageCreateDtoToMessage")
                .addMappings(new PropertyMap<MessageCreateDto, Message>() {

                    @Override
                    protected void configure() {

                        map().setText(source.getText());
                    }
                });

        this.mapper.createTypeMap(MessageUpdateDto.class, Message.class, "messageUpdateDtoToMessageNonNullFields")
                .addMappings(new PropertyMap<MessageUpdateDto, Message>() {

                    @Override
                    protected void configure() {

                        when(Conditions.isNull()).skip().setText(source.getText());
                    }
                });

        this.mapper.createTypeMap(Message.class, MessageReadDto.class, "messageToMessageReadDto")
                .addMappings(new PropertyMap<Message, MessageReadDto>() {

                    @Override
                    protected void configure() {

                        map().setId(source.getId());
                        map().setSenderId(source.getSender().getId());
                        map().setReceiverId(source.getReceiver().getId());
                        map().setText(source.getText());
                        map().setCreatedAt(source.getCreatedAt());
                        map().setUpdatedAt(source.getUpdatedAt());
                    }
                });
    }

    public Message toMessage(MessageCreateDto messageCreateDto) {
        return this.mapper.map(messageCreateDto, Message.class, "messageCreateDtoToMessage");
    }

    public void toMessageNonNullFields(MessageUpdateDto messageUpdateDto, Message message) {
        this.mapper.map(messageUpdateDto, message, "messageUpdateDtoToMessageNonNullFields");
    }

    public MessageReadDto toMessageReadDto(Message message) {
        return this.mapper.map(message, MessageReadDto.class, "messageToMessageReadDto");
    }

    public List<MessageReadDto> toMessageReadDtoList(Collection<Message> messages) {

        List<MessageReadDto> messageReadDtoList = new ArrayList<>();

        if (messages != null) {

            for (Message message : messages) {
                messageReadDtoList.add(this.toMessageReadDto(message));
            }
        }

        return messageReadDtoList;
    }
}
