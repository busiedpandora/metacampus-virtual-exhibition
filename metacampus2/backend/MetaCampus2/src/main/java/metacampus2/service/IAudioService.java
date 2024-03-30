package metacampus2.service;

import metacampus2.model.Audio;
import java.util.List;
public interface IAudioService {

    void addNewAudio(Audio audio);

    List<Audio> getAllAudios();
}
