package metacampus2.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public abstract class Space {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String urlName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coordinates_id", referencedColumnName = "id", nullable = false)
    @JsonManagedReference
    private Coordinate coordinates;

    @ManyToOne
    @JoinColumn(name = "metaverse_id", nullable = false)
    @JsonManagedReference
    private Metaverse metaverse;

    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    private User creator;
}
