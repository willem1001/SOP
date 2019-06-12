package controller;

import manager.UserManager;
import model.PostType;
import model.Tweet;
import manager.TweetManager;
import model.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/tweet")
@ApplicationScoped
public class TweetController {

    @Inject
    private TweetManager tweetmanager;

    @Inject
    private UserManager userManager;

    @GET
    @Produces(APPLICATION_JSON)
    public List<Tweet> getAllTweets(){
        return tweetmanager.getAll();
    }

    @POST
    @Path("create")
    @Consumes(APPLICATION_JSON)
    public Response createTweet(JsonObject jsonObject,@HeaderParam("Authorization") String authKey,@HeaderParam("Username") String uname){
        if(userManager.authorize(authKey,uname)){
            Tweet tweet = new Tweet();
            tweet.setContent(jsonObject.getString("content"));
            tweet.setCreationDate(new Timestamp(System.currentTimeMillis()));
            if(jsonObject.containsKey("parentId")){
                tweet.setParentId(Long.valueOf(jsonObject.getInt("parentId")));
                tweet.setPostType(PostType.REACTION);
            }
            tweet.setPostType(PostType.TWEET);
            tweet.setUserId(jsonObject.getInt("userId"));
            tweetmanager.insert(tweet);
            User user  = userManager.getById(jsonObject.getInt("userId"));
            user.addTweet(tweet);
            userManager.update(user);
            return Response.ok(tweet).build();
        }
        else return Response.status(401).build();

    }

    @GET
    @Path("timeline/{id}")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response getTimeline(@PathParam("id")long id, @HeaderParam("Authorization") String authKey,@HeaderParam("Username") String uname){
        if(userManager.authorize(authKey,uname)){
             List<Object> tweets = tweetmanager.getTweetsTimeline(id);
             return Response.ok(tweets).build();
        }
        else return Response.status(401).build();
    }

    @GET
    @Consumes(APPLICATION_JSON)
    @Path("search/{searchTerm}")
    public Response search(@PathParam("searchTerm")String searchTerm){
        List<Object> results = tweetmanager.search(searchTerm);
        return Response.ok(results).build();
    }

    @GET
    @Consumes(APPLICATION_JSON)
    @Path("{id}")
    public Response getByTweetId(@PathParam("id")long id){
        Tweet tweet = tweetmanager.getById(id);
        return Response.ok(tweet).build();
    }

    @GET
    @Consumes(APPLICATION_JSON)
    @Path("/content/")
    public Response getTweetByContent(JsonObject jsonObject){
        if(jsonObject.containsKey("content")){
            List<Tweet> tweets = tweetmanager.getByContent(jsonObject.getString("content"));
            return Response.ok(tweets).build();
        }
        return Response.status(401).build();
    }

    @Transactional
    @DELETE
    @Path("/delete/{id}")
    public Response deleteTweet(@PathParam("id")long id, @HeaderParam("Authorization") String authKey,@HeaderParam("Username") String uname){
        if(userManager.authorize(authKey,uname)){
            tweetmanager.delete(tweetmanager.getById(id));
            return Response.ok().build();
        }
        return Response.status(401).build();

    }



    @PUT
    @Path("/update/{id}")
    public Response updateTweet(@PathParam("id")long id, JsonObject jsonObject, @HeaderParam("Authorization") String authKey,@HeaderParam("Username") String uname){
        if (userManager.authorize(authKey,uname)){
            Tweet tweet = tweetmanager.getById(id);
            if(jsonObject.containsKey("content")){
                tweet.setContent(jsonObject.getString("content"));
            }

            if(jsonObject.containsKey("likeIds")){
                List<Long> likeIds = new ArrayList<Long>();
                JsonArray jsonLikes = jsonObject.getJsonArray("likeIds");
                for(int i = 0; i<jsonLikes.size(); i++){
                    likeIds.add(Long.valueOf(jsonLikes.getInt(i)));
                }
                tweet.setLikeIds(likeIds);
            }

            if(jsonObject.containsKey("replyIds")){
                List<Long> replyIds = new ArrayList<Long>();
                JsonArray jsonReplies = jsonObject.getJsonArray("replyIds");
                for(int i = 0; i<jsonReplies.size();i++){
                    replyIds.add(Long.valueOf(jsonReplies.getInt(i)));
                }
                tweet.setReplyIds(replyIds);
            }


            tweetmanager.update(tweet);
            return Response.ok().build();
        } else {
            return Response.status(401).build();
        }

    }
}
