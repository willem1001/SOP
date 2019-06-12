package dao;

import model.User;

import java.util.List;

public interface UserDAO {
    User findById(long id);

    User findByUsername(String username);

    List<User> findAll();

    User create(User user);

    void update(User user);

    void delete(User user);

    User login(User user);

    List getFollowers(long userId);

    List getFollowing(long userId);

    List<String> authorize(String token, String username);

    List<Object> search(String searchTerm);


}
