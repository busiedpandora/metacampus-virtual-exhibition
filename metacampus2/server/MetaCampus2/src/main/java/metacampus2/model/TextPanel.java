package metacampus2.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TextPanel extends Space {

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "text_id")
    @JsonManagedReference
    private Text text;


    @PreRemove
    private void preRemove() {
        if(text != null) {
            if (text.getTextPanels().size() > 1) {
                text.getTextPanels().remove(this);
                text = null;
            }
        }
    }
}
