package metacampus2.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Classroom extends Resource {
    @Column(nullable = false)
    private String number;

    @OneToMany(mappedBy = "classroom")
    private List<Lecture> lectures;
}
