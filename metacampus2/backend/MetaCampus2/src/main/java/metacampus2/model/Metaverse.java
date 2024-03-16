package metacampus2.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Metaverse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    /*@OneToMany(mappedBy = "metaverse")
    @JsonBackReference
    private List<Classroom> classrooms;

    @OneToMany(mappedBy = "metaverse")
    @JsonIgnoreProperties("metaverse")
    private List<Event> events;

    @OneToMany(mappedBy = "metaverse")
    @JsonIgnoreProperties("metaverse")
    private List<Lecture> lectures;

    @OneToMany(mappedBy = "metaverse")
    @JsonIgnoreProperties("metaverse")
    private List<Person> people;

    @OneToMany(mappedBy = "metaverse")
    @JsonIgnoreProperties("metaverse")
    private List<Office> offices;*/

    @OneToMany(mappedBy = "metaverse")
    private List<Space> spaces;
}
