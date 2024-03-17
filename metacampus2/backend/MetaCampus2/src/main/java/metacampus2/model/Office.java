/*
package metacampus2.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Office extends Resource {

    @Column(nullable = false)
    private String number;

    @OneToMany(mappedBy = "office")
    @JsonBackReference
    private List<Person> people;
}
*/
