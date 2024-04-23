package metacampus2.service;

import metacampus2.model.Audio;
import metacampus2.model.DisplayPanel;
import metacampus2.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
public interface IAudioService {
    void addNewAudio(Audio audio);
    void editAudio(Audio audio);
    boolean createFile(Audio audio, MultipartFile audioFile, Image image, DisplayPanel displayPanel);
    boolean renameFile(String oldName, Audio audio, Image image, DisplayPanel displayPanel);
    List<Audio> getAllAudios();
    void removeAudio(Audio audio);
    Audio getAudioByTitle(String title);
    Audio getAudioById(Long id);
}
