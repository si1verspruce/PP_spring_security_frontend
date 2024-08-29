package app.service;

import app.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> getUsers();
    void add(User user);
    void delete(Long id);
    void update(Long idToUpdate, User user);
    User getById(Long id);
}
