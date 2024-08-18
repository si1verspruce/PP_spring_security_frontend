package app.service;

import app.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> getUsers();
    void add(User user, String[] roles);
    void delete(long id);
    void update(long idToUpdate, User user, String[] roles);
    User getById(long id);
}
