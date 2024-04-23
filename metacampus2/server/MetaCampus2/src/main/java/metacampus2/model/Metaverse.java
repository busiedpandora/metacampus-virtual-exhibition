package metacampus2.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Metaverse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String urlName;

    @Column(nullable = false)
    private int minXDimension;

    @Column(nullable = false)
    private int maxXDimension;

    @Column(nullable = false)
    private int minYDimension;

    @Column(nullable = false)
    private int maxYDimension;

    @Column(nullable = false)
    private int minZDimension;

    @Column(nullable = false)
    private int maxZDimension;

    @OneToMany(mappedBy = "metaverse", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Space> spaces;

    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    private User creator;
}
