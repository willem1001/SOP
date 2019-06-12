package models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {
    Tweet tweet;
    User user;
    @Before
    public void setUp() throws Exception {
        tweet = new Tweet();
        user = new User();
    }

    @Test
    public void getFirstName() {
        user.setFirstName("FirstName");
        assertEquals("FirstName", user.getFirstName());
    }

    @Test
    public void setFirstName() {
        assertNull(user.getFirstName());
        user.setFirstName("FirstName");
        assertNotNull(user.getFirstName());
        assertEquals("FirstName",user.getFirstName());
    }

    @Test
    public void getLastName() {
        user.setLastName("LastName");
        assertEquals("LastName", user.getLastName());
    }

    @Test
    public void setLastName() {
        assertNull(user.getLastName());
        user.setLastName("LastName");
        assertNotNull(user.getLastName());
        assertEquals("LastName", user.getLastName());
    }

    @Test
    public void getUsername() {
        user.setUsername("username");
        assertEquals("username",user.getUsername());
    }

    @Test
    public void setUsername() {
        assertNull(user.getUsername());
        user.setUsername("username");
        assertNotNull(user.getUsername());
        assertEquals("username",user.getUsername());
    }

    @Test
    public void getEmailAddress() {
        user.setEmailAddress("email");
        assertEquals("email", user.getEmailAddress());
    }

    @Test
    public void setEmailAddress() {
        assertNull(user.getEmailAddress());
        user.setEmailAddress("email");
        assertNotNull(user.getEmailAddress());
        assertEquals("email",user.getEmailAddress());
    }

    @Test
    public void getPassword() {
        user.setPassword("password");
        assertEquals("password", user.getPassword());
    }

    @Test
    public void setPassword() {
        assertNull(user.getPassword());
        user.setPassword("password");
        assertNotNull(user.getPassword());
        assertEquals("password", user.getPassword());
    }

    @Test
    public void addFollower() {
        assertEquals(0, user.getFollowers().size());
        user.addFollower(user);
        assertEquals(1,user.getFollowers().size());
        assertEquals(user,user.getFollowers().get(0));
        user.addFollower(user);
        assertEquals(0, user.getFollowers().size());
    }

    @Test
    public void addTweet() {
        assertEquals(0, user.getTweets().size());
        user.addTweet(tweet);
        assertEquals(1, user.getTweets().size());
        assertEquals(tweet, user.getTweets().get(0));
    }

    @Test
    public void getTweets() {
        user.addTweet(tweet);
        assertEquals(tweet,user.getTweets().get(0));
    }
}
