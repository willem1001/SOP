package models;

import org.junit.Before;
import org.junit.Test;

import java.sql.Time;
import java.sql.Timestamp;

import static org.junit.Assert.*;

public class TweetTest {

    public Tweet tweet;
    public User user;
    @Before
    public void setUp() throws Exception {
        tweet = new Tweet();
        user = new User();
    }

    @Test
    public void getTitle() {
        tweet.setTitle("Title");
        assertEquals("Title",tweet.getTitle());
    }

    @Test
    public void setTitle() {
        assertNull(tweet.getTitle());
        tweet.setTitle("Title");
        assertNotNull(tweet.getTitle());
        assertEquals("Title", tweet.getTitle());
    }

    @Test
    public void getContent() {
        tweet.setContent("Content");
        assertEquals("Content", tweet.getContent());
    }

    @Test
    public void setContent() {
        assertNull(tweet.getContent());
        tweet.setContent("Content");
        assertNotNull(tweet.getContent());
        assertEquals("Content", tweet.getContent());
    }

    @Test
    public void getCreationDate() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        tweet.setCreationDate(ts);
        assertEquals(ts,tweet.getCreationDate());
    }

    @Test
    public void setCreationDate() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        assertNull(tweet.getCreationDate());
        tweet.setCreationDate(ts);
        assertNotNull(tweet.getCreationDate());
        assertEquals(ts,tweet.getCreationDate());
    }

    @Test
    public void getCreator() {
        tweet.setCreator(user);
        assertEquals(user,tweet.getCreator());
    }

    @Test
    public void setCreator() {
        assertNull(tweet.getCreator());
        tweet.setCreator(user);
        assertNotNull(tweet.getCreator());
        assertEquals(user,tweet.getCreator());
    }

    @Test
    public void addLike() {
        assertEquals(0,tweet.getLikes().size());
        tweet.addLike(user);
        assertEquals(1,tweet.getLikes().size());
    }

    @Test
    public void getLikes() {
        tweet.addLike(user);
        assertEquals(1,tweet.getLikes().size());
    }
}
