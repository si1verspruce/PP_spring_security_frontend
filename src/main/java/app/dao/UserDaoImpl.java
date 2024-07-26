package app.dao;

import app.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public List<User> getUsers() {
        return em.createQuery("from User", User.class).getResultList();
    }

    @Override
    public void add(User user) {
        em.persist(user);
    }

    @Override
    public void delete(long id) {
        User attachedUser = em.find(User.class, id);
        if (attachedUser != null) {
            em.remove(attachedUser);
        }
    }

    @Override
    public void update(long idToUpdate, User user) {
        User userToUpdate = em.find(User.class, idToUpdate);
        if (userToUpdate != null) {
            userToUpdate.mergeWith(user);
            em.merge(userToUpdate);
        }
    }
}
