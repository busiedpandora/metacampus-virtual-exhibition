package metacampus2.service;

import metacampus2.model.User;

import java.util.List;

public interface IUserService {
    User getUser(String username);
    void addUser(User user);
    void editUser(User user);
    List<User> getAllUsers();
    User getUserLogged();
}
