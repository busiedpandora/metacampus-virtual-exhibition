package metacampus2.service;

import metacampus2.model.Audio;
import metacampus2.model.DisplayPanel;
import metacampus2.model.Image;
import metacampus2.repository.IAudioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AudioService extends AbstractService implements IAudioService {

    private IAudioRepository audioRepository;

    @Autowired
    public AudioService(IAudioRepository audioRepository) {
        this.audioRepository = audioRepository;
    }

    @Override
    public void addNewAudio(Audio audio) {
        audio.setLastEditDate(LocalDateTime.now());

        audioRepository.save(audio);
    }

    @Override
    public boolean createFile(Audio audio, MultipartFile audioFile, Image image, DisplayPanel displayPanel) {
        String audioName = audioFile.getOriginalFilename();
        String imageNameWithoutExtension = image.getFileName().substring(0, image.getFileName().lastIndexOf('.'));
        File imageDirectory = new File(METAVERSES_PATH + displayPanel.getMetaverse().getUrlName() +
                SEPARATOR + DISPLAY_PANELS_PATH + displayPanel.getUrlName() + SEPARATOR
                + IMAGES_PATH + imageNameWithoutExtension);

        if(!imageDirectory.exists()) {
            return false;
        }

        File audioDirectory = new File(imageDirectory.getPath() + SEPARATOR + AUDIO_PATH);

        if(!audioDirectory.exists()) {
            if(!audioDirectory.mkdirs()) {
                return false;
            }
        }

        Path audioPath = Path.of(audioDirectory.getPath() + SEPARATOR + audioName);

        try {
            Files.copy(audioFile.getInputStream(), audioPath, StandardCopyOption.REPLACE_EXISTING);

            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public List<Audio> getAllAudios() {
        return audioRepository.findAll();
    }

    @Override
    public void removeAudio(Audio audio) {
        audioRepository.delete(audio);
    }
}
