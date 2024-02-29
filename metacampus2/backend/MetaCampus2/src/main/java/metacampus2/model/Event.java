package metacampus2.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Event extends Resource {

    private String coordinates;

    private LocalDateTime dateTime;

    @OneToMany(mappedBy = "event")
    @JsonIgnoreProperties("event")
    private List<Classroom> locationEvent;

    @OneToOne
    @JoinColumn(name = "fk_metaverse")
    @JsonIgnoreProperties("eventMetaverse")
    private Metaverse metaverseEvent;
}
