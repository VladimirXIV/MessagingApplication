package just.education.messaging_app.repository;

import just.education.messaging_app.entity.User;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;

import java.util.List;


public class UserRepository {

    private EntityManagerFactory entityManagerFactory;


    public UserRepository() {
    }

    public UserRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }


    public User create(User user) {

        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        final EntityTransaction entityTransaction = entityManager.getTransaction();

        try {

            entityTransaction.begin();

            entityManager.persist(user); // persist and set ID

        } catch (PersistenceException pEx) {

            entityTransaction.rollback();

            throw pEx;

        } finally {

            entityTransaction.commit();

            entityManager.close();
        }

        return user;
    }

    public User retrieveById(Long id) {

        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        final EntityTransaction entityTransaction = entityManager.getTransaction();

        User user = null;

        try {

            entityTransaction.begin();

            user = entityManager.find(User.class, id);

        } finally {

            if (user != null) {
                entityTransaction.commit();
            } else {
                entityTransaction.rollback();
            }

            entityManager.close();
        }

        return user;
    }

    public List<User> retrieveAll() {

        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        final EntityTransaction entityTransaction = entityManager.getTransaction();

        final Query query = entityManager.createQuery("select u from User u");

        List<User> users = null;

        try {

            entityTransaction.begin();

            users = query.getResultList();

        } catch (PersistenceException pEx) {

            entityTransaction.rollback();
            throw pEx;

        } finally {

            if (users != null) {
                entityTransaction.commit();
            } else {
                entityTransaction.rollback();
            }

            entityManager.close();
        }

        return users;
    }

    /**
     * Update user by id {@link User#getId()} with non-null fields
     *
     * @param user user to update
     * @return updated user
     */
    public User update(User user) {

        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        final EntityTransaction entityTransaction = entityManager.getTransaction();

        User persistedUser = null;

        try {

            entityTransaction.begin();

            persistedUser = entityManager.merge(user);

        } catch (PersistenceException pEx) {

            entityTransaction.rollback();
            throw pEx;

        } finally {

            entityTransaction.commit();

            entityManager.close();
        }

        return persistedUser;
    }


    public User deleteById(Long id) {

        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        final EntityTransaction entityTransaction = entityManager.getTransaction();

        User user = null;

        try {

            entityTransaction.begin();

            user = entityManager.find(User.class, id);

            entityManager.remove(user);

        } catch (PersistenceException pEx) {

            entityTransaction.rollback();
            throw pEx;

        } finally {

            entityTransaction.commit();

            entityManager.close();
        }

        return user;
    }
}