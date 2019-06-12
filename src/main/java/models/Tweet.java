package models;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Tweet {
    private String title;
    private String content;
    private Timestamp creationDate;
    private User creator;
    private List<User> likes = new ArrayList<User>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public void addLike(User u){
        if(likes.contains(u)){
            likes.remove(u);
        } else {
            likes.add(u);
        }
    }

    public List<User> getLikes(){
        return likes;
    }

}
