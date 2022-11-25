package just.education.messaging_app.repository;

import just.education.messaging_app.entity.Post;
import just.education.messaging_app.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

public class PostRepository {

    private EntityManagerFactory entityManagerFactory;


    public PostRepository() {
    }

    public PostRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }


    public Post create(Post post, long userId) {

        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        final EntityTransaction entityTransaction = entityManager.getTransaction();

        try {

            entityTransaction.begin();

            User user = entityManager.getReference(User.class, userId);

            post.setUser(user);

            entityManager.persist(post);

        } catch (PersistenceException pEx) {

            entityTransaction.rollback();

            throw pEx;

        } finally {

            entityTransaction.commit();

            entityManager.close();
        }

        return post;
    }

    public Post retrieveById(Long id) {

        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        final EntityTransaction entityTransaction = entityManager.getTransaction();

        Post post = null;

        try {

            entityTransaction.begin();

            post = entityManager.find(Post.class, id);

        } finally {

            if (post != null) {
                entityTransaction.commit();
            } else {
                entityTransaction.rollback();
            }

            entityManager.close();
        }

        return post;
    }

    public Post update(Post post) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.persist(post);

        entityManager.getTransaction().commit();

        return post;
    }


    public Post deleteById(Long id) {

        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        final EntityTransaction entityTransaction = entityManager.getTransaction();

        Post post = null;

        try {

            entityTransaction.begin();

            post = entityManager.find(Post.class, id);

            entityManager.remove(post);

        } catch (PersistenceException pEx) {

            entityTransaction.rollback();
            throw pEx;

        } finally {

            entityTransaction.commit();

            entityManager.close();
        }

        return post;
    }
}