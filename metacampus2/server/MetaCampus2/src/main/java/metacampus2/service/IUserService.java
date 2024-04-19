package metacampus2.service;

import metacampus2.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    User addUser(User user);

    Optional<User> findUser(Long idUser);

    Optional<User> findUserByUsername(String username);

    List<User> getAllUsers();

    char generateCharater();
}
