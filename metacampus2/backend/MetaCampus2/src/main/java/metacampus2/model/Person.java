package metacampus2.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class Person extends Resource {

    private String firstName;
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Role role;

}
