package dao;

import model.User;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class UserDAOImpl implements UserDAO {



    public User findById(long id) {
        //return em.find(User.class,id);
        return null;
    }

    public User findByUsername(String username) {
        //return  (User) em.createQuery("SELECT u FROM User u WHERE u.username = :username").setParameter("username",username)
        //        .getSingleResult();
        return null;
    }

    public List<User> findAll() {
        //return em.createQuery("SELECT u FROM User u",User.class).getResultList();
        return null;
    }

    public User create(User user) {
       // em.persist(user);
       // em.flush();
       // return user;
            return null;
    }

   
    public void update(User user) {

        
        //em.merge(user);
    }

    public void delete(User user) {
        //User u = null;
      //  if(!em.contains(user)){
    //        u = em.merge(user);
  //      }
//
       // em.remove(u);
       
    }

    public User login(User user) {
        //Object result = em.createQuery("SELECT u FROM User u WHERE u.username = :username AND u.password = :password").setParameter("username",user.getUsername()).setParameter("password",user.getPassword()).getSingleResult();
        //em.flush();
        //return (User) result;
        return null;
    }

    public List<User> getFollowers(long userId){
            return null;
        //return em.createQuery("SELECT u FROM User u WHERE  u.followerIds IN (SELECT uf FROM u.followerIds uf)AND  u.id =:userId").setParameter("userId",userId).getResultList();
    }

    public List<User> getFollowing(long userId){
            return null;
       // return em.createQuery("SELECT u FROM User u WHERE  u.followingIds IN (SELECT uf FROM u.followingIds uf)AND u.id = :userId").setParameter("userId",userId).getResultList();
    }

    public List<String> authorize(String token,String username){
        return null;
       //return em.createQuery("SELECT u.token FROM User u WHERE u.token = :token AND u.username = :username").setParameter("token",token).setParameter("username",username).getResultList();
    }

    public List<Object> search(String searchTerm){
        return null;
        //String searchTemp = "%"+searchTerm+"%";
        //return em.createQuery("SELECT u FROM User u WHERE u.username LIKE :tempSearch").setParameter("tempSearch",searchTemp).getResultList();


    }


}
