package metacampus2.initializer;

import metacampus2.AbstractTest;
import metacampus2.model.DisplayPanelType;
import metacampus2.model.Metaverse;
import metacampus2.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class EntitiesInitializerTest extends AbstractTest {
    @Autowired
    private EntitiesInitializer entitiesInitializer;
    @Autowired
    private IMetaverseService metaverseService;
    @Autowired
    private ITextPanelService textPanelService;
    @Autowired
    private IDisplayPanelService displayPanelService;
    @Autowired
    private ISpaceService spaceService;


    @BeforeEach
    void setUp() {
        entitiesInitializer = new EntitiesInitializer(metaverseService, textPanelService,
                                                        displayPanelService, spaceService);
    }

    @Test
    void run() throws Exception {
        Metaverse metaverse = metaverseService.getMetaverseByName("Campus Est SUPSI");
        assertNotNull(metaverse);
        assertEquals("Campus Est SUPSI", metaverse.getName());

        var textPanels = textPanelService.getAllTextPanelsFromMetaverse("Campus Est SUPSI");
        assertEquals(1, textPanels.size());
        assertEquals("Text Panel 1",  textPanels.get(0).getName());
        assertEquals(-5,  textPanels.get(0).getCoordinates().getX());
        assertEquals(0,  textPanels.get(0).getCoordinates().getY());
        assertEquals(5,  textPanels.get(0).getCoordinates().getZ());

        var displayPanels = displayPanelService.getAllDisplayPanelsFromMetaverse("Campus Est SUPSI");
        assertEquals(1, displayPanels.size());
        assertEquals("Display Panel 1",  displayPanels.get(0).getName());
        assertEquals(DisplayPanelType.ONE_DISPLAY_PANEL_EXHIBITION, displayPanels.get(0).getType());
        assertEquals(5,  displayPanels.get(0).getCoordinates().getX());
        assertEquals(0,  displayPanels.get(0).getCoordinates().getY());
        assertEquals(5,  displayPanels.get(0).getCoordinates().getZ());
    }
}
