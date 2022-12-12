package just.education.messaging_app.repository;


import just.education.messaging_app.entity.User;
import just.education.messaging_app.entity.Friendship;
import just.education.messaging_app.entity.FriendshipType;
import just.education.messaging_app.entity.FriendshipStatus;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;


public class FriendshipRepository {

    private EntityManagerFactory entityManagerFactory;


    public FriendshipRepository(){
    }

    public FriendshipRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }


    public Friendship create(final long senderId, final long receiverId, final long typeId, final long statusCode, Friendship friendship) {

        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        final EntityTransaction entityTransaction = entityManager.getTransaction();

        try {

            entityTransaction.begin();

            User sender = entityManager.getReference(User.class, senderId);
            User receiver = entityManager.getReference(User.class, receiverId);

            FriendshipType type = entityManager.getReference(FriendshipType.class, typeId);
            FriendshipStatus status = entityManager.getReference(FriendshipStatus.class, statusCode);

            friendship.setSender(sender);
            friendship.setReceiver(receiver);
            friendship.setType(type);
            friendship.setStatus(status);

            entityManager.persist(friendship);

        } catch (PersistenceException pEx) {

            entityTransaction.rollback();

            throw pEx;

        } finally {

            entityTransaction.commit();

            entityManager.close();
        }

        return friendship;
    }

    public Friendship retrieveById(final long id) {

        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        final EntityTransaction entityTransaction = entityManager.getTransaction();

        Friendship friendship = null;

        try {

            entityTransaction.begin();

            friendship = entityManager.find(Friendship.class, id);

        } finally {

            if (friendship != null) {
                entityTransaction.commit();
            } else {
                entityTransaction.rollback();
            }

            entityManager.close();
        }

        return friendship;
    }

    public Friendship update(Friendship friendship) {

        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        final EntityTransaction entityTransaction = entityManager.getTransaction();

        try {

            entityTransaction.begin();

            entityManager.merge(friendship);

        } catch (PersistenceException pEx) {

            entityTransaction.rollback();

            throw pEx;

        } finally {

            entityTransaction.commit();

            entityManager.close();
        }

        return friendship;

    }

    public Friendship deleteById(Long id) {

        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        final EntityTransaction entityTransaction = entityManager.getTransaction();

        Friendship friendship = null;

        try {

            entityTransaction.begin();

            friendship = entityManager.find(Friendship.class, id);

        } catch (PersistenceException pEx) {

            entityTransaction.rollback();
            throw pEx;

        } finally {

            entityTransaction.commit();

            entityManager.close();
        }

        return friendship;
    }
}