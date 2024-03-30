package metacampus2.model;


import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Audio extends Resource {
    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;

    @OneToMany(mappedBy = "audio")
    List<AudioPanel> audioPanels;

    @PrePersist
    public void prePersist() {
        if (audioPanels != null) {
            for (AudioPanel panel : audioPanels) {
                panel.setAudio(this);
            }
        }
    }
}