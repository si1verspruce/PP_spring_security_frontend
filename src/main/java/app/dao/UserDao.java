package app.dao;

import app.model.User;

import java.util.List;

public interface UserDao {
    List<User> getUsers();
    void add(User user);
    void delete(User user);
    void update(User user);
}