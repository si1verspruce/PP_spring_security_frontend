package app.service;

import app.dao.UserDao;
import app.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserDao userDao, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDao = userDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Transactional
    @Override
    public List<User> getUsers() {
        return userDao.getUsers();
    }

    @Transactional
    @Override
    public void add(User user) {
        if (user == null || checkForInvalidData(buildUser(user))) {
            return;
        }
        userDao.add(user);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        if (id < 0) {
            return;
        }
        userDao.delete(id);
    }

    @Transactional
    @Override
    public void update(Long idToUpdate, User user) {
        if (user == null || idToUpdate < 0 || checkForInvalidData(buildUser(user))) {
            return;
        }
        userDao.update(idToUpdate, user);
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.getUserByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }

    @Transactional
    @Override
    public User getById(Long id){
        User user = userDao.getById(id);
        if (user == null) {
            throw new RuntimeException("User with id " + id + " not found");
        }
        return user;
    }

    private User buildUser(User user) {
        user.setPassword("{bcrypt}" + bCryptPasswordEncoder.encode(user.getPassword()));
        return user;
    }

    private boolean checkForInvalidData(User user) {
        return user.getFirstName().isEmpty() || user.getLastName().isEmpty() || user.getAge() < 0
                || user.getUsername().isEmpty() || user.getPassword().isEmpty() || user.getAuthorities().isEmpty();
    }
}
