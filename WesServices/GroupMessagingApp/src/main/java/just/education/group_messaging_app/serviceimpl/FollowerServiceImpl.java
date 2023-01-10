package just.education.group_messaging_app.serviceimpl;

import just.education.group_messaging_app.mapper.FollowerMapper;
import just.education.group_messaging_app.repository.FollowerRepository;
import just.education.group_messaging_app.service.FollowerService;


public class FollowerServiceImpl implements FollowerService {

    private FollowerRepository followerRepository;
    private FollowerMapper followerMapper;


    public FollowerServiceImpl() {
    }

    public FollowerServiceImpl(FollowerRepository followerRepository, FollowerMapper followerMapper) {
        this.followerRepository = followerRepository;
        this.followerMapper = followerMapper;
    }
}
