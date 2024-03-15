package just.education.user_messaging_app.service;

import just.education.user_messaging_app.dto.FriendshipReadDto;
import just.education.user_messaging_app.dto.FriendshipCreateDto;
import just.education.user_messaging_app.dto.FriendshipUpdateDto;


public interface FriendshipService {

    public FriendshipReadDto create(final long senderId, FriendshipCreateDto friendshipCreateDto);

    public FriendshipReadDto findById(final long id);

    public FriendshipReadDto updateById(final long id, FriendshipUpdateDto friendshipUpdateDto);

    public FriendshipReadDto deleteById(final long id);
}
