package app.service;

import app.model.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();
    void add(User user, String[] roles);
    void delete(long id);
    void update(long idToUpdate, User user, String[] roles);
}
