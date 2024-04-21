package metacampus2.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
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
    @ManyToMany(mappedBy = "images", fetch = FetchType.EAGER)
    @JsonBackReference
    private List<DisplayPanel> displayPanels;

    @OneToOne(mappedBy = "image", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private Audio audio;

    @Transient
    private List<Integer> imageIndexes;

    @PrePersist
    public void prePersist() {
        if(imageIndexes == null) {
            return;
        }

        if(displayPanels == null) {
            displayPanels = new ArrayList<>();
        }

        for(int i = 0; i < displayPanels.size(); i++) {
            DisplayPanel displayPanel = displayPanels.get(i);

            if(displayPanel.getImages() == null) {
                displayPanel.setImages(new ArrayList<>());
            }

            if(i < imageIndexes.size()) {
                int imageIndex = imageIndexes.get(i);

                if(imageIndex < displayPanel.getType().getCapacity()) {
                    if (imageIndex == displayPanel.getImages().size()) {
                        displayPanel.getImages().add(this);
                    } else {
                        displayPanel.getImages().set(imageIndex, this);
                    }
                }
            }
        }
    }
}
