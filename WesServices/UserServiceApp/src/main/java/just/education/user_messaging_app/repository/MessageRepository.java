package just.education.user_messaging_app.repository;

import just.education.user_messaging_app.entity.Message;
import just.education.user_messaging_app.entity.User;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;

import java.util.List;


public class MessageRepository {

    private EntityManagerFactory entityManagerFactory;


    public MessageRepository() {
    }

    public MessageRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }


    public Message create(final long sourceId, final long targetId, Message message) {

        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        final EntityTransaction entityTransaction = entityManager.getTransaction();

        try {

            entityTransaction.begin();

            User sender = entityManager.find(User.class, sourceId);
            User receiver = entityManager.find(User.class, targetId);

            message.setSender(sender);
            message.setReceiver(receiver);

            entityManager.persist(message);

        } catch (PersistenceException pEx) {

            entityTransaction.rollback();

            throw pEx;

        } finally {

            entityTransaction.commit();

            entityManager.close();
        }

        return message;
    }

    public Message retrieveById(Long id) {

        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        final EntityTransaction entityTransaction = entityManager.getTransaction();

        Message message = null;

        try {

            entityTransaction.begin();

            message = entityManager.find(Message.class, id);

        } finally {

            if (message != null) {
                entityTransaction.commit();
            } else {
                entityTransaction.rollback();
            }

            entityManager.close();
        }

        return message;
    }

    public List<Message> retrieveBySenderId(Long id) {

        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        final EntityTransaction entityTransaction = entityManager.getTransaction();

        final Query query = entityManager.createQuery("select m from Message m where m.sender.id = :id");
        query.setParameter("id", id);

        List<Message> messages = null;

        try {

            entityTransaction.begin();

            messages = query.getResultList();

        } finally {

            if (messages != null) {
                entityTransaction.commit();
            } else {
                entityTransaction.rollback();
            }

            entityManager.close();
        }

        return messages;
    }

    public List<Message> retrieveByReceiverId(Long id) {

        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        final EntityTransaction entityTransaction = entityManager.getTransaction();

        final Query query = entityManager.createQuery("select m from Message m where m.receiver.id = :id");
        query.setParameter("id", id);

        List<Message> messages = null;

        try {

            entityTransaction.begin();

            messages = query.getResultList();

        } finally {

            if (messages != null) {
                entityTransaction.commit();
            } else {
                entityTransaction.rollback();
            }

            entityManager.close();
        }

        return messages;
    }

    public Message update(Message message) {

        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        final EntityTransaction entityTransaction = entityManager.getTransaction();

        try {

            entityTransaction.begin();

            entityManager.merge(message);

        } catch (PersistenceException pEx) {

            entityTransaction.rollback();
            throw pEx;

        } finally {

            entityTransaction.commit();

            entityManager.close();
        }

        return message;
    }

    public Message deleteById(Long id) {

        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        final EntityTransaction entityTransaction = entityManager.getTransaction();

        Message message = null;

        try {

            entityTransaction.begin();

            message = entityManager.find(Message.class, id);

            entityManager.remove(message);

        } catch (PersistenceException pEx) {

            entityTransaction.rollback();
            throw pEx;

        } finally {

            entityTransaction.commit();

            entityManager.close();
        }

        return message;
    }
}
