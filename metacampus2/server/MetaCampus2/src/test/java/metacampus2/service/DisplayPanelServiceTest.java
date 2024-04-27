package metacampus2.service;

import metacampus2.AbstractTest;
import metacampus2.model.DisplayPanel;
import metacampus2.repository.IDisplayPanelRepository;
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
class DisplayPanelServiceTest  extends AbstractTest {
    @Mock
    private IDisplayPanelRepository displayPanelRepository;
    private DisplayPanelService displayPanelService;


    @BeforeEach
    public void setUp(){
        displayPanelService = new DisplayPanelService(displayPanelRepository);
    }
    @Test
    void addNewDisplayPanel() {
        displayPanelService.addNewDisplayPanel(displayPanel);

        verify(displayPanelRepository, times(1)).save(displayPanel);
    }

    @Test
    void deleteDisplayPanel() {
        displayPanelService.deleteDisplayPanel(displayPanel);

        verify(displayPanelRepository, times(1)).delete(displayPanel);
    }

    @Test
    void getAllDisplayPanels() {
        when(displayPanelRepository.findAll()).thenReturn(List.of(displayPanel));

        List<DisplayPanel> displayPanels = displayPanelService.getAllDisplayPanels();

        verify(displayPanelRepository, times(1)).findAll();

        assertEquals(displayPanel, displayPanels.get(0));
    }

    @Test
    void getAllDisplayPanelsFromMetaverse() {
        when(displayPanelRepository.findAllByMetaverseName(metaverse.getName())).thenReturn(List.of(displayPanel));

        List<DisplayPanel> displayPanels = displayPanelService.getAllDisplayPanelsFromMetaverse(metaverse.getName());

        verify(displayPanelRepository, times(1)).findAllByMetaverseName(metaverse.getName());

        assertEquals(displayPanel, displayPanels.get(0));
    }

    @Test
    void getAllDisplayPanelsFromMetaverseByUrlName() {
        when(displayPanelRepository.findAllByMetaverseUrlName(metaverse.getUrlName())).thenReturn(List.of(displayPanel));

        List<DisplayPanel> displayPanels = displayPanelService.getAllDisplayPanelsFromMetaverseByUrlName(metaverse.getUrlName());

        verify(displayPanelRepository, times(1)).findAllByMetaverseUrlName(metaverse.getUrlName());

        assertEquals(displayPanel, displayPanels.get(0));
    }

    @Test
    void getDisplayPanelById() {
        when(displayPanelRepository.findById(displayPanel.getId())).thenReturn(Optional.of(displayPanel));

        DisplayPanel dp = displayPanelService.getDisplayPanelById(displayPanel.getId());

        verify(displayPanelRepository, times(1)).findById(displayPanel.getId());

        assertEquals(displayPanel, dp);
    }
}