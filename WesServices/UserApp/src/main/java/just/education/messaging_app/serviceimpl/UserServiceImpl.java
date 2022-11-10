package just.education.messaging_app.serviceimpl;

import just.education.messaging_app.model.User;
import just.education.messaging_app.repository.UserRepository;
import just.education.messaging_app.service.UserService;

public class UserServiceImpl implements UserService {

    UserRepository userRepository;


    public UserServiceImpl() {

    }

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void create() {

    }

    public User findById(long id) {
        return userRepository.retrieveById(id);
    }

    public void update() {

    }

    public void delete(int id) {

    }
}
