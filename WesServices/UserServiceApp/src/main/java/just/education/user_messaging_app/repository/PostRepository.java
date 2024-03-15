package just.education.user_messaging_app.repository;

import just.education.user_messaging_app.entity.Post;
import just.education.user_messaging_app.entity.User;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;

import java.util.List;


public class PostRepository {

    private EntityManagerFactory entityManagerFactory;


    public PostRepository() {
    }

    public PostRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }


    public Post create(long senderId, long receiverId, Post post) {

        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        final EntityTransaction entityTransaction = entityManager.getTransaction();

        try {

            entityTransaction.begin();

            User sender = entityManager.getReference(User.class, senderId);
            User receiver = entityManager.getReference(User.class, receiverId);

            post.setSender(sender);
            post.setReceiver(receiver);

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

    public List<Post> retrieveByReceiverId(Long id) {

        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        final EntityTransaction entityTransaction = entityManager.getTransaction();

        final Query query = entityManager.createQuery("select p from Post p where p.receiver.id = :id");
        query.setParameter("id", id);

        List<Post> posts = null;

        try {

            entityTransaction.begin();

            posts = query.getResultList();

        } catch (PersistenceException pEx) {

            entityTransaction.rollback();
            throw pEx;

        } finally {

            if (posts != null) {
                entityTransaction.commit();
            } else {
                entityTransaction.rollback();
            }

            entityManager.close();
        }

        return posts;
    }

    public List<Post> retrieveBySenderId(Long id) {

        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        final EntityTransaction entityTransaction = entityManager.getTransaction();

        final Query query = entityManager.createQuery("select p from Post p where p.sender.id = :id");
        query.setParameter("id", id);

        List<Post> posts = null;

        try {

            entityTransaction.begin();

            posts = query.getResultList();

        } catch (PersistenceException pEx) {

            entityTransaction.rollback();
            throw pEx;

        } finally {

            if (posts != null) {
                entityTransaction.commit();
            } else {
                entityTransaction.rollback();
            }

            entityManager.close();
        }

        return posts;
    }

    public Post update(Post post) {

        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        final EntityTransaction entityTransaction = entityManager.getTransaction();

        try {

            entityTransaction.begin();

            entityManager.merge(post);

        } catch (PersistenceException pEx) {

            entityTransaction.rollback();

            throw pEx;

        } finally {

            entityTransaction.commit();

            entityManager.close();
        }

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