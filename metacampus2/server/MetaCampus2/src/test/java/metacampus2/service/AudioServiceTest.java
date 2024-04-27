package metacampus2.service;

import metacampus2.AbstractTest;
import metacampus2.model.Audio;
import metacampus2.repository.IAudioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AudioServiceTest extends AbstractTest {
    @Mock
    private IAudioRepository audioRepository;
    private AudioService audioService;


    @BeforeEach
    public void setUp(){
        audioService = new AudioService(audioRepository);
    }

    @Test
    void addNewAudio() {
        audioService.addNewAudio(audio);

        verify(audioRepository, times(1)).save(audio);
    }

    @Test
    void editAudio() {
        audioService.addNewAudio(audio);

        verify(audioRepository, times(1)).save(audio);

        assertNotNull(audio.getLastEditDate());
    }

    @Test
    void getAllAudios() {
        when(audioRepository.findAll()).thenReturn(List.of(audio));

        List<Audio> audios = audioService.getAllAudios();

        verify(audioRepository, times(1)).findAll();

        assertEquals(audio, audios.get(0));
    }

    @Test
    void removeAudio() {
        audioService.removeAudio(audio);

        verify(audioRepository, times(1)).delete(audio);
    }

    @Test
    void getAudioByTitle() {
        when(audioRepository.findByTitle(audio.getTitle())).thenReturn(audio);

        Audio a = audioService.getAudioByTitle(audio.getTitle());

        verify(audioRepository, times(1)).findByTitle(audio.getTitle());

        assertEquals(audio, a);
    }

    @Test
    void getAudioById() {
        when(audioRepository.findById(audio.getId())).thenReturn(Optional.of(audio));

        Audio a = audioService.getAudioById(audio.getId());

        verify(audioRepository, times(1)).findById(audio.getId());

        assertEquals(audio, a);
    }
}