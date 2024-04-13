package metacampus2.service;

import metacampus2.AbstractTest;
import metacampus2.model.Text;
import metacampus2.repository.ITextRepository;
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
class TextServiceTest extends AbstractTest {

    @Mock
    private ITextRepository textRepository;
    private TextService textService;

    @BeforeEach
    public void setUp() {
        textService = new TextService(textRepository);
    }

    @Test
    void getAllTexts() {

        List<Text> textList = new ArrayList<>();

        Text text = new Text();
        text.setName("TEXT 1");

        textList.add(new Text());
        textList.add(text);

        when(textRepository.findAll()).thenReturn(textList);

        assertEquals(text.getName(), textService.getAllTexts().get(1).getName());
    }

    @Test
    void getTextbyName() {

        Text text = new Text();
        text.setName("TEXT 1");

        when(textRepository.findByName(Mockito.anyString())).thenReturn(text);

        assertEquals(text, textService.getTextbyName(text.getName()));

    }
}