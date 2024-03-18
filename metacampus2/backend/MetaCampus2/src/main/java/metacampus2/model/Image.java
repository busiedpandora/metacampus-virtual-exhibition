package metacampus2.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Image extends Resource {
    private String path;

    @OneToMany(mappedBy = "image")
    @JsonBackReference
    private List<DisplayPanel> displayPanels;

    @PrePersist
    public void prePersist() {
        if(displayPanels != null) {
            for (DisplayPanel panel : displayPanels) {
                panel.setImage(this);
            }
        }
    }
}
