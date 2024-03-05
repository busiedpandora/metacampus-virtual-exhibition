package metacampus2.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference
    private List<Person> people;
}
