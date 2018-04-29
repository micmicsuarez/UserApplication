package ph.com.masagana.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ph.com.masagana.exception.EntityException;
import ph.com.masagana.type.ApiError;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private UserRepository repository;

    @Autowired
    UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public User create(User user) throws EntityException {
        boolean emailAddressExists = emailAddressExists(user.getEmailAddress());

        if (emailAddressExists) {
            throw new EntityException(ApiError.EMAIL_ADDRESS_EXISTS.value());
        }

        boolean usernameExists = usernameExists(user.getUsername());
        if(usernameExists) {
            throw new EntityException(ApiError.USERNAME_EXISTS_ALREADY.value());
        }
        return repository.saveAndFlush(user);
    }

    @Transactional
    public User fetchById(UUID id) {
        Optional<User> user = repository.findById(id);
        return user != null ? user.get() : null;
    }

    @Transactional
    public User fetchByEmailAddress(String emailAddress) {
        return repository.findByEmailAddress(emailAddress);
    }

    @Transactional
    public User update(User user) {
        return repository.saveAndFlush(user);
    }

    @Transactional
    public Page<User> fetchAll(int pageNumber, int numberOfItems) {
        Pageable pageable = new QPageRequest(pageNumber, numberOfItems);

        return repository.findAll(pageable);
    }

    private boolean emailAddressExists(String emailAddress) {
        return repository.findByEmailAddress(emailAddress) != null;
    }

    private boolean usernameExists(String username) {
        return repository.findByUsername(username) != null;
    }
}
