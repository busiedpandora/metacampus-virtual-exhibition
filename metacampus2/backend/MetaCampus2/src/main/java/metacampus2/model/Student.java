package metacampus2.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Student extends Person {

    private String cardNumber;

    @JoinTable(name = "STUDENT_CLASSROOM", joinColumns = @JoinColumn(name = "fk_classroom"), inverseJoinColumns = @JoinColumn(name = "fk_student"))
    @ManyToMany
    @JsonIgnoreProperties("studentList")
    private List<Classroom> classroomStudent;


    @JoinTable(name = "STUDENT_METAVERSE", joinColumns = @JoinColumn(name = "fk_metaverse"), inverseJoinColumns = @JoinColumn(name = "fk_student"))
    @ManyToMany
    @JsonIgnoreProperties("students")
    private List<Metaverse> metaverseStudent;

}
