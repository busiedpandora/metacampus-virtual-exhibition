package metacampus2.service;

import metacampus2.AbstractTest;
import metacampus2.model.TextPanel;
import metacampus2.repository.ITextPanelRepository;
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
class TextPanelServiceTest extends AbstractTest {
    @Mock
    private ITextPanelRepository textPanelRepository;
    private TextPanelService textPanelService;


    @BeforeEach
    public void setUp(){
        textPanelService = new TextPanelService(textPanelRepository);
    }

    @Test
    void addNewTextPanel() {
        textPanelService.addNewTextPanel(textPanel);

        verify(textPanelRepository, times(1)).save(textPanel);
    }

    @Test
    void deleteTextPanel() {
        textPanelService.deleteTextPanel(textPanel);

        verify(textPanelRepository, times(1)).delete(textPanel);
    }

    @Test
    void getAllTextPanels() {
        when(textPanelRepository.findAll()).thenReturn(List.of(textPanel));

        List<TextPanel> textPanels = textPanelService.getAllTextPanels();

        verify(textPanelRepository, times(1)).findAll();

        assertEquals(textPanel, textPanels.get(0));
    }

    @Test
    void getAllTextPanelsFromMetaverse() {
        when(textPanelRepository.findAllByMetaverseName(metaverse.getName())).thenReturn(List.of(textPanel));

        List<TextPanel> textPanels = textPanelService.getAllTextPanelsFromMetaverse(metaverse.getName());

        verify(textPanelRepository, times(1)).findAllByMetaverseName(metaverse.getName());

        assertEquals(textPanel, textPanels.get(0));
    }

    @Test
    void getAllTextPanelsFromMetaverseByUrlName() {
        when(textPanelRepository.findAllByMetaverseUrlName(metaverse.getUrlName())).thenReturn(List.of(textPanel));

        List<TextPanel> textPanels = textPanelService.getAllTextPanelsFromMetaverseByUrlName(metaverse.getUrlName());

        verify(textPanelRepository, times(1)).findAllByMetaverseUrlName(metaverse.getUrlName());

        assertEquals(textPanel, textPanels.get(0));
    }

    @Test
    void getTextPanelById() {
        when(textPanelRepository.findById(textPanel.getId())).thenReturn(Optional.of(textPanel));

        TextPanel tp = textPanelService.getTextPanelById(textPanel.getId());

        verify(textPanelRepository, times(1)).findById(textPanel.getId());

        assertEquals(textPanel, tp);
    }
}