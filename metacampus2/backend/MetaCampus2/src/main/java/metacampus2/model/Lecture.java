package metacampus2.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference
    private Classroom classroom;

    @ManyToOne
    @JoinColumn(name = "lecturer_id", nullable = false)
    @JsonManagedReference
    private Person lecturer;
}
