package just.education.user_messaging_app.mapper;

import just.education.user_messaging_app.dto.FriendshipCreateDto;
import just.education.user_messaging_app.dto.FriendshipReadDto;

import just.education.user_messaging_app.dto.FriendshipUpdateDto;
import just.education.user_messaging_app.dto.PostCreateDto;
import just.education.user_messaging_app.entity.Friendship;
import just.education.user_messaging_app.entity.Post;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;


public class FriendshipMapper {

    private final ModelMapper mapper;


    public FriendshipMapper() {

        this.mapper = new ModelMapper();

        this.mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        this.mapper.createTypeMap(FriendshipCreateDto.class, Friendship.class, "friendshipCreateDtoToFriendship")
                .addMappings(new PropertyMap<FriendshipCreateDto, Friendship>() {

                    @Override
                    protected void configure() {

                        map().setNotes(source.getNotes());
                    }
                });

        this.mapper.createTypeMap(FriendshipUpdateDto.class, Friendship.class, "friendshipUpdateDtoToFriendshipNonNullFields")
                .addMappings(new PropertyMap<FriendshipUpdateDto, Friendship>() {

                    @Override
                    protected void configure() {

                        when(Conditions.isNull()).skip().setNotes(source.getNotes());
                    }
                });

        this.mapper.createTypeMap(FriendshipReadDto.class, Friendship.class, "friendshipToFriendshipReadDto")
                .addMappings(new PropertyMap<FriendshipReadDto, Friendship>() {

                    @Override
                    protected void configure() {


                    }
                });
    }

    public Friendship toFriendship(FriendshipCreateDto friendshipCreateDto) {
        return this.mapper.map(friendshipCreateDto, Friendship.class, "friendshipCreateDtoToFriendship");
    }

    public FriendshipReadDto toFriendshipReadDto(Friendship friendship) {
        return this.mapper.map(friendship, FriendshipReadDto.class, "friendshipToFriendshipReadDto");
    }

    public void toFriendshipNonNullFields(FriendshipUpdateDto friendshipUpdateDto, Friendship friendship) {
        this.mapper.map(friendshipUpdateDto, friendship, "friendshipUpdateDtoToFriendshipNonNullFields");
    }
}
