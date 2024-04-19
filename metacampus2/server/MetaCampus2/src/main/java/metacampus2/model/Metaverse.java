package metacampus2.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @OneToMany(mappedBy = "metaverse")
    @JsonBackReference
    private List<Space> spaces;

    @ManyToMany
    @JoinTable(name = "metaverses_users",
            joinColumns = @JoinColumn(name = "metaverse_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @JsonManagedReference
    private List<User> users;
}
