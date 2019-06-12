package jsf;

import manager.UserManager;
import model.Role;
import model.User;

import java.io.Serializable;

public class LoginBacking implements Serializable {


    Object userDAO;

    Object tweetDAO;

    private String username;
    private String password;

    private String errorMessage;

    private User loggedInUser;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    private void login() {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
    }

    private boolean isLoggedIn() {
        return loggedInUser != null;
    }

    private boolean isModerator() {
        return loggedInUser.getRole().equals(Role.MODERATOR);
    }

    public String redirectLogin() {
        String url = "index";
        String urlArgs = "?faces-redirect=true";

        login();

        if(!isLoggedIn()) {
            errorMessage = "Couldn't find a user with given username and password.";
            return url + urlArgs;
        }

        if(isModerator()) {
            url = "login";
        } else {
            errorMessage = "User is not authorized to access this page.";
        }

        return url + urlArgs;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}