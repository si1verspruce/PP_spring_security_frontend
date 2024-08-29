package app.dao;

import app.model.User;

import java.util.List;

public interface UserDao {
    List<User> getUsers();
    void add(User user);
    void delete(Long id);
    void update(Long idToUpdate, User user);
    User getUserByLogin(String login);
    User getById(Long id);
}