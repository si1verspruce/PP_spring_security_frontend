package app.service;

import app.model.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();
    void add(User user);
    void delete(User user);
    void update(User user);
}
