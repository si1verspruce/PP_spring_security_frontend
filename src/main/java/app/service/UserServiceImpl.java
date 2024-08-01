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
        if (user == null || checkForAnyInvalid(user)) {
            return;
        }
        userDao.add(formatUser(user));
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
        if (user == null || idToUpdate < 0 || checkForAnyInvalid(user)) {
            return;
        }
        userDao.update(idToUpdate, formatUser(user));
    }

    private User formatUser(User user) {
        return user;
    }

    public boolean checkForAnyInvalid(User user) {
        return user.getFirstName().isEmpty() || user.getLastName().isEmpty() || user.getAge() < 0
                || user.getRoles() == null;
    }
}
