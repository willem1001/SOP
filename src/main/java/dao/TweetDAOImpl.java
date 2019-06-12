package dao;

import model.Tweet;
import model.User;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class TweetDAOImpl implements TweetDAO{

    private EntityManager em;

    public Tweet findById(long id) {
        return em.find(Tweet.class,id);
    }

    public List<Tweet> findByContent(String content) {
        return (List<Tweet>) em.createQuery("SELECT t FROM Tweet t WHERE t.content = :content").setParameter("content", content)
                .getResultList();
    }

    public List<Tweet> findAll() {
        return em.createQuery("SELECT t FROM Tweet t",Tweet.class).getResultList();
    }

    public Tweet create(Tweet tweet) {
        em.persist(tweet);
        em.flush();
        return tweet;
    }

    public void update(Tweet tweet) {
        Tweet t = null;
        if(!em.contains(tweet)){
            t = em.merge(tweet);
        }
        em.merge(t);
        em.flush();
    }

    @Transactional
    public void delete(Tweet tweet) {
        Tweet t = null;
        t = em.merge(tweet);
        em.remove(t);
    }

    public List<Tweet> getAllByUser(User user) {
        return null;
        //return em.createQuery("SELECT t FROM Tweet t WHERE t.user.username =:username").setParameter("username",user.getUsername()).getResultList();
    }

    public List<Object> getTweetsTimeline(long userId){
        return em.createQuery("SELECT t,u  FROM Tweet t, User u WHERE t.userId IN (SELECT uf FROM u.followingIds uf WHERE u.id = :userId) ORDER BY t.creationDate DESC").setParameter("userId",userId).getResultList();
    }

    public List<Object> search(String searchTerm){
        String searchTemp = "%"+searchTerm+"%";
        return em.createQuery("SELECT t, u FROM Tweet t, User u WHERE t.content LIKE :tempSearch").setParameter("tempSearch",searchTemp).getResultList();
    }
}
