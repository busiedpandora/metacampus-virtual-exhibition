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
    @ManyToMany
    @JoinTable(name = "displayPanels_images",
                joinColumns = @JoinColumn(name = "displayPanel_id"),
                inverseJoinColumns = @JoinColumn(name = "image_id"))
    @JsonManagedReference
    private List<Image> images;

    @Enumerated(EnumType.STRING)
    private DisplayPanelType type;
}
