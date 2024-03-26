package metacampus2.service;

import metacampus2.model.AudioPanel;

import java.util.List;

public interface IAudioPanelService {

    void addNewAudioPane(AudioPanel audioPanel);

    List<AudioPanel> getAllAudioPanels();

    List<AudioPanel> getAllAudioPanelsFromMetaverse(String metaverse);
}
