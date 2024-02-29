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
public class Classroom extends Location {

    @ManyToMany(mappedBy = "classroomList")
    @JsonIgnoreProperties("classroomList")
    private List<Lecturer> lecturerList;

    @ManyToMany(mappedBy = "classroomStudent")
    @JsonIgnoreProperties("classroomStudent")
    private List<Student> studentList;

    @ManyToOne
    @JoinColumn(name = "fk_event")
    @JsonIgnoreProperties("locationEvent")
    private Event event;

    @OneToMany(mappedBy = "classroom")
    @JsonIgnoreProperties("classroom")
    private List<Lecture> lectureList;

    @JoinTable(name = "CLASSROOM_METAVERSE", joinColumns = @JoinColumn(name = "fk_metaverse"), inverseJoinColumns = @JoinColumn(name = "fk_classroom"))
    @ManyToMany
    @JsonIgnoreProperties("classrooms")
    private List<Metaverse> metaverseClassroom;

}
