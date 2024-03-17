package metacampus2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Image extends Resource {
    private String imagePath;

    @OneToMany(mappedBy = "image")
    private List<DisplayPanel> displayPanels;
}
