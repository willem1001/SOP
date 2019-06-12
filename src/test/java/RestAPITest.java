import com.google.gson.Gson;
import model.Role;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import restdata.CreateUserRequest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class RestAPITest {

    private HttpClientBuilder httpClientBuilder;
    private Gson gson;

    private HttpPut createPutRequest(String url, String json) throws UnsupportedEncodingException {
        HttpPut put = new HttpPut(url);
        put.addHeader("Content-Type","application/json");
        put.addHeader("Accept", "application/json");
        StringEntity entity = new StringEntity(json);
        put.setEntity(entity);
        return put;
    }

    private HttpDelete createDeleteRequest(String url){
        HttpDelete delete = new HttpDelete(url);
        delete.addHeader("Content-Type","application/json");
        return delete;
    }

    private HttpGet createGetRequest(String url){
        HttpGet get = new HttpGet(url);
        get.addHeader("Content-Type","application/json");
        return get;
    }

    private HttpPost createPostRequest(String url, String json) throws UnsupportedEncodingException {
        HttpPost post = new HttpPost(url);
        post.addHeader("Content-Type", "application/json");
        post.setHeader("Accept", "application/json");
        StringEntity entity = new StringEntity(json);
        post.setEntity(entity);
        return post;

    }

    private HttpResponse executeRequest(HttpUriRequest request) throws IOException {
        return httpClientBuilder.build().execute(request);
    }

    private String getJsonFromResponse(HttpResponse response) throws IOException {
        return EntityUtils.toString(response.getEntity());
    }

    @Before
    public void init(){
        gson = new Gson();
        httpClientBuilder = HttpClientBuilder.create();
    }

    @Test
    public void testUserAPI() throws IOException {
        //Test creation of user via RESTApi
        CreateUserRequest user = new CreateUserRequest();
        user.setUsername("username12123");
        user.setPassword("password");
        user.setRole(Role.REGULAR);
        HttpPost post = createPostRequest("http://localhost:8080/Kwetter_war/user/create",gson.toJson(user));
        HttpResponse httpResponse = executeRequest(post);

        //Expect 200 'ok' response from http
        Assert.assertEquals(200,httpResponse.getStatusLine().getStatusCode());

        String json = getJsonFromResponse(httpResponse);
        CreateUserRequest testUser = gson.fromJson(json,CreateUserRequest.class);


        //Test if username equals username from testuser
        Assert.assertEquals(user.getUsername(),testUser.getUsername());

        //Test update of user via RESTApi
        user.setAvatar("avatar");
        user.setBio("bio");
        HttpPut put = createPutRequest("http://localhost:8080/Kwetter_war/user/update/" + testUser.getId(),gson.toJson(user));
        HttpResponse putResponse = executeRequest(put);
        String json2 = getJsonFromResponse(putResponse);
        CreateUserRequest testUser2 = gson.fromJson(json2,CreateUserRequest.class);

        //Expect 200 'ok' response from http
        Assert.assertEquals(200,putResponse.getStatusLine().getStatusCode());
        //Test if updated values are actually updated
        Assert.assertEquals(user.getBio(),testUser2.getBio());
        Assert.assertEquals(user.getAvatar(),testUser2.getAvatar());

        //Test delete of user via RestApi
        HttpDelete delete = createDeleteRequest("http://localhost:8080/Kwetter_war/user/delete/"+testUser2.getId());
        executeRequest(delete);



    }


}
