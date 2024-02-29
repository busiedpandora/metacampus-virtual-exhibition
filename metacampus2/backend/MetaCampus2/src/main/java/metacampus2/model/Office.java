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
public class Office extends Location {

    @OneToMany(mappedBy = "office")
    @JsonIgnoreProperties("office")
    private List<Lecturer> teachers;

    @ManyToOne
    @JoinColumn(name = "fk_metaverse")
    @JsonIgnoreProperties("offices")
    private Metaverse metaverseOffice;

}
