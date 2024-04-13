package metacampus2.service;

import metacampus2.AbstractTest;
import metacampus2.model.AudioPanel;
import metacampus2.repository.IAudioPanelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AudioPanelServiceTest extends AbstractTest {

    @Mock
    private IAudioPanelRepository audioPanelRepository;
    private AudioPanelService audioPanelService;

    @BeforeEach
    public void setUp(){
        audioPanelService = new AudioPanelService(audioPanelRepository);
    }

    @Test
    void getAllAudioPanels() {

        List<AudioPanel> audioPanelList = new ArrayList<>();

        AudioPanel audioPanel = new AudioPanel();

        audioPanel.setName("Picasso");

        audioPanelList.add(audioPanel);
        audioPanelList.add(new AudioPanel());

        when(audioPanelRepository.findAll()).thenReturn(audioPanelList);

        assertEquals(audioPanelList.size(), audioPanelService.getAllAudioPanels().size());

    }

    @Test
    void getAllAudioPanelsFromMetaverse() {

        List<AudioPanel> audioPanelList = new ArrayList<>();

        AudioPanel audioPanel = new AudioPanel();

        audioPanel.setName("Picasso");

        audioPanelList.add(audioPanel);
        audioPanelList.add(new AudioPanel());

        when(audioPanelRepository.findAllByMetaverseName(Mockito.anyString())).thenReturn(audioPanelList);

        assertEquals(audioPanel.getName(), audioPanelService.getAllAudioPanelsFromMetaverse("USI").get(0).getName());
    }
}