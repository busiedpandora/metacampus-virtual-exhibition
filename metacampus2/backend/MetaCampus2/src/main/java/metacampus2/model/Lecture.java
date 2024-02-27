package metacampus2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Lecture {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    @ManyToOne
    private Classroom classroom;
    private LocalDateTime localDateTime;
    @ManyToOne
    private Person lecturer;
}
