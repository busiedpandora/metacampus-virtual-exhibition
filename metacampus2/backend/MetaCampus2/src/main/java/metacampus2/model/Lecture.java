package metacampus2.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Lecture extends Resource {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "classroom_id", nullable = false)
    private Classroom classroom;

    @ManyToOne
    @JoinColumn(name = "lecturer_id", nullable = false)
    private Person lecturer;



    /*
    @ManyToOne
    @JoinColumn(name = "fk_classroom")
    @JsonIgnoreProperties("lectureList")
    private Classroom classroom;

    private LocalDateTime localDateTime;

    @ManyToOne
    @JoinColumn(name = "fk_lecturer")
    @JsonIgnoreProperties("lectureClassroom")
    private Lecturer lecturer;


    @ManyToOne
    @JoinColumn(name = "fk_metaverse")
    @JsonIgnoreProperties("eventMetaverse")
    private Metaverse metaverseLecture;
     */
}
