package metacampus2.initializer;

import metacampus2.model.User;
import metacampus2.model.UserRole;
import metacampus2.service.IUserService;
import metacampus2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
public class UsersInitializer implements CommandLineRunner, Ordered {
    private IUserService userService;

    @Autowired
    public UsersInitializer(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        if(userService.getUser(UserService.ADMIN1) == null) {
            User admin1 = new User();
            admin1.setFirstName("Simone");
            admin1.setLastName("Giamboni");
            admin1.setUsername(UserService.ADMIN1);
            admin1.setPassword("metacampus_admin1_2024");
            admin1.setRole(UserRole.ROLE_ADMIN);
            userService.addUser(admin1);
        }

        if(userService.getUser(UserService.ADMIN2) == null) {
            User admin2 = new User();
            admin2.setFirstName("Daniel");
            admin2.setLastName("Ibrahim");
            admin2.setUsername(UserService.ADMIN2);
            admin2.setPassword("metacampus_admin2_2024");
            admin2.setRole(UserRole.ROLE_ADMIN);
            userService.addUser(admin2);
        }
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
