package metacampus2.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class DisplayPanel extends Space {
    @ManyToOne
    @JoinColumn(name = "image_id")
    @JsonManagedReference
    private Image image;
}
