package model;

import java.util.ArrayList;
import java.util.List;


public class User {

    
    private long id;

    private Role role;

    private String username;

    private String password;

    private String location;

    private String token;

    private String website;
    private String bio;

    private String avatar;

    private String secretKey;
    private boolean twoFactor;

    private int authCode;

    private List<Long> followerIds = new ArrayList<Long>();

    private List<Long> followingIds = new ArrayList<Long>();

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
