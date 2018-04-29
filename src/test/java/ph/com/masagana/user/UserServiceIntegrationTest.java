package ph.com.masagana.user;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ph.com.masagana.UserConfig;

import javax.validation.ConstraintViolationException;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {UserConfig.class})
@Sql("UserTestData.sql")
@Transactional
public class UserServiceIntegrationTest {

    @Autowired
    private UserService userService;

    @Test
    public void create_givenAValidRequest_shouldCreateUser() throws Exception {
        User user = new User();
        user.setFirstName("Michael");
        user.setUsername("michael");

        User savedUser = userService.create(user);

        Assert.assertNotNull(savedUser);
    }

    @Test
    public void update_givenAValidRequest_shouldUpdateTheUserInfo() throws Exception {
        User user = userService.fetchByEmailAddress("sample@emailaddress.com");
        user.setUsername("micmic");

        User updatedUser = userService.update(user);

        Assert.assertEquals("micmic", updatedUser.getUsername());
    }

    @Test(expected = ConstraintViolationException.class)
    public void update_givenARequestWithInvalidEmailAddress_shouldThrowConstraintViolationException() throws Exception {
        User user = userService.fetchByEmailAddress("sample@emailaddress.com");
        user.setEmailAddress("michael@");

        userService.update(user);
    }
}
