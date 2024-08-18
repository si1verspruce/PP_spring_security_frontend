package app.service;

import app.dao.UserDao;
import app.model.Role;
import app.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final ApplicationContext context;
    private final UserDao userDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(ApplicationContext context, UserDao userDao, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.context = context;
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
    public void add(User user, String[] roleNames) {
        if (user == null || roleNames == null || checkForInvalidData(buildUser(user, List.of(roleNames)))) {
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
    public void update(long idToUpdate, User user, String[] roleNames) {
        if (user == null || idToUpdate < 0 || checkForInvalidData(buildUser(user, List.of(roleNames)))) {
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
    public User getById(long id){
        User user = userDao.getById(id);
        if (user == null) {
            throw new RuntimeException("User with id " + id + " not found");
        }
        return user;
    }

    private User buildUser(User user, List<String> roleNames) {
        List<Role> rolesDB = context.getBean(RoleService.class).getRoles();
        user.setRoles(rolesDB.stream().filter(roleDB -> roleNames.contains(roleDB.getAuthority()))
                .collect(Collectors.toSet()));
        user.setPassword("{bcrypt}" + bCryptPasswordEncoder.encode(user.getPassword()));
        return user;
    }

    private boolean checkForInvalidData(User user) {
        return user.getFirstName().isEmpty() || user.getLastName().isEmpty() || user.getAge() < 0
                || user.getLogin().isEmpty() || user.getPassword().isEmpty() || user.getRoles().isEmpty();
    }
}
