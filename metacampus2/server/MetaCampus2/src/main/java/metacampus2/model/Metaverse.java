package metacampus2.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @OneToMany(mappedBy = "metaverse")
    @JsonBackReference
    private List<Space> spaces;
}
