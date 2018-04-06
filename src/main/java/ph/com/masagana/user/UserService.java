package ph.com.masagana.user;

import org.springframework.data.domain.Page;
import ph.com.masagana.exception.EntityException;

import java.util.UUID;

public interface UserService {

    User create(User user) throws EntityException;

    User fetchById(UUID id);

    User fetchByEmailAddress(String emailAddress);

    User update(User user);

    Page<User> fetchAll(int pageNumber, int numberOfItems);
}
