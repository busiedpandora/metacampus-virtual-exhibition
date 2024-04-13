package metacampus2.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@NoArgsConstructor
public abstract class Space {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String urlName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coordinates_id", referencedColumnName = "id", nullable = false)
    @JsonManagedReference
    private Coordinate coordinates;

    @ManyToOne
    @JoinColumn(name = "metaverse_id", nullable = false)
    @JsonManagedReference
    private Metaverse metaverse;


    @PrePersist
    private void setUrlName() {
        this.urlName = this.name
                .toLowerCase()
                .replaceAll("[*!?,.+/]+", "-")
                .replaceAll(" ", "-");
    }
}
