package manager;

import dao.UserDAO;
import mail.EmailSender;
import model.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.mail.MessagingException;


import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Stateless
public class UserManager {

    @Inject
    private UserDAO userDAO;

    @Inject
    private EmailSender mail;

    public User getById(long id){
        return userDAO.findById(id);
    }

    public List<User> getAll(){
        return userDAO.findAll();
    }

    public void insert(User user){
        userDAO.create(user);
        user.addFollowerUser(user);
        user.addFollowingUser(user);
        userDAO.update(user);
        final User u = user;

        ExecutorService emailExecutor = Executors.newSingleThreadExecutor();
        emailExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    mail.sendMail("New Registration","A new user registered with username: " +u.getUsername()+" ","valkenburg1997@gmail.com",u.getUsername());
                } catch (IOException e) {
                   e.printStackTrace();
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        });
        emailExecutor.shutdown();
      }

    public User getByUsername(String username){
        return userDAO.findByUsername(username);
    }

    public void update(User user){
        userDAO.update(user);
    }

    public void delete(User user){
        userDAO.delete(user);
    }

    public User login(User user){
        User u = userDAO.findByUsername(user.getUsername());
        u.setToken(user.getToken());
        //u.setTwoFactor(true);
        userDAO.update(u);
        return userDAO.login(user);
    }

    public List<User> getFollowers(long userId){
        return userDAO.getFollowers(userId);
    }

    public List<User> getFollowing(long userId){
        return userDAO.getFollowing(userId);
    }

    public boolean authorize(String token, String username){

        List<String> tkn = userDAO.authorize(token,username);
        if(tkn.isEmpty()){
            return false;
        }
        return tkn.get(0).equals(token);

    }

    public List<Object> search(String searchTerm){
        return userDAO.search(searchTerm);
    }
}

