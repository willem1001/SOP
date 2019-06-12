import dao.UserDAO;
import manager.UserManager;
import model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class UserManagerTest {

    @InjectMocks
    private UserManager userManager;

    @Mock
    UserDAO userDAO;

    private User user;


    @Test
    public void testCreateUser(){
        user = new User();
        user.setUsername("username");
        user.setPassword("password");
        userManager.insert(user);
        Mockito.verify(userDAO,Mockito.times(1)).create(user);
    }

    @Test
    public void testGetUser(){
        user = new User();
        user.setUsername("username");
        user.setPassword("password");
        userManager.getByUsername(user.getUsername());
        Mockito.verify(userDAO,Mockito.times(1)).findByUsername(user.getUsername());

        userManager.getById(user.getId());
        Mockito.verify(userDAO,Mockito.times(1)).findById(user.getId());
    }

    @Test
    public void testUpdateUser(){
        user = new User();
        user.setUsername("username");
        user.setPassword("password");
        user.setBio("bio");
        userManager.update(user);
        Mockito.verify(userDAO,Mockito.times(1)).update(user);
    }
}
