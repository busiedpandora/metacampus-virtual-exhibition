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
public class Lecturer extends Person {

    private String cellphone;
    private String officePhone;

    @ManyToOne
    @JoinColumn(name = "fk_office")
    @JsonIgnoreProperties("teachers")
    private Office office;

    @JoinTable(name = "LECTURER_CLASSROOM", joinColumns = @JoinColumn(name = "fk_classroom"), inverseJoinColumns = @JoinColumn(name = "fk_lecturer"))
    @ManyToMany
    @JsonIgnoreProperties("lecturerList")
    private List<Classroom> classroomList;

    @OneToMany(mappedBy = "lecturer")
    @JsonIgnoreProperties("lecturer")
    private List<Lecture> lectureClassroom;

    @JoinTable(name = "LECTURER_METAVERSE", joinColumns = @JoinColumn(name = "fk_metaverse"), inverseJoinColumns = @JoinColumn(name = "fk_lecturer"))
    @ManyToMany
    @JsonIgnoreProperties("lecturers")
    private List<Metaverse> metaverseLecturer;
}
