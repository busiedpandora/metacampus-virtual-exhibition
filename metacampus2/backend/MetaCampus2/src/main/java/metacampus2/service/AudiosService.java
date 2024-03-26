package metacampus2.service;

import metacampus2.model.Audio;
import metacampus2.repository.IAudiosRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AudiosService implements IAudioService {

    private IAudiosRepository audioRepository;

    @Autowired
    public AudiosService(IAudiosRepository audioRepository) {
        this.audioRepository = audioRepository;
    }

    @Override
    public void addNewAudio(Audio audio) {
        audioRepository.save(audio);
    }

    @Override
    public List<Audio> getAllAudios() {
        return audioRepository.findAll();
    }

    @Override
    public Audio getAudioByPath(String path) {
        return audioRepository.findByAudioPath(path);
    }
}
