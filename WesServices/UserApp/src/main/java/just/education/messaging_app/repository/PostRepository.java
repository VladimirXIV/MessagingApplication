package just.education.messaging_app.repository;

import just.education.messaging_app.model.Post;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class PostRepository {

    EntityManagerFactory entityManagerFactory;


    public PostRepository() {
    }

    public PostRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }


    public Post create(Post post) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.persist(post);

        entityManager.getTransaction().commit();

        return post;
    }

    public Post retrieveById(Long id) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Post post = entityManager.find(Post.class, id);

        entityManager.getTransaction().commit();

        return post;
    }

    public Post update(Post post) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.persist(post);

        entityManager.getTransaction().commit();

        return post;
    }


    public void deleteById(Long id) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Post post = retrieveById(id);
        this.delete(post);

        entityManager.getTransaction().commit();
    }

    private void delete(Post post) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.remove(post);

        entityManager.getTransaction().commit();
    }
}