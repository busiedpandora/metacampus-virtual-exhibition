package metacampus2.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    @Enumerated(EnumType.STRING)
    private UserRole role;
}
