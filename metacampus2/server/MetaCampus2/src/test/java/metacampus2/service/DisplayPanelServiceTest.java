package metacampus2.service;

import metacampus2.AbstractTest;
import metacampus2.model.DisplayPanel;
import metacampus2.model.Image;
import metacampus2.repository.IDisplayPanelRepository;
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
class DisplayPanelServiceTest  extends AbstractTest {

    @Mock
    private IDisplayPanelRepository displayPanelRepository;
    private DisplayPanelService displayPanelService;

    @BeforeEach
    public void setUp(){
        displayPanelService = new DisplayPanelService(displayPanelRepository);
    }

    @Test
    void getAllDisplayPanels() {

        List<DisplayPanel> displayPanelList = new ArrayList<>();

        DisplayPanel displayPanel = new DisplayPanel();
        Image image = new Image();
        image.setFileName("picasso - 1");

        displayPanelList.add(displayPanel);
        displayPanelList.add(new DisplayPanel());
        displayPanelList.add(new DisplayPanel());

        when(displayPanelRepository.findAll()).thenReturn(displayPanelList);

        assertEquals(displayPanelList.size(), displayPanelService.getAllDisplayPanels().size());

    }

    @Test
    void getAllDisplayPanelsFromMetaverse() {

        List<DisplayPanel> displayPanelList = new ArrayList<>();

        DisplayPanel displayPanel = new DisplayPanel();

        displayPanel.setName("Picasso");

        displayPanelList.add(displayPanel);
        displayPanelList.add(new DisplayPanel());

        when(displayPanelRepository.findAllByMetaverseName(Mockito.anyString())).thenReturn(displayPanelList);

        assertEquals(displayPanel.getName(), displayPanelService.getAllDisplayPanelsFromMetaverse("USI").get(0).getName());

    }
}