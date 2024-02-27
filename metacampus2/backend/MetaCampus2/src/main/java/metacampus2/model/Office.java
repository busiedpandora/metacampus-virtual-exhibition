package metacampus2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Office {
    @Id
    @GeneratedValue
    private Long id;

    private String coordinates;
    private String number;
    @OneToMany
    private List<Person> persons;
}
