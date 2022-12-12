package just.education.messaging_app.serviceimpl;

import just.education.messaging_app.dto.FriendshipCreateDto;
import just.education.messaging_app.dto.FriendshipReadDto;
import just.education.messaging_app.dto.FriendshipUpdateDto;
import just.education.messaging_app.entity.Friendship;
import just.education.messaging_app.mapper.FriendshipMapper;
import just.education.messaging_app.service.FriendshipService;
import just.education.messaging_app.repository.FriendshipRepository;

import java.sql.Timestamp;
import java.time.Instant;


public class FriendshipServiceImpl implements FriendshipService {

    private FriendshipRepository friendshipRepository;
    private FriendshipMapper friendshipMapper;


    public FriendshipServiceImpl() {
    }

    public FriendshipServiceImpl(FriendshipRepository friendshipRepository, FriendshipMapper friendshipMapper) {
        this.friendshipRepository = friendshipRepository;
        this.friendshipMapper = friendshipMapper;
    }


    @Override
    public FriendshipReadDto create(final long senderId, FriendshipCreateDto friendshipCreateDto) {

        final Friendship friendship = friendshipMapper.toFriendship(friendshipCreateDto);

        final long receiverId = friendshipCreateDto.getReceiverId();
        final long typeId = friendshipCreateDto.getTypeId();
        final long statusCode = friendshipCreateDto.getStatusCode();

        final Timestamp createdAt = Timestamp.from(Instant.now());
        friendship.setCreatedAt(createdAt);
        friendship.setUpdatedAt(createdAt);

        final Friendship createdFriendship = friendshipRepository.create(senderId, receiverId, typeId, statusCode, friendship);

        return friendshipMapper.toFriendshipReadDto(createdFriendship);
    }

    @Override
    public FriendshipReadDto findById(long id) {

        final Friendship friendship = friendshipRepository.retrieveById(id);

        return friendshipMapper.toFriendshipReadDto(friendship);
    }

    @Override
    public FriendshipReadDto updateById(final long id, FriendshipUpdateDto friendshipUpdateDto) {

        final Friendship currentFriendship = friendshipRepository.retrieveById(id);

        friendshipMapper.toFriendshipNonNullFields(friendshipUpdateDto, currentFriendship);

        final Timestamp updatedAt = Timestamp.from(Instant.now());
        currentFriendship.setUpdatedAt(updatedAt);

        final Friendship updatedFriendship = friendshipRepository.update(currentFriendship);

        return friendshipMapper.toFriendshipReadDto(updatedFriendship);
    }

    @Override
    public FriendshipReadDto deleteById(long id) {

        final Friendship friendship = friendshipRepository.deleteById(id);

        return friendshipMapper.toFriendshipReadDto(friendship);
    }
}