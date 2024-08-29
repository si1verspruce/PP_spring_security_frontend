package app.dao;

import app.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> getUsers() {
        return em.createQuery("from User u left join fetch u.roles", User.class).getResultList();
    }

    @Override
    public void add(User user) {
        em.persist(user);
    }

    @Override
    public void delete(Long id) {
        User attachedUser = em.find(User.class, id);
        if (attachedUser != null) {
            em.remove(attachedUser);
        }
    }

    @Override
    public void update(Long idToUpdate, User user) {
        User userToUpdate = em.find(User.class, idToUpdate);
        if (userToUpdate != null) {
            em.merge(mergePersistentWithModel(userToUpdate, user));
        }
    }

    @Override
    public User getUserByLogin(String login) {
        try {
            return em.createQuery("from User u left join fetch u.roles where u.login = :login", User.class)
                    .setParameter("login", login).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public User getById(Long id) {
        try {
            return em.createQuery("from User u left join fetch u.roles where u.id = :id", User.class)
                    .setParameter("id", id).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    private User mergePersistentWithModel(User persistent, User model) {
        Long id = persistent.getId();
        persistent = model;
        persistent.setId(id);
        return persistent;
    }
}