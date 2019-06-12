package dao;

import model.Tweet;
import model.User;

import java.util.List;

public interface TweetDAO {

    Tweet findById(long id);

    List<Tweet> findByContent(String content);

    List<Tweet> findAll();

    Tweet create(Tweet tweet);

    void update(Tweet tweet);

    void delete(Tweet tweet);

    List<Tweet> getAllByUser(User user);

    List<Object> getTweetsTimeline(long userId);

    List<Object> search(String searchTerm);
}
