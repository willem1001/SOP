package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Column(name="username",nullable = false,unique = true,length = 30)
    private String username;

    @Column(name = "password",nullable = false,length = 255)
    private String password;

    @Column(name = "location")
    private String location;

    @Column (name = "token")
    private String token;

    @Column(name = "website")
    private String website;

    @Column(name = "bio")
    private String bio;

    @Column(name = "avatar")
    private String avatar;

    @Column(name= "secretKey")
    private String secretKey;

    @Column(name="twoFactor")
    private boolean twoFactor;

    @Column(name="authCode")
    private int authCode;

    @ElementCollection
    private List<Long> followerIds = new ArrayList<Long>();

    @ElementCollection
    private List<Long> followingIds = new ArrayList<Long>();

    @ElementCollection
    private List<Long> tweetIds = new ArrayList<Long>();


    public void addTweet(Tweet tweet){
        tweetIds.add(tweet.getId());
    }

    public void removeTweet(Tweet tweet){
        tweetIds.remove(tweet.getId());
    }

    public void addFollowingUser(User user){
        long id = user.getId();
        if(!followingIds.contains(id)){
            followingIds.add(id);
        } else{
            followingIds.remove(id);
        }
    }

    public void addFollowerUser(User user){
        long id = user.getId();
        if(!followerIds.contains(id)){
            followerIds.add(id);
        } else{
            followerIds.remove(id);
        }
    }

    public User(){}

    public User(String token){
        this.token = token;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<Long> getFollowerIds() {
        return followerIds;
    }

    public void setFollowerIds(List<Long> followerIds) {
        this.followerIds = followerIds;
    }

    public List<Long> getTweetIds() {
        return tweetIds;
    }

    public void setTweetIds(List<Long> tweetIds) {
        this.tweetIds = tweetIds;
    }

    public List<Long> getFollowingIds() {
        return followingIds;
    }

    public void setFollowingIds(List<Long> followingIds) {

        this.followingIds = followingIds;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public int getAuthCode() {
        return authCode;
    }

    public void setAuthCode(int authCode) {
        this.authCode = authCode;
    }

    public boolean isTwoFactor() {
        return twoFactor;
    }

    public void setTwoFactor(boolean twoFactor) {
        this.twoFactor = twoFactor;
    }
}
