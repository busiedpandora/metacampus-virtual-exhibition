package metacampus2.service;

import metacampus2.AbstractTest;
import metacampus2.model.Audio;
import metacampus2.repository.IAudiosRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AudioServiceTest extends AbstractTest {

    @Mock
    private IAudiosRepository audiosRepository;
    private AudioService audioService;

    @BeforeEach
    public void setUp(){
        audioService = new AudioService(audiosRepository);
    }

    @Test
    void getAllAudios() {

        List<Audio> audioList = new ArrayList<>();

        Audio audio = new Audio();
        audio.setName("registrazione A");
        audioList.add(audio);
        audioList.add(new Audio());

        when(audiosRepository.findAll()).thenReturn(audioList);

        assertEquals(audio.getName(), audioService.getAllAudios().get(0).getName());
    }

    @Test
    @Disabled
    void getAudioByPath() {
/*
        Audio audio = new Audio();
        audio.setName("registrazione A");
        audio.setAudioPath(".." + File.separator + "audioA");

        when(audiosRepository.findByAudioPath(Mockito.anyString())).thenReturn(audio);

        assertEquals(audio.getAudioPath(), audiosService.getAudioByPath(audio.getAudioPath()).getAudioPath());

 */

    }
}