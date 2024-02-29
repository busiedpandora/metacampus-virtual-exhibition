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

    private String name;

    @ManyToMany(mappedBy = "metaverseClassroom")
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
}
