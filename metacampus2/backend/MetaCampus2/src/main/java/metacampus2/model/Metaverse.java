package metacampus2.model;

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

    @OneToMany(mappedBy = "metaverse")
    private List<Classroom> classrooms;

    @OneToMany(mappedBy = "metaverse")
    private List<Event> events;

    @OneToMany(mappedBy = "metaverse")
    private List<Lecture> lectures;

    @OneToMany(mappedBy = "metaverse")
    private List<Person> people;

    @OneToMany(mappedBy = "metaverse")
    private List<Office> offices;


    /*@ManyToMany(mappedBy = "metaverseClassroom")
    @JsonIgnoreProperties("metaverseClassroom")
    private List<Classroom> classrooms;

    @OneToOne(mappedBy = "metaverseEvent")
    @JsonIgnoreProperties("metaverseEvent")
    private Event eventMetaverse;

    @OneToMany(mappedBy = "metaverseLecture")
    @JsonIgnoreProperties("metaverseLecture")
    private List<Lecture> lectures;

    @ManyToMany(mappedBy = "metaverseLecturer")
    @JsonIgnoreProperties("metaverseLecturer")
    private List<Lecturer> lecturers;

    @OneToMany(mappedBy = "metaverseOffice")
    @JsonIgnoreProperties("metaverseOffice")
    private List<Office> offices;

    @ManyToMany(mappedBy = "metaverseStudent")
    @JsonIgnoreProperties("metaverseStudent")
    private List<Student> students;
     */
}
