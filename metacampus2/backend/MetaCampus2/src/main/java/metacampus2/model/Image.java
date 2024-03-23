package metacampus2.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Image extends Resource {
    private String path;

    @ManyToMany(mappedBy = "images")
    @JsonBackReference
    private List<DisplayPanel> displayPanels;

    @PrePersist
    public void prePersist() {
        if(displayPanels != null) {
            for (DisplayPanel panel : displayPanels) {
                if(panel.getImages() == null) {
                    panel.setImages(new ArrayList<>());
                }
                if(panel.isFull()) {
                    panel.getImages().add(0, this);
                    panel.getImages().remove(panel.getImages().size() - 1);
                }
                else {
                    panel.getImages().add(this);
                }
            }
        }
    }
}
