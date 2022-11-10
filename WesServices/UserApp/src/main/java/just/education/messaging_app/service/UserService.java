package just.education.messaging_app.service;


import just.education.messaging_app.model.User;

public interface UserService {

    public void create();

    public User findById(final long id);

    public void update();

    public void delete(final int id);
}
