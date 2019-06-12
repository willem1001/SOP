package models;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String firstName;
    private String lastName;
    private String username;
    private String emailAddress;
    private String password;
    private List<User> followers = new ArrayList<User>();
    private List<Tweet> tweets = new ArrayList<Tweet>();

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addFollower(User u){
        if(followers.contains(u)){
            followers.remove(u);
        } else {
            followers.add(u);
        }
    }

    public void addTweet(Tweet t){
        tweets.add(t);
    }

    public List<User> getFollowers() {
        return followers;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }
}
