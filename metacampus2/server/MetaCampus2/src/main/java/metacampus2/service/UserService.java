package metacampus2.service;

import metacampus2.model.User;
import metacampus2.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User addUser(User user) {

        List<User> users = getAllUsers();

        if (!users.isEmpty()) {

            for (User user1 : users) {

                if (user.equals(user1))
                    return null;

                while (Objects.equals(user1.getNomeUtente(), user.getNomeUtente())) {
                    user.setNomeUtente(user.getNomeUtente() + generateCharater());
                }
            }
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public Optional<User> findUser(Long idUser) {
        return userRepository.findById(idUser);
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return userRepository.findAll().stream().filter((ut) -> Objects.equals(ut.getNomeUtente(), username)).findFirst();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public char generateCharater() {
        Random random = new Random();
        return (char) (random.nextInt(26) + 'a');
    }
}
