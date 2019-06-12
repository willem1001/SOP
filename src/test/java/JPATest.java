import model.Role;
import model.Tweet;
import model.User;
import org.junit.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Timestamp;
import java.util.List;

public class JPATest {

    static EntityManager em;
    static EntityManagerFactory emf;

    @Before
    public void init(){
        emf = null;
        em = null;
        emf = Persistence.createEntityManagerFactory("KwetterInMem");
        em = emf.createEntityManager();
    }



    @Test
    public void testUserPersist(){
        //Test if User can be saved
        em.getTransaction().begin();

        User reg1 = new User();
        reg1.setUsername("regular1");
        reg1.setPassword("password");
        reg1.setRole(Role.REGULAR);

        User reg2 = new User();
        reg2.setUsername("regular2");
        reg2.setPassword("password");
        reg2.setRole(Role.REGULAR);

        User mod1 = new User();
        mod1.setUsername("moderator1");
        mod1.setPassword("password");
        mod1.setRole(Role.MODERATOR);

        User mod2 = new User();
        mod2.setUsername("moderator2");
        mod2.setPassword("password");
        mod2.setRole(Role.MODERATOR);

        em.persist(reg1);
        em.persist(reg2);
        em.persist(mod1);
        em.persist(mod2);
        em.getTransaction().commit();

        List<User> users  = em.createQuery("SELECT u FROM User u").getResultList();

        Assert.assertNotNull(users);

    }

    @Test
    public void testUserUpdate(){
        //Test if user can be updated
        User reg1 = new User();
        reg1.setUsername("regular1");
        reg1.setPassword("password");
        String bio = "bio";
        String website = "website";
        em.getTransaction().begin();
        em.persist(reg1);
        em.getTransaction().commit();
        Assert.assertEquals(em.createQuery("SELECT u FROM User u").getSingleResult(),reg1);

        em.getTransaction().begin();
        reg1.setBio(bio);
        reg1.setWebsite(website);
        em.merge(reg1);
        em.getTransaction().commit();

        Assert.assertEquals(em.createQuery("SELECT u FROM User u").getSingleResult(),reg1);

    }

    @Test
    public void testUserDelete(){
        em.getTransaction().begin();

        User reg1 = new User();
        reg1.setUsername("regular1");
        reg1.setPassword("password");
        em.persist(reg1);
        em.getTransaction().commit();

        Assert.assertEquals(em.createQuery("SELECT u FROM User u").getSingleResult(),reg1);

        em.getTransaction().begin();
        em.remove(reg1);
        em.getTransaction().commit();

        List<User> users = em.createQuery("SELECT u FROM User u").getResultList();

        Assert.assertEquals(users.size(),0);
    }

    @Test
    public void testTweetPersist(){
        //Test if Tweet can be saved
        em.getTransaction().begin();

        User reg1 = new User();
        reg1.setUsername("regular1");
        reg1.setPassword("password");
        Tweet tweet1 = new Tweet();
        tweet1.setContent("content");
        tweet1.setUserId(reg1.getId());
        tweet1.setCreationDate(new Timestamp(System.currentTimeMillis()));

        em.persist(reg1);
        em.persist(tweet1);
        em.getTransaction().commit();

        Assert.assertEquals(em.createQuery("SELECT t FROM Tweet t").getSingleResult(),tweet1);


    }

    @Test
    public void testTweetUpdate(){
        //Test if tweet can be updated
        em.getTransaction().begin();

        User reg1 = new User();
        reg1.setUsername("regular1");
        reg1.setPassword("password");
        Tweet tweet1 = new Tweet();
        tweet1.setContent("content");
        tweet1.setUserId(reg1.getId());
        tweet1.setCreationDate(new Timestamp(System.currentTimeMillis()));

        em.persist(reg1);
        em.persist(tweet1);

        Tweet tweet2 = (Tweet) em.createQuery("SELECT t FROM Tweet t").getSingleResult();
        tweet2.setContent("updated content");
        em.merge(tweet2);
        em.getTransaction().commit();

        Assert.assertEquals(((Tweet) em.createQuery("SELECT t FROM Tweet t").getSingleResult()).getContent(),"updated content");
    }

    @Test
    public void testTweetDelete(){
        //Test if tweet can be deleted
        em.getTransaction().begin();

        User reg1 = new User();
        reg1.setUsername("regular1");
        reg1.setPassword("password");

        Tweet tweet1 = new Tweet();
        tweet1.setContent("content");
        tweet1.setUserId(reg1.getId());
        tweet1.setCreationDate(new Timestamp(System.currentTimeMillis()));

        em.persist(reg1);
        em.persist(tweet1);
        em.getTransaction().commit();

        Assert.assertEquals(em.createQuery("SELECT t FROM Tweet t").getSingleResult(),tweet1);

        em.getTransaction().begin();
        em.remove(tweet1);
        em.getTransaction().commit();

        List<Tweet> tweets =  em.createQuery("SELECT t FROM Tweet t").getResultList();
        Assert.assertEquals(0,tweets.size());
    }
}
