package metacampus2.controller;

import metacampus2.model.*;
import metacampus2.service.IDisplayPanelService;
import metacampus2.service.ISpaceService;
import metacampus2.service.IUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class DisplayPanelControllerTest extends AbstractControllerTest {
    @InjectMocks
    private DisplayPanelController displayPanelController;
    @Mock
    private IDisplayPanelService displayPanelService;
    @Mock
    private ISpaceService spaceService;
    @Mock
    private IUserService userService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void displayPanels() throws Exception {
        mockMvc
                .perform(get(MainController.CTRL_SPACES + TextPanelController.CTRL_DISPLAY_PANELS)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.TEXT_PLAIN))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is2xxSuccessful())
                .andExpect(status().is(HttpStatus.OK.value()))

                .andExpect(model().size(8))
                .andExpect(model().hasNoErrors())

                .andExpect(model().attributeExists(MainController.MODEL_PROJECT_NAME))
                .andExpect(model().attribute(MainController.MODEL_PROJECT_NAME, any(String.class)))
                .andExpect(model().attribute(MainController.MODEL_PROJECT_NAME, notNullValue()))
                .andExpect(model().attribute(MainController.MODEL_PROJECT_NAME, equalTo("MetaCampus")))

                .andExpect(model().attributeExists(MainController.MODEL_MENU_CATEGORY))
                .andExpect(model().attribute(MainController.MODEL_MENU_CATEGORY, any(MenuCategory.class)))
                .andExpect(model().attribute(MainController.MODEL_MENU_CATEGORY, notNullValue()))
                .andExpect(model().attribute(MainController.MODEL_MENU_CATEGORY, equalTo(MenuCategory.SPACES)))

                .andExpect(model().attributeExists(MainController.MODEL_MENU_ENTITY))
                .andExpect(model().attribute(MainController.MODEL_MENU_ENTITY, any(MenuEntity.class)))
                .andExpect(model().attribute(MainController.MODEL_MENU_ENTITY, notNullValue()))
                .andExpect(model().attribute(MainController.MODEL_MENU_ENTITY, equalTo(MenuEntity.DISPLAY_PANEL)))

                .andExpect(model().attributeExists(DisplayPanelController.MODEL_DISPLAY_PANELS))
                .andExpect(model().attribute(DisplayPanelController.MODEL_DISPLAY_PANELS, any(List.class)))
                .andExpect(model().attribute(DisplayPanelController.MODEL_DISPLAY_PANELS, everyItem(any(DisplayPanel.class))))
                .andExpect(model().attribute(DisplayPanelController.MODEL_DISPLAY_PANELS, notNullValue()))

                .andExpect(view().name(DisplayPanelController.VIEW_DISPLAY_PANELS));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void newDisplayPanelForm() throws Exception {
        mockMvc
                .perform(get(MainController.CTRL_SPACES + TextPanelController.CTRL_DISPLAY_PANELS
                        + MainController.CTRL_NEW)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.TEXT_PLAIN))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is2xxSuccessful())
                .andExpect(status().is(HttpStatus.OK.value()))

                .andExpect(model().size(13))
                .andExpect(model().hasNoErrors())

                .andExpect(model().attributeExists(MainController.MODEL_PROJECT_NAME))
                .andExpect(model().attribute(MainController.MODEL_PROJECT_NAME, any(String.class)))
                .andExpect(model().attribute(MainController.MODEL_PROJECT_NAME, notNullValue()))
                .andExpect(model().attribute(MainController.MODEL_PROJECT_NAME, equalTo("MetaCampus")))

                .andExpect(model().attributeExists(MainController.MODEL_MENU_CATEGORY))
                .andExpect(model().attribute(MainController.MODEL_MENU_CATEGORY, any(MenuCategory.class)))
                .andExpect(model().attribute(MainController.MODEL_MENU_CATEGORY, notNullValue()))
                .andExpect(model().attribute(MainController.MODEL_MENU_CATEGORY, equalTo(MenuCategory.SPACES)))

                .andExpect(model().attributeExists(MainController.MODEL_MENU_ENTITY))
                .andExpect(model().attribute(MainController.MODEL_MENU_ENTITY, any(MenuEntity.class)))
                .andExpect(model().attribute(MainController.MODEL_MENU_ENTITY, notNullValue()))
                .andExpect(model().attribute(MainController.MODEL_MENU_ENTITY, equalTo(MenuEntity.DISPLAY_PANEL)))

                .andExpect(model().attributeExists(DisplayPanelController.MODEL_DISPLAY_PANEL))
                .andExpect(model().attribute(DisplayPanelController.MODEL_DISPLAY_PANEL, any(DisplayPanel.class)))
                .andExpect(model().attribute(DisplayPanelController.MODEL_DISPLAY_PANEL, notNullValue()))

                .andExpect(model().attributeExists(MainController.MODEL_TITLE))
                .andExpect(model().attribute(MainController.MODEL_TITLE, any(String.class)))
                .andExpect(model().attribute(MainController.MODEL_TITLE, notNullValue()))
                .andExpect(model().attribute(MainController.MODEL_TITLE, equalTo("New display panel:")))

                .andExpect(model().attributeExists(MainController.MODEL_FORM_HREF))
                .andExpect(model().attribute(MainController.MODEL_FORM_HREF, any(String.class)))
                .andExpect(model().attribute(MainController.MODEL_FORM_HREF, notNullValue()))
                .andExpect(model().attribute(MainController.MODEL_FORM_HREF, equalTo("/spaces/display-panels/new")))

                .andExpect(model().attributeExists(DisplayPanelController.MODEL_DISPLAY_PANEL_TYPES))
                .andExpect(model().attribute(DisplayPanelController.MODEL_DISPLAY_PANEL_TYPES, any(DisplayPanelType[].class)))
                .andExpect(model().attribute(DisplayPanelController.MODEL_DISPLAY_PANEL_TYPES, notNullValue()))

                .andExpect(model().attributeExists(DisplayPanelController.MODEL_METAVERSES))
                .andExpect(model().attribute(DisplayPanelController.MODEL_METAVERSES, any(List.class)))
                .andExpect(model().attribute(DisplayPanelController.MODEL_METAVERSES, everyItem(any(Metaverse.class))))
                .andExpect(model().attribute(DisplayPanelController.MODEL_METAVERSES, notNullValue()))

                .andExpect(model().attributeDoesNotExist(MainController.MODEL_ERROR))

                .andExpect(view().name(DisplayPanelController.VIEW_DISPLAY_PANEL_FORM));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void editDisplayPanelForm() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(displayPanelController).build();

        when(displayPanelService.getDisplayPanelById(1L)).thenReturn(displayPanel);

        mockMvc
                .perform(get(MainController.CTRL_SPACES + DisplayPanelController.CTRL_DISPLAY_PANELS
                        + "/1" + MainController.CTRL_EDIT)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.TEXT_PLAIN))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is2xxSuccessful())
                .andExpect(status().is(HttpStatus.OK.value()))

                .andExpect(model().size(13))
                .andExpect(model().hasNoErrors())

                .andExpect(model().attributeExists(MainController.MODEL_MENU_CATEGORY))
                .andExpect(model().attribute(MainController.MODEL_MENU_CATEGORY, any(MenuCategory.class)))
                .andExpect(model().attribute(MainController.MODEL_MENU_CATEGORY, notNullValue()))
                .andExpect(model().attribute(MainController.MODEL_MENU_CATEGORY, equalTo(MenuCategory.SPACES)))

                .andExpect(model().attributeExists(MainController.MODEL_MENU_ENTITY))
                .andExpect(model().attribute(MainController.MODEL_MENU_ENTITY, any(MenuEntity.class)))
                .andExpect(model().attribute(MainController.MODEL_MENU_ENTITY, notNullValue()))
                .andExpect(model().attribute(MainController.MODEL_MENU_ENTITY, equalTo(MenuEntity.DISPLAY_PANEL)))

                .andExpect(model().attributeExists(DisplayPanelController.MODEL_DISPLAY_PANEL))
                .andExpect(model().attribute(DisplayPanelController.MODEL_DISPLAY_PANEL, any(DisplayPanel.class)))
                .andExpect(model().attribute(DisplayPanelController.MODEL_DISPLAY_PANEL, notNullValue()))

                .andExpect(model().attributeExists(MainController.MODEL_TITLE))
                .andExpect(model().attribute(MainController.MODEL_TITLE, any(String.class)))
                .andExpect(model().attribute(MainController.MODEL_TITLE, notNullValue()))
                .andExpect(model().attribute(MainController.MODEL_TITLE, equalTo("Edit display panel:")))

                .andExpect(model().attributeExists(MainController.MODEL_FORM_HREF))
                .andExpect(model().attribute(MainController.MODEL_FORM_HREF, any(String.class)))
                .andExpect(model().attribute(MainController.MODEL_FORM_HREF, notNullValue()))
                .andExpect(model().attribute(MainController.MODEL_FORM_HREF, equalTo("/spaces/display-panels/1/edit")))

                .andExpect(model().attributeExists(DisplayPanelController.MODEL_DISPLAY_PANEL_TYPES))
                .andExpect(model().attribute(DisplayPanelController.MODEL_DISPLAY_PANEL_TYPES, any(DisplayPanelType[].class)))
                .andExpect(model().attribute(DisplayPanelController.MODEL_DISPLAY_PANEL_TYPES, notNullValue()))

                .andExpect(model().attributeExists(DisplayPanelController.MODEL_METAVERSES))
                .andExpect(model().attribute(DisplayPanelController.MODEL_METAVERSES, any(List.class)))
                .andExpect(model().attribute(DisplayPanelController.MODEL_METAVERSES, everyItem(any(Metaverse.class))))
                .andExpect(model().attribute(DisplayPanelController.MODEL_METAVERSES, notNullValue()))

                .andExpect(model().attributeDoesNotExist(MainController.MODEL_ERROR))

                .andExpect(view().name(DisplayPanelController.VIEW_DISPLAY_PANEL_FORM));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void displayPanelsFromMetaverse() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(displayPanelController).build();

        mockMvc
                .perform(get(MainController.CTRL_SPACES + "/campus_est_supsi"
                        + DisplayPanelController.CTRL_DISPLAY_PANELS)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is2xxSuccessful())
                .andExpect(status().is(HttpStatus.OK.value()))

                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());

        verify(displayPanelService, Mockito.times(1)).getAllDisplayPanelsFromMetaverseByUrlName("campus_est_supsi");
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void newDisplayPanel() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(displayPanelController).build();

        when(spaceService.getSpaceByCoordinatesAndMetaverse(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString()))
                .thenReturn(displayPanel);

        mockMvc
                .perform(post(MainController.CTRL_SPACES + DisplayPanelController.CTRL_DISPLAY_PANELS
                        + MainController.CTRL_NEW)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.TEXT_PLAIN)
                        .param("name", displayPanel.getName())
                        .param("coordinates.x", String.valueOf(displayPanel.getCoordinates().getX()))
                        .param("coordinates.y", String.valueOf(displayPanel.getCoordinates().getY()))
                        .param("coordinates.z", String.valueOf(displayPanel.getCoordinates().getZ()))
                        .param("metaverse.name", displayPanel.getMetaverse().getName()))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is3xxRedirection())
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(status().isFound())

                .andExpect(model().size(0))
                .andExpect(model().hasNoErrors())

                .andExpect(view().name("redirect:" + MainController.CTRL_SPACES
                        + DisplayPanelController.CTRL_DISPLAY_PANELS + MainController.CTRL_NEW + "?error=a space with these coordinates already exists"))
                .andExpect(redirectedUrl(MainController.CTRL_SPACES + DisplayPanelController.CTRL_DISPLAY_PANELS
                        + MainController.CTRL_NEW + "?error=a space with these coordinates already exists"));

        when(spaceService.getSpaceByCoordinatesAndMetaverse(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString()))
                .thenReturn(null);
        when(spaceService.getSpaceByNameAndMetaverse(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(displayPanel);

        mockMvc
                .perform(post(MainController.CTRL_SPACES + DisplayPanelController.CTRL_DISPLAY_PANELS
                        + MainController.CTRL_NEW)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.TEXT_PLAIN)
                        .param("name", displayPanel.getName())
                        .param("coordinates.x", String.valueOf(displayPanel.getCoordinates().getX()))
                        .param("coordinates.y", String.valueOf(displayPanel.getCoordinates().getY()))
                        .param("coordinates.z", String.valueOf(displayPanel.getCoordinates().getZ()))
                        .param("metaverse.name", displayPanel.getMetaverse().getName()))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is3xxRedirection())
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(status().isFound())

                .andExpect(model().size(0))
                .andExpect(model().hasNoErrors())

                .andExpect(view().name("redirect:" + MainController.CTRL_SPACES
                        + DisplayPanelController.CTRL_DISPLAY_PANELS + MainController.CTRL_NEW + "?error=a display panel with this name already exists"))
                .andExpect(redirectedUrl(MainController.CTRL_SPACES + DisplayPanelController.CTRL_DISPLAY_PANELS
                        + MainController.CTRL_NEW + "?error=a display panel with this name already exists"));

        when(spaceService.getSpaceByCoordinatesAndMetaverse(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString()))
                .thenReturn(null);
        when(spaceService.getSpaceByNameAndMetaverse(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(null);
        when(displayPanelService.createDirectory(Mockito.any())).thenReturn(true);

        mockMvc
                .perform(post(MainController.CTRL_SPACES + DisplayPanelController.CTRL_DISPLAY_PANELS
                        + MainController.CTRL_NEW)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.TEXT_PLAIN)
                        .param("name", displayPanel.getName())
                        .param("coordinates.x", String.valueOf(displayPanel.getCoordinates().getX()))
                        .param("coordinates.y", String.valueOf(displayPanel.getCoordinates().getY()))
                        .param("coordinates.z", String.valueOf(displayPanel.getCoordinates().getZ()))
                        .param("metaverse.name", displayPanel.getMetaverse().getName()))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is3xxRedirection())
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(status().isFound())

                .andExpect(model().size(0))
                .andExpect(model().hasNoErrors())

                .andExpect(view().name("redirect:" + MainController.CTRL_SPACES
                        + TextPanelController.CTRL_DISPLAY_PANELS))
                .andExpect(redirectedUrl(MainController.CTRL_SPACES + TextPanelController.CTRL_DISPLAY_PANELS));

        verify(displayPanelService).addNewDisplayPanel(Mockito.any());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void editDisplayPanel() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(displayPanelController).build();

        when(spaceService.getSpaceByCoordinatesAndMetaverse(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString()))
                .thenReturn(displayPanel);

        mockMvc
                .perform(post(MainController.CTRL_SPACES + DisplayPanelController.CTRL_DISPLAY_PANELS
                        + "/100" + MainController.CTRL_EDIT)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.TEXT_PLAIN)
                        .param("name", displayPanel.getName())
                        .param("coordinates.x", String.valueOf(displayPanel.getCoordinates().getX()))
                        .param("coordinates.y", String.valueOf(displayPanel.getCoordinates().getY()))
                        .param("coordinates.z", String.valueOf(displayPanel.getCoordinates().getZ()))
                        .param("metaverse.name", displayPanel.getMetaverse().getName()))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is3xxRedirection())
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(status().isFound())

                .andExpect(model().size(0))
                .andExpect(model().hasNoErrors())

                .andExpect(view().name("redirect:" + MainController.CTRL_SPACES
                        + DisplayPanelController.CTRL_DISPLAY_PANELS + "/100" + MainController.CTRL_EDIT
                        + "?error=a space with these coordinates already exists"))
                .andExpect(redirectedUrl(MainController.CTRL_SPACES + DisplayPanelController.CTRL_DISPLAY_PANELS
                        + "/100" + MainController.CTRL_EDIT + "?error=a space with these coordinates already exists"));

        when(spaceService.getSpaceByCoordinatesAndMetaverse(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString()))
                .thenReturn(null);
        when(spaceService.getSpaceByNameAndMetaverse(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(null);
        when(spaceService.getSpaceById(Mockito.anyLong())).thenReturn(displayPanel);
        when(displayPanelService.renameDirectory(Mockito.anyString(), Mockito.any())).thenReturn(true);

        mockMvc
                .perform(post(MainController.CTRL_SPACES + DisplayPanelController.CTRL_DISPLAY_PANELS
                        + "/2" + MainController.CTRL_EDIT)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.TEXT_PLAIN)
                        .param("name", displayPanel.getName())
                        .param("coordinates.x", String.valueOf(displayPanel.getCoordinates().getX()))
                        .param("coordinates.y", String.valueOf(displayPanel.getCoordinates().getY()))
                        .param("coordinates.z", String.valueOf(displayPanel.getCoordinates().getZ()))
                        .param("metaverse.name", displayPanel.getMetaverse().getName()))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is3xxRedirection())
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(status().isFound())

                .andExpect(model().size(0))
                .andExpect(model().hasNoErrors())

                .andExpect(view().name("redirect:" + MainController.CTRL_SPACES
                        + DisplayPanelController.CTRL_DISPLAY_PANELS))
                .andExpect(redirectedUrl(MainController.CTRL_SPACES + DisplayPanelController.CTRL_DISPLAY_PANELS));

        verify(displayPanelService).addNewDisplayPanel(Mockito.any());


        Mockito.clearInvocations(displayPanelService);
        when(spaceService.getSpaceByCoordinatesAndMetaverse(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString()))
                .thenReturn(null);
        when(spaceService.getSpaceByNameAndMetaverse(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(displayPanel);

        mockMvc
                .perform(post(MainController.CTRL_SPACES + DisplayPanelController.CTRL_DISPLAY_PANELS
                        + "/2" + MainController.CTRL_EDIT)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.TEXT_PLAIN)
                        .param("name", displayPanel.getName())
                        .param("coordinates.x", String.valueOf(displayPanel.getCoordinates().getX()))
                        .param("coordinates.y", String.valueOf(displayPanel.getCoordinates().getY()))
                        .param("coordinates.z", String.valueOf(displayPanel.getCoordinates().getZ()))
                        .param("metaverse.name", displayPanel.getMetaverse().getName()))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is3xxRedirection())
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(status().isFound())

                .andExpect(model().size(0))
                .andExpect(model().hasNoErrors())

                .andExpect(view().name("redirect:" + MainController.CTRL_SPACES
                        + TextPanelController.CTRL_DISPLAY_PANELS))
                .andExpect(redirectedUrl(MainController.CTRL_SPACES + TextPanelController.CTRL_DISPLAY_PANELS));

        verify(displayPanelService).addNewDisplayPanel(Mockito.any());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void deleteDisplayPanel() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(displayPanelController).build();

        when(displayPanelService.getDisplayPanelById(Mockito.anyLong())).thenReturn(displayPanel);
        when(userService.getUserLogged()).thenReturn(displayPanel.getCreator());

        mockMvc
                .perform(get(MainController.CTRL_SPACES + DisplayPanelController.CTRL_DISPLAY_PANELS
                        + "/2" + MainController.CTRL_DELETE)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.TEXT_PLAIN))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is3xxRedirection())
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(status().isFound())

                .andExpect(model().size(0))
                .andExpect(model().hasNoErrors())

                .andExpect(view().name("redirect:" + MainController.CTRL_SPACES
                        + DisplayPanelController.CTRL_DISPLAY_PANELS))
                .andExpect(redirectedUrl(MainController.CTRL_SPACES + DisplayPanelController.CTRL_DISPLAY_PANELS));

        verify(displayPanelService).deleteDirectory(displayPanel);
        verify(displayPanelService).deleteDisplayPanel(displayPanel);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void getImage() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(displayPanelController).build();

        mockMvc
                .perform(get(MainController.CTRL_SPACES + "/campus_est_supsi" + DisplayPanelController.CTRL_DISPLAY_PANELS
                        + "/one_display_panel_exhibition" + MainController.CTRL_IMAGES + "/imageTitle/imageFileName")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is2xxSuccessful())
                .andExpect(status().is(HttpStatus.OK.value()));

        verify(displayPanelService).getImageFile(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void getAudio() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(displayPanelController).build();

        when(displayPanelService.getAudioFile(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(),
                Mockito.anyString(), Mockito.anyString())).thenReturn(new byte[1]);

        mockMvc
                .perform(get(MainController.CTRL_SPACES + "/campus_est_supsi" + DisplayPanelController.CTRL_DISPLAY_PANELS
                        + "/one_display_panel_exhibition" + MainController.CTRL_IMAGES + "/imageTitle" + MainController.CTRL_AUDIOS
                        + "/audioTitle/audioFileName")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is2xxSuccessful())
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(content().contentTypeCompatibleWith("audio/wav"));

        verify(displayPanelService).getAudioFile(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(),
                Mockito.anyString(), Mockito.anyString());
    }
}