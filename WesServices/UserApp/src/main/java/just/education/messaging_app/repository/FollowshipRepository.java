package just.education.messaging_app.repository;

import just.education.messaging_app.entity.User;
import just.education.messaging_app.entity.Followship;
import just.education.messaging_app.entity.FollowshipType;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;


public class FollowshipRepository {

    private EntityManagerFactory entityManagerFactory;


    public FollowshipRepository() {
    }

    public FollowshipRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }


    public Followship create(final long followerId, final long followedId, final long typeId, Followship followship) {

        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        final EntityTransaction entityTransaction = entityManager.getTransaction();

        try {

            entityTransaction.begin();

            User follower = entityManager.getReference(User.class, followerId);
            User followed = entityManager.getReference(User.class, followedId);
            FollowshipType type = entityManager.getReference(FollowshipType.class, typeId);

            followship.setFollower(follower);
            followship.setFollowed(followed);
            followship.setType(type);

            entityManager.persist(followship);

        } catch (PersistenceException pEx) {

            entityTransaction.rollback();

            throw pEx;

        } finally {

            entityTransaction.commit();

            entityManager.close();
        }

        return followship;
    }

    public Followship retrieveById(final long id) {

        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        final EntityTransaction entityTransaction = entityManager.getTransaction();

        Followship followship = null;

        try {

            entityTransaction.begin();

            followship = entityManager.find(Followship.class, id);

        } finally {

            if (followship != null) {
                entityTransaction.commit();
            } else {
                entityTransaction.rollback();
            }

            entityManager.close();
        }

        return followship;
    }

    public Followship update(Followship followship) {

        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        final EntityTransaction entityTransaction = entityManager.getTransaction();

        try {

            entityTransaction.begin();

            entityManager.merge(followship);

        } catch (PersistenceException pEx) {

            entityTransaction.rollback();

            throw pEx;

        } finally {

            entityTransaction.commit();

            entityManager.close();
        }

        return followship;
    }

    public Followship deleteById(Long id) {

        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        final EntityTransaction entityTransaction = entityManager.getTransaction();

        Followship followship = null;

        try {

            entityTransaction.begin();

            followship = entityManager.find(Followship.class, id);

        } catch (PersistenceException pEx) {

            entityTransaction.rollback();
            throw pEx;

        } finally {

            entityTransaction.commit();

            entityManager.close();
        }

        return followship;
    }
}