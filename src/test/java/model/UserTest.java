package model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class UserTest {

    User user;
    Tweet tweet;
    User following;
    User follower;
    List<User> testUsers;
    List<Tweet> testTweets;
    @Before
    public void setUp() throws Exception {
        testTweets = new ArrayList<Tweet>();
        testUsers = new ArrayList<User>();

        user = new User();
        user.setUsername("User123");
        user.setRole(Role.REGULAR);
        user.setId(1);

        tweet = new Tweet();
        tweet.setContent("content");
        tweet.setUserId(user.getId());
        tweet.setCreationDate(new Timestamp(System.currentTimeMillis()));
        tweet.setId(1);

        following = new User();
        following.setUsername("following");
        following.setRole(Role.REGULAR);
        user.setId(2);

        follower = new User();
        follower.setUsername("follower");
        follower.setRole(Role.REGULAR);
        follower.setId(3);
    }

    @Test
    public void addTweet() {
        user.addTweet(tweet);
        testTweets.add(tweet);

        Assert.assertEquals(testTweets.get(0).getId(),(long) user.getTweetIds().get(0));
        user.removeTweet(tweet);
        testTweets.remove(tweet);
    }

    @Test
    public void removeTweet() {
        user.addTweet(tweet);
        testTweets.add(tweet);
        Assert.assertEquals(testTweets.get(0).getId(),(long) user.getTweetIds().get(0));
        user.removeTweet(tweet);
        Assert.assertEquals(0,user.getTweetIds().size());
    }

    @Test
    public void addFollowingUser() {
        user.addFollowingUser(following);
        Assert.assertEquals(following.getId(),(long)user.getFollowingIds().get(0));
        user.addFollowingUser(following);
        Assert.assertEquals(0,user.getFollowingIds().size());
    }

    @Test
    public void addFollowerUser() {
        user.addFollowerUser(follower);
        Assert.assertEquals(follower.getId(),(long)user.getFollowerIds().get(0));
        user.addFollowerUser(follower);
        Assert.assertEquals(0,user.getFollowerIds().size());
    }
}