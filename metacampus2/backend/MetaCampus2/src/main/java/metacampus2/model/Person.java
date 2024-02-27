package metacampus2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;
    private String role;
    private String cellphone;
    private String officePhone;
    @ManyToOne
    private Office office;
}
