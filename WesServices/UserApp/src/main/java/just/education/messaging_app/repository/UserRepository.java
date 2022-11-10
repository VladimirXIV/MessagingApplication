package just.education.messaging_app.repository;

import just.education.messaging_app.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


public class UserRepository {

    EntityManagerFactory entityManagerFactory;


    public UserRepository() {
    }

    public UserRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }


    public User create(User user) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.persist(user);

        entityManager.getTransaction().commit();

        return user;
    }

    public User retrieveById(Long id) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        User user = entityManager.find(User.class, id);

        entityManager.getTransaction().commit();

        return user;
    }

    public User update(User user) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.persist(user);

        entityManager.getTransaction().commit();

        return user;
    }


    public void deleteById(Long id) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        User user = retrieveById(id);
        this.delete(user);

        entityManager.getTransaction().commit();
    }

    private void delete(User user) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.remove(user);

        entityManager.getTransaction().commit();
    }
}