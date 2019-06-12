package jsf;

import manager.TweetManager;
import manager.UserManager;
import model.Role;
import model.Tweet;
import model.User;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

public class AdminBacking implements Serializable {


    private int tweetId;
    private String username;
    private String userRoleName;
    private Role role;

    private List<User> users;
    private String twatDeleteMessage;
    private String twatCreatorDeleteMessage;
    private String roleAssignMessage;

    public int getTweetId() {
        return tweetId;
    }

    public void setTweetId(int tweetId) {
        this.tweetId = tweetId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserRoleName() {
        return userRoleName;
    }

    public void setUserRoleName(String userRoleName) {
        this.userRoleName = userRoleName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getTwatDeleteMessage() {
        return twatDeleteMessage;
    }

    public String getTwatCreatorDeleteMessage() {
        return twatCreatorDeleteMessage;
    }

    public String getRoleAssignMessage() {
        return roleAssignMessage;
    }

    // Get users (show roles)
    public List<User> getUsers() {
        return users;
    }

    public void init() {
        //users = userDao.findAll();
    }

    // Delete messages
    public void deleteTweet(int id) {
        //Tweet tweet = tweetDAO.findById(id);
       //tweetDAO.delete(tweet);

    }

    // Assign new role to user
    public void assignRole(String username, Role role) {
       /* System.out.println("alert");
        User user = userDao.findByUsername(username);
        user.setRole(role);
        userDao.update(user);*/
    }


}
