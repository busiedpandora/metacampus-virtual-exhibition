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
        audio.setCreationDate(LocalDateTime.now());

        audioRepository.save(audio);
    }

    @Override
    public void editAudio(Audio audio) {
        audio.setLastEditDate(LocalDateTime.now());

        audioRepository.save(audio);
    }

    @Override
    public boolean createFile(Audio audio, MultipartFile audioFile, Image image, DisplayPanel displayPanel) {
        String audioName = audioFile.getOriginalFilename();
        File imageDirectory = new File(METAVERSES_PATH + displayPanel.getMetaverse().getUrlName() +
                SEPARATOR + DISPLAY_PANELS_PATH + displayPanel.getUrlName() + SEPARATOR
                + IMAGES_PATH + image.getTitle());

        if(!imageDirectory.exists()) {
            return false;
        }

        File audioDirectory = new File(imageDirectory.getPath() + SEPARATOR + AUDIO_PATH + audio.getTitle());

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
    public boolean renameFile(String oldName, Audio audio, Image image, DisplayPanel displayPanel) {
        File audioDirectory = new File(METAVERSES_PATH
                + displayPanel.getMetaverse().getUrlName() + SEPARATOR
                + DISPLAY_PANELS_PATH + displayPanel.getUrlName() + SEPARATOR + IMAGES_PATH + image.getTitle()
                + SEPARATOR + AUDIO_PATH + oldName);

        File audioRenamedDirectory = new File(METAVERSES_PATH
                + displayPanel.getMetaverse().getUrlName() + SEPARATOR
                + DISPLAY_PANELS_PATH + displayPanel.getUrlName() + SEPARATOR + IMAGES_PATH + image.getTitle()
                + SEPARATOR + AUDIO_PATH + audio.getTitle());

        return audioDirectory.renameTo(audioRenamedDirectory);
    }

    @Override
    public List<Audio> getAllAudios() {
        return audioRepository.findAll();
    }

    @Override
    public void removeAudio(Audio audio) {
        audioRepository.delete(audio);
    }

    @Override
    public Audio getAudioByTitle(String title) {
        return audioRepository.findByTitle(title);
    }

    @Override
    public Audio getAudioById(Long id) {
        return audioRepository.findById(id).orElse(null);
    }
}
