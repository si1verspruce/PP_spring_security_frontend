package app.dao;

import app.model.User;

import java.util.List;

public interface UserDao {
    List<User> getUsers();
    void add(User user);
    void delete(long id);
    void update(long idToUpdate, User user);
    User getUserByLogin(String login);
}