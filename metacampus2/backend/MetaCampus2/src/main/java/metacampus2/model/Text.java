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
public class Text extends Resource {
    private String value;

    @OneToMany(mappedBy = "text")
    @JsonBackReference
    List<TextPanel> textPanels;

    @PrePersist
    public void prePersist() {
        if (textPanels != null) {
            for (TextPanel panel : textPanels) {
                panel.setText(this);
            }
        }
    }
}
