package metacampus2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AudioPanel extends Space {

    @ManyToOne
    @JoinColumn(name = "audio_id")
    private Audio audio;
}
