package dao;

import model.User;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class UserDAOImpl implements UserDAO {


    private EntityManager em;

    public User findById(long id) {
        return em.find(User.class,id);
    }

    public User findByUsername(String username) {
        return  (User) em.createQuery("SELECT u FROM User u WHERE u.username = :username").setParameter("username",username)
                .getSingleResult();
    }

    public List<User> findAll() {
        return em.createQuery("SELECT u FROM User u",User.class).getResultList();
    }

    public User create(User user) {
        em.persist(user);
        em.flush();
        return user;

    }

    @Transactional
    public void update(User user) {


        em.merge(user);
    }

    public void delete(User user) {
        User u = null;
        if(!em.contains(user)){
            u = em.merge(user);
        }

        em.remove(u);
    }

    public User login(User user) {
        Object result = em.createQuery("SELECT u FROM User u WHERE u.username = :username AND u.password = :password").setParameter("username",user.getUsername()).setParameter("password",user.getPassword()).getSingleResult();
        em.flush();
        return (User) result;
    }

    public List<User> getFollowers(long userId){

        return em.createQuery("SELECT u FROM User u WHERE  u.followerIds IN (SELECT uf FROM u.followerIds uf)AND  u.id =:userId").setParameter("userId",userId).getResultList();
    }

    public List<User> getFollowing(long userId){

        return em.createQuery("SELECT u FROM User u WHERE  u.followingIds IN (SELECT uf FROM u.followingIds uf)AND u.id = :userId").setParameter("userId",userId).getResultList();
    }

    public List<String> authorize(String token,String username){
       return em.createQuery("SELECT u.token FROM User u WHERE u.token = :token AND u.username = :username").setParameter("token",token).setParameter("username",username).getResultList();
    }

    public List<Object> search(String searchTerm){
        String searchTemp = "%"+searchTerm+"%";
        return em.createQuery("SELECT u FROM User u WHERE u.username LIKE :tempSearch").setParameter("tempSearch",searchTemp).getResultList();


    }


}
