package manager;

import model.Tweet;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Stateless
public class TweetManager  {


    public Tweet getById(long id){
        //return tweetDAO.findById(id);
        return null;
    }

    public List<Tweet> getAll(){
        //return tweetDAO.findAll();
        return null;
    }

    public Tweet insert(Tweet tweet){
        //return tweetDAO.create(tweet);
        return null;
    }

    public List<Tweet> getByContent(String content){
       // return tweetDAO.findByContent(content);
        return null;
    }

    public void update(Tweet tweet){
        //tweetDAO.update(tweet);

    }

    public void delete(Tweet tweet){
        //tweetDAO.delete(tweet);
    }

    public List<Object> getTweetsTimeline(long userId){
        return null;//return tweetDAO.getTweetsTimeline(userId);
    }

    public List<Object> search(String searchTerm){
        //return tweetDAO.search(searchTerm);
        return null;
    }
}
