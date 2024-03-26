package metacampus2.service;

import metacampus2.AbstractTest;
import metacampus2.model.TextPanel;
import metacampus2.repository.ITextPanelRepository;
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
class TextPanelServiceTest extends AbstractTest {

    @Mock
    private ITextPanelRepository textPanelRepository;
    private TextPanelService textPanelService;

    @BeforeEach
    public void setUp(){
        textPanelService = new TextPanelService(textPanelRepository);
    }

    @Test
    void getAllTextPanels() {

        List<TextPanel> textPanelList = new ArrayList<>();

        TextPanel textPanel = new TextPanel();

        textPanel.setName("Leopardi - passero solitario");

        textPanelList.add(textPanel);
        textPanelList.add(new TextPanel());

        when(textPanelRepository.findAll()).thenReturn(textPanelList);

        assertEquals(textPanelList.size(), textPanelService.getAllTextPanels().size());

    }

    @Test
    void getAllTextPanelsFromMetaverse() {

        List<TextPanel> textPanelList = new ArrayList<>();

        TextPanel textPanel = new TextPanel();

        textPanel.setName("Leopardi - passero solitario");

        textPanelList.add(textPanel);
        textPanelList.add(new TextPanel());

        when(textPanelRepository.findAllByMetaverseName(Mockito.anyString())).thenReturn(textPanelList);

        assertEquals(textPanel.getName(), textPanelService.getAllTextPanelsFromMetaverse("USI").get(0).getName());

    }
}