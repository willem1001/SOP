package model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class Tweet {

    private long Id;

    private long parentId;

    private String content;

    private Timestamp creationDate;

    //@ManyToOne(cascade = CascadeType.REFRESH)
    //private User user;

    private long userId;

    private List<Long> replyIds = new ArrayList<Long>();

    private List<Long> likeIds = new ArrayList<Long>();

    private PostType postType;

    public void like(User user){
        long id = user.getId();
        if(!likeIds.contains(id)){
            likeIds.add(id);
        } else {
            likeIds.remove(id);
        }
    }

    public void reply(String content, User user, long parentId){
        Tweet tweet = new Tweet();
        tweet.setContent(content);
        tweet.setUserId(user.getId());
        tweet.setParentId(parentId);
        replyIds.add(tweet.getId());

    }

    public Tweet(){}


    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    /*public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }*/

    public List<Long> getReplyIds() {
        return replyIds;
    }

    public void setReplyIds(List<Long> replyIds) {
        this.replyIds = replyIds;
    }

    public List<Long> getLikeIds() {
        return likeIds;
    }

    public void setLikeIds(List<Long> likeIds) {
        this.likeIds = likeIds;
    }

    public PostType getPostType() {
        return postType;
    }

    public void setPostType(PostType postType) {
        this.postType = postType;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
