import dao.TweetDAO;
import dao.UserDAO;
import manager.TweetManager;
import manager.UserManager;
import model.Tweet;
import model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TweetManagerTest {

    @InjectMocks
    private TweetManager tweetManager;

    @InjectMocks
    private UserManager userManager;

    @Mock
    private TweetDAO tweetDAO;

    @Mock
    private UserDAO userDAO;

    private User user;
    private Tweet tweet;

    @Test
    public void testCreateTweet(){
        user = new User();
        user.setUsername("username");
        user.setPassword("password");
        userManager.insert(user);
        tweet = new Tweet();
        tweet.setContent("content");
        tweet.setUserId(user.getId());
        tweetManager.insert(tweet);

        Mockito.verify(tweetDAO,Mockito.times(1)).create(tweet);

    }

    @Test
    public void testGetTweet(){
        user = new User();
        user.setUsername("username");
        user.setPassword("password");
        userManager.insert(user);
        tweet = new Tweet();
        tweet.setContent("content");
        tweet.setUserId(user.getId());
        tweetManager.insert(tweet);

        tweetManager.getById(tweet.getId());
        Mockito.verify(tweetDAO,Mockito.times(1)).findById(tweet.getId());

        tweetManager.getByContent(tweet.getContent());
        Mockito.verify(tweetDAO,Mockito.times(1)).findByContent(tweet.getContent());
    }

    @Test
    public void testUpdateTweet(){
        user = new User();
        user.setUsername("username");
        user.setPassword("password");
        userManager.insert(user);
        tweet = new Tweet();
        tweet.setContent("content");
        tweet.setUserId(user.getId());
        tweetManager.insert(tweet);

        tweet.setContent("Updated content");
        tweetManager.update(tweet);
        Mockito.verify(tweetDAO,Mockito.times(1)).update(tweet);
    }

}
