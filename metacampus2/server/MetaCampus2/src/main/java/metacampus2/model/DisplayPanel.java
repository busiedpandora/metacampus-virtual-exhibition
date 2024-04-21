package metacampus2.model;

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
public class DisplayPanel extends Space {
    @ManyToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinTable(name = "displayPanels_images",
                joinColumns = @JoinColumn(name = "displayPanel_id"),
                inverseJoinColumns = @JoinColumn(name = "image_id"))
    @JsonManagedReference
    private List<Image> images;

    @Enumerated(EnumType.STRING)
    private DisplayPanelType type;


    @PreRemove
    private void preRemove() {
        if(images != null) {
            for(Image image : images) {
                if(image.getDisplayPanels().size() > 1) {
                    image.getDisplayPanels().remove(this);
                    images = null;
                }
            }
        }
    }
}
