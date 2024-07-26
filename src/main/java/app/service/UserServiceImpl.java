package app.service;

import app.dao.UserDao;
import app.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public List<User> getUsers() {
        return userDao.getUsers();
    }

    @Transactional
    @Override
    public void add(User user) {
        if (user == null || user.checkForAnyInvalid()) {
            return;
        }
        userDao.add(user);
    }

    @Transactional
    @Override
    public void delete(long id) {
        if (id < 0) {
            return;
        }
        userDao.delete(id);
    }

    @Transactional
    @Override
    public void update(long idToUpdate, User user) {
        if (user == null || idToUpdate < 0 || user.checkForAnyInvalid()) {
            return;
        }
        userDao.update(idToUpdate, user);
    }
}
