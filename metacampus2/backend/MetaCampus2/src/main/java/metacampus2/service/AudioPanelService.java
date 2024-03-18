package metacampus2.service;

import metacampus2.model.AudioPanel;
import metacampus2.repository.IAudioPanelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AudioPanelService implements IAudioPanelService {

    private IAudioPanelRepository audioPanelRepository;

    @Autowired
    public AudioPanelService(IAudioPanelRepository audioPanelRepository) {
        this.audioPanelRepository = audioPanelRepository;
    }

    @Override
    public void addNewAudioPane(AudioPanel audioPanel) {
        audioPanelRepository.save(audioPanel);
    }

    @Override
    public List<AudioPanel> getAllAudioPanels() {
        return audioPanelRepository.findAll();
    }

    @Override
    public List<AudioPanel> getAllAudioPanelsFromMetaverse(String metaverse) {
        return audioPanelRepository.findAllByMetaverseName(metaverse);
    }
}
