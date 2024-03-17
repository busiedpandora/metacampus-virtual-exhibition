package metacampus2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Audio extends Resource {
    private String audioPath;

    @OneToMany(mappedBy = "audio")
    List<AudioPanel> audioPanels;
}