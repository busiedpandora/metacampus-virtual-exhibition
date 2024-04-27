package metacampus2.service;

import metacampus2.AbstractTest;
import metacampus2.model.Text;
import metacampus2.repository.ITextRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TextServiceTest extends AbstractTest {
    @Mock
    private ITextRepository textRepository;
    private TextService textService;


    @BeforeEach
    public void setUp() {
        textService = new TextService(textRepository);
    }

    @Test
    void addNewText() {
        textService.addNewText(text);

        verify(textRepository, times(1)).save(text);
    }

    @Test
    void editText() {
        textService.editText(text);

        verify(textRepository, times(1)).save(text);

        assertNotNull(text.getLastEditDate());
    }

    @Test
    void getAllTexts() {
        when(textRepository.findAll()).thenReturn(List.of(text));

        List<Text> texts = textService.getAllTexts();

        verify(textRepository, times(1)).findAll();

        assertEquals(text, texts.get(0));
    }

    @Test
    void getTextByTitle() {
        when(textRepository.findByTitle(text.getTitle())).thenReturn(text);

        Text t = textService.getTextByTitle(text.getTitle());

        verify(textRepository, times(1)).findByTitle(text.getTitle());

        assertEquals(text, t);
    }

    @Test
    void getTextByFileName() {
        when(textRepository.findByFileName(text.getFileName())).thenReturn(text);

        Text t = textService.getTextByFileName(text.getFileName());

        verify(textRepository, times(1)).findByFileName(text.getFileName());

        assertEquals(text, t);
    }

    @Test
    void getTextById() {
        when(textRepository.findById(text.getId())).thenReturn(Optional.of(text));

        Text t = textService.getTextById(text.getId());

        verify(textRepository, times(1)).findById(text.getId());

        assertEquals(text, t);
    }
}