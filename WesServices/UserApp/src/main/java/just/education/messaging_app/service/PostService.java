package just.education.messaging_app.service;

import just.education.messaging_app.model.Post;

public interface PostService {

    public void create();

    public Post findById(final long id);

    public void update();

    public void delete(final int id);
}
