package just.education.messaging_app.mapper;

import just.education.messaging_app.dto.FollowshipCreateDto;
import just.education.messaging_app.dto.FollowshipReadDto;
import just.education.messaging_app.dto.FollowshipUpdateDto;
import just.education.messaging_app.entity.Followship;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;


public class FollowshipMapper {

    private final ModelMapper mapper;

    public FollowshipMapper() {

        this.mapper = new ModelMapper();

        this.mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        this.mapper.createTypeMap(FollowshipCreateDto.class, Followship.class, "followshipCreateDtoToFollowship")
                .addMappings(new PropertyMap<FollowshipCreateDto, Followship>() {

                    @Override
                    protected void configure() {

                    }
                });

        this.mapper.createTypeMap(Followship.class, FollowshipReadDto.class, "followshipToFollowshipReadDto")
                .addMappings(new PropertyMap<Followship, FollowshipReadDto>() {

                    @Override
                    protected void configure() {

                    }
                });

        this.mapper.createTypeMap(FollowshipUpdateDto.class, Followship.class, "followshipUpdateDtoToFollowshipNonNullFields")
                .addMappings(new PropertyMap<FollowshipUpdateDto, Followship>() {

                    @Override
                    protected void configure() {

                    }
                });
    }

    public Followship toFollowship(FollowshipCreateDto followshipCreateDto) {
        return this.mapper.map(followshipCreateDto, Followship.class, "followshipCreateDtoToFollowship");
    }

    public FollowshipReadDto toFollowshipReadDto(Followship followship) {
        return this.mapper.map(followship, FollowshipReadDto.class, "followshipToFollowshipReadDto");
    }

    public void toFollowshipNonNullFields(FollowshipUpdateDto followshipUpdateDto, Followship followship) {
        this.mapper.map(followshipUpdateDto, followship, "followshipUpdateDtoToFollowshipNonNullFields");
    }
}