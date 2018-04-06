package ph.com.masagana.user;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ph.com.masagana.exception.EntityException;
import ph.com.masagana.type.Status;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @Before
    public void setUp() {
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    public void create_givenAValidRequest_shouldCreateUser() throws Exception {
        User user = new User();

        when(userRepository.save(any(User.class))).thenReturn(user);

        User savedUser = userService.create(user);

        Assert.assertNotNull(savedUser);
    }

    @Test
    public void create_givenAValidRequest_shouldSetTheInitialStatusOfUserToUnverified() throws Exception {
        User user = new User();

        when(userRepository.save(any(User.class))).thenReturn(user);

        User savedUser = userService.create(user);

        Assert.assertEquals(Status.UNVERIFIED, savedUser.getStatus());
    }

    @Test(expected = EntityException.class)
    public void create_givenARequestAndEmailAddressIsAlreadyTaken_shouldThrowAnEntityException() throws Exception {
        User user = new User();
        user.setEmailAddress("takenalready@gmail.com");

        when(userRepository.findByEmailAddress("takenalready@gmail.com")).thenReturn(new User());

        userService.create(user);
    }

    @Test(expected = EntityException.class)
    public void create_givenARequestAndUsernameIsAlreadyExists_shouldThrowAnEntityException() throws Exception {
        User user = new User();
        user.setUsername("micmic");

        when(userRepository.findByUsername("micmic")).thenReturn(new User());

        userService.create(user);
    }
}
