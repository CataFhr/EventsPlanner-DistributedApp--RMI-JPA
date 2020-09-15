package server.dao;

import server.model.User;

import java.util.Optional;

public interface IUserDao {

    User addUser(User user);

    Optional<User> findByUsername(String username);

    User getById(int userId);
}
