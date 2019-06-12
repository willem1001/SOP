package controller;

import com.google.common.hash.Hashing;
import com.google.gson.Gson;
import model.Role;
import model.User;
import manager.UserManager;
import restdata.CreateUserRequest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/user")
@ApplicationScoped
public class UserController {


    private UserManager userManager;

    @POST
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    @Path("/login")
    public Response login(JsonObject jsonObject) throws GeneralSecurityException {
        User user = new User();
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        int authc = Integer.parseInt(jsonObject.getString("authCode"));
        if(authc != 0){
            user.setAuthCode(authc);
        }

        user.setUsername(username);
        user.setPassword(password);

        user.setToken(Hashing.sha512().hashString(username, StandardCharsets.UTF_8).toString());
        User u = userManager.login(user);
        u.setPassword("");
        if(u.isTwoFactor()){
            return Response.ok(u).build();
        }
        else if(u.isTwoFactor()){
            return Response.status(401).build();
        }
        return Response.ok(u).build();


    }

    @GET
    @Produces(APPLICATION_JSON)
    @Path("/followers/{id}")
    public List<User> getFollowers(@PathParam("id") long id) {
        return userManager.getFollowers(id);
    }

    @GET
    @Produces(APPLICATION_JSON)
    @Path("/following/{id}")
    public List<User> getFollowing(@PathParam("id") long id) {
        return userManager.getFollowers(id);
    }


    @GET
    @Produces(APPLICATION_JSON)
    public Response getAllUsers() {
        List<User> users = userManager.getAll();
        return Response.ok(users).build();
    }

    @POST
    @Path("/register")
    @Consumes(APPLICATION_JSON)
    public Response createUser(CreateUserRequest createUserRequest) {

        User user = new User();
        if (createUserRequest.getRole() != null) {
            user.setRole(createUserRequest.getRole());
        } else {
            user.setRole(Role.REGULAR);
        }

        user.setUsername(createUserRequest.getUsername());
        user.setPassword(createUserRequest.getPassword());
        user.setWebsite(createUserRequest.getWebsite());
        user.setLocation(createUserRequest.getLocation());
        user.setBio(createUserRequest.getBio());

        user.setAvatar("https://www.jbproductions.nl/images/artiest/new/im_m3_d600_1545_1.jpg");
        userManager.insert(user);
        return Response.ok(user).build();
    }

    @GET
    @Consumes(APPLICATION_JSON)
    @Path("{id}")
    public User getUserById(@PathParam("id") long id) {
        return userManager.getById(id);

    }

    @GET
    @Consumes(APPLICATION_JSON)
    @Path("name/{username}")
    public User getUserByUsername(@PathParam("username") String username) {
        return userManager.getByUsername(username);
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteUser(@PathParam("id") long id) {
        userManager.delete(userManager.getById(id));
        return Response.ok().build();
    }

    @GET
    @Consumes(APPLICATION_JSON)
    @Path("search/{searchTerm}")
    public Response search(@PathParam("searchTerm") String searchTerm) {
        List<Object> results = userManager.search(searchTerm);
        return Response.ok(results).build();
    }


    @PUT
    @Consumes(APPLICATION_JSON)
    @Path("/update/{id}")
    public Response updateUser(@PathParam("id") long id, JsonObject jsonObject, @HeaderParam("Authorization") String authKey, @HeaderParam("Username") String uname) {
        User u = new Gson().fromJson(jsonObject.toString(), User.class);
        User user = userManager.getById(id);

        if (1 == 1 || userManager.authorize(authKey, uname)) {
            if (u.getRole() != null) {
                user.setRole(u.getRole());
            }
            if (u.getAvatar() != null) {
                user.setAvatar(u.getAvatar());
            }
            if (u.getLocation() != null) {
                user.setLocation(u.getLocation());
            }
            if (u.getBio() != null) {
                user.setBio(u.getBio());
            }
            user.setTwoFactor(u.isTwoFactor());

            if (jsonObject.containsKey("followerIds")) {
                JsonArray jsonFollowers = jsonObject.getJsonArray("followerIds");
                List<Long> followers = new ArrayList<Long>();
                for (int i = 0; i < jsonFollowers.size(); i++) {
                    followers.add(Long.valueOf(jsonFollowers.getInt(i)));
                }
                user.setFollowerIds(followers);
            }

            if (jsonObject.containsKey("followingIds")) {
                List<Long> following = new ArrayList<Long>();
                JsonArray jsonFollowing = jsonObject.getJsonArray("followingIds");
                for (int i = 0; i < jsonFollowing.size(); i++) {
                    following.add(Long.valueOf(jsonFollowing.getInt(i)));
                }
                user.setFollowingIds(following);
            }
            if (jsonObject.containsKey("tweetIds")) {
                List<Long> tweets = new ArrayList<Long>();
                JsonArray jsonTweets = jsonObject.getJsonArray("tweetIds");
                for (int i = 0; i < jsonTweets.size(); i++) {
                    tweets.add(Long.valueOf(jsonTweets.getInt(i)));
                }
                user.setTweetIds(tweets);
            }

            userManager.update(user);
            return Response.ok(user).build();
        }

        return Response.status(401).build();
    }




}
