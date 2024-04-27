package metacampus2.controller;

import metacampus2.model.MenuCategory;
import metacampus2.model.MenuEntity;
import metacampus2.model.Metaverse;
import metacampus2.model.TextPanel;
import metacampus2.service.ISpaceService;
import metacampus2.service.ITextPanelService;
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

class TextPanelControllerTest extends AbstractControllerTest {
    @InjectMocks
    private TextPanelController textPanelController;
    @Mock
    private ITextPanelService textPanelService;
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
    void textPanels() throws Exception {
        mockMvc
                .perform(get(MainController.CTRL_SPACES + TextPanelController.CTRL_TEXT_PANELS)
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
                .andExpect(model().attribute(MainController.MODEL_MENU_ENTITY, equalTo(MenuEntity.TEXT_PANEL)))

                .andExpect(model().attributeExists(TextPanelController.MODEL_TEXT_PANELS))
                .andExpect(model().attribute(TextPanelController.MODEL_TEXT_PANELS, any(List.class)))
                .andExpect(model().attribute(TextPanelController.MODEL_TEXT_PANELS, everyItem(any(TextPanel.class))))
                .andExpect(model().attribute(TextPanelController.MODEL_TEXT_PANELS, notNullValue()))

                .andExpect(view().name(TextPanelController.VIEW_TEXT_PANELS));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void newTextPanelForm() throws Exception {
        mockMvc
                .perform(get(MainController.CTRL_SPACES + TextPanelController.CTRL_TEXT_PANELS
                        + MainController.CTRL_NEW)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.TEXT_PLAIN))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is2xxSuccessful())
                .andExpect(status().is(HttpStatus.OK.value()))

                .andExpect(model().size(12))
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
                .andExpect(model().attribute(MainController.MODEL_MENU_ENTITY, equalTo(MenuEntity.TEXT_PANEL)))

                .andExpect(model().attributeExists(TextPanelController.MODEL_TEXT_PANEL))
                .andExpect(model().attribute(TextPanelController.MODEL_TEXT_PANEL, any(TextPanel.class)))
                .andExpect(model().attribute(TextPanelController.MODEL_TEXT_PANEL, notNullValue()))

                .andExpect(model().attributeExists(MainController.MODEL_TITLE))
                .andExpect(model().attribute(MainController.MODEL_TITLE, any(String.class)))
                .andExpect(model().attribute(MainController.MODEL_TITLE, notNullValue()))
                .andExpect(model().attribute(MainController.MODEL_TITLE, equalTo("New text panel:")))

                .andExpect(model().attributeExists(MainController.MODEL_FORM_HREF))
                .andExpect(model().attribute(MainController.MODEL_FORM_HREF, any(String.class)))
                .andExpect(model().attribute(MainController.MODEL_FORM_HREF, notNullValue()))
                .andExpect(model().attribute(MainController.MODEL_FORM_HREF, equalTo("/spaces/text-panels/new")))

                .andExpect(model().attributeExists(TextPanelController.MODEL_METAVERSES))
                .andExpect(model().attribute(TextPanelController.MODEL_METAVERSES, any(List.class)))
                .andExpect(model().attribute(TextPanelController.MODEL_METAVERSES, everyItem(any(Metaverse.class))))
                .andExpect(model().attribute(TextPanelController.MODEL_METAVERSES, notNullValue()))

                .andExpect(model().attributeDoesNotExist(MainController.MODEL_ERROR))

                .andExpect(view().name(TextPanelController.VIEW_TEXT_PANEL_FORM));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void editTextPanelForm() throws Exception {
        when(textPanelService.getTextPanelById(1L)).thenReturn(textPanel);

        mockMvc
                .perform(get(MainController.CTRL_SPACES + TextPanelController.CTRL_TEXT_PANELS
                        + "/1" + MainController.CTRL_EDIT)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.TEXT_PLAIN))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is2xxSuccessful())
                .andExpect(status().is(HttpStatus.OK.value()))

                .andExpect(model().size(12))
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
                .andExpect(model().attribute(MainController.MODEL_MENU_ENTITY, equalTo(MenuEntity.TEXT_PANEL)))

                .andExpect(model().attributeExists(TextPanelController.MODEL_TEXT_PANEL))
                .andExpect(model().attribute(TextPanelController.MODEL_TEXT_PANEL, any(TextPanel.class)))
                .andExpect(model().attribute(TextPanelController.MODEL_TEXT_PANEL, notNullValue()))

                .andExpect(model().attributeExists(MainController.MODEL_TITLE))
                .andExpect(model().attribute(MainController.MODEL_TITLE, any(String.class)))
                .andExpect(model().attribute(MainController.MODEL_TITLE, notNullValue()))
                .andExpect(model().attribute(MainController.MODEL_TITLE, equalTo("Edit text panel:")))

                .andExpect(model().attributeExists(MainController.MODEL_FORM_HREF))
                .andExpect(model().attribute(MainController.MODEL_FORM_HREF, any(String.class)))
                .andExpect(model().attribute(MainController.MODEL_FORM_HREF, notNullValue()))
                .andExpect(model().attribute(MainController.MODEL_FORM_HREF, equalTo("/spaces/text-panels/1/edit")))

                .andExpect(model().attributeExists(TextPanelController.MODEL_METAVERSES))
                .andExpect(model().attribute(TextPanelController.MODEL_METAVERSES, any(List.class)))
                .andExpect(model().attribute(TextPanelController.MODEL_METAVERSES, everyItem(any(Metaverse.class))))
                .andExpect(model().attribute(TextPanelController.MODEL_METAVERSES, notNullValue()))

                .andExpect(model().attributeDoesNotExist(MainController.MODEL_ERROR))

                .andExpect(view().name(TextPanelController.VIEW_TEXT_PANEL_FORM));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void textPanelsFromMetaverse() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(textPanelController).build();

        mockMvc
                .perform(get(MainController.CTRL_SPACES + "/campus_est_supsi"
                        + TextPanelController.CTRL_TEXT_PANELS)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is2xxSuccessful())
                .andExpect(status().is(HttpStatus.OK.value()))

                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());

        verify(textPanelService, Mockito.times(1)).getAllTextPanelsFromMetaverseByUrlName("campus_est_supsi");
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void newTextPanel() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(textPanelController).build();

        when(spaceService.getSpaceByCoordinatesAndMetaverse(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString()))
                .thenReturn(textPanel);

        mockMvc
                .perform(post(MainController.CTRL_SPACES + TextPanelController.CTRL_TEXT_PANELS
                        + MainController.CTRL_NEW)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.TEXT_PLAIN)
                        .param("name", textPanel.getName())
                        .param("coordinates.x", String.valueOf(textPanel.getCoordinates().getX()))
                        .param("coordinates.y", String.valueOf(textPanel.getCoordinates().getY()))
                        .param("coordinates.z", String.valueOf(textPanel.getCoordinates().getZ()))
                        .param("metaverse.name", textPanel.getMetaverse().getName()))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is3xxRedirection())
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(status().isFound())

                .andExpect(model().size(0))
                .andExpect(model().hasNoErrors())

                .andExpect(view().name("redirect:" + MainController.CTRL_SPACES
                        + TextPanelController.CTRL_TEXT_PANELS + MainController.CTRL_NEW + "?error=a space with these coordinates already exists"))
                .andExpect(redirectedUrl(MainController.CTRL_SPACES + TextPanelController.CTRL_TEXT_PANELS
                        + MainController.CTRL_NEW + "?error=a space with these coordinates already exists"));

        when(spaceService.getSpaceByCoordinatesAndMetaverse(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString()))
                .thenReturn(null);
        when(spaceService.getSpaceByNameAndMetaverse(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(textPanel);

        mockMvc
                .perform(post(MainController.CTRL_SPACES + TextPanelController.CTRL_TEXT_PANELS
                        + MainController.CTRL_NEW)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.TEXT_PLAIN)
                        .param("name", textPanel.getName())
                        .param("coordinates.x", String.valueOf(textPanel.getCoordinates().getX()))
                        .param("coordinates.y", String.valueOf(textPanel.getCoordinates().getY()))
                        .param("coordinates.z", String.valueOf(textPanel.getCoordinates().getZ()))
                        .param("metaverse.name", textPanel.getMetaverse().getName()))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is3xxRedirection())
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(status().isFound())

                .andExpect(model().size(0))
                .andExpect(model().hasNoErrors())

                .andExpect(view().name("redirect:" + MainController.CTRL_SPACES
                        + TextPanelController.CTRL_TEXT_PANELS + MainController.CTRL_NEW + "?error=a text panel with this name already exists"))
                .andExpect(redirectedUrl(MainController.CTRL_SPACES + TextPanelController.CTRL_TEXT_PANELS
                        + MainController.CTRL_NEW + "?error=a text panel with this name already exists"));

        when(spaceService.getSpaceByCoordinatesAndMetaverse(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString()))
                .thenReturn(null);
        when(spaceService.getSpaceByNameAndMetaverse(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(null);
        when(textPanelService.createDirectory(Mockito.any())).thenReturn(true);

        mockMvc
                .perform(post(MainController.CTRL_SPACES + TextPanelController.CTRL_TEXT_PANELS
                        + MainController.CTRL_NEW)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.TEXT_PLAIN)
                        .param("name", textPanel.getName())
                        .param("coordinates.x", String.valueOf(textPanel.getCoordinates().getX()))
                        .param("coordinates.y", String.valueOf(textPanel.getCoordinates().getY()))
                        .param("coordinates.z", String.valueOf(textPanel.getCoordinates().getZ()))
                        .param("metaverse.name", textPanel.getMetaverse().getName()))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is3xxRedirection())
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(status().isFound())

                .andExpect(model().size(0))
                .andExpect(model().hasNoErrors())

                .andExpect(view().name("redirect:" + MainController.CTRL_SPACES
                        + TextPanelController.CTRL_TEXT_PANELS))
                .andExpect(redirectedUrl(MainController.CTRL_SPACES + TextPanelController.CTRL_TEXT_PANELS));

        verify(textPanelService).addNewTextPanel(Mockito.any());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void editTextPanel() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(textPanelController).build();

        when(spaceService.getSpaceByCoordinatesAndMetaverse(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString()))
                .thenReturn(textPanel);

        mockMvc
                .perform(post(MainController.CTRL_SPACES + TextPanelController.CTRL_TEXT_PANELS
                        + "/100" + MainController.CTRL_EDIT)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.TEXT_PLAIN)
                        .param("name", textPanel.getName())
                        .param("coordinates.x", String.valueOf(textPanel.getCoordinates().getX()))
                        .param("coordinates.y", String.valueOf(textPanel.getCoordinates().getY()))
                        .param("coordinates.z", String.valueOf(textPanel.getCoordinates().getZ()))
                        .param("metaverse.name", textPanel.getMetaverse().getName()))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is3xxRedirection())
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(status().isFound())

                .andExpect(model().size(0))
                .andExpect(model().hasNoErrors())

                .andExpect(view().name("redirect:" + MainController.CTRL_SPACES
                        + TextPanelController.CTRL_TEXT_PANELS + "/100" + MainController.CTRL_EDIT + "?error=a space with these coordinates already exists"))
                .andExpect(redirectedUrl(MainController.CTRL_SPACES + TextPanelController.CTRL_TEXT_PANELS
                        + "/100" + MainController.CTRL_EDIT + "?error=a space with these coordinates already exists"));


        when(spaceService.getSpaceByCoordinatesAndMetaverse(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString()))
                .thenReturn(null);
        when(spaceService.getSpaceByNameAndMetaverse(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(null);
        when(spaceService.getSpaceById(Mockito.anyLong())).thenReturn(textPanel);
        when(textPanelService.renameDirectory(Mockito.anyString(), Mockito.any())).thenReturn(true);

        mockMvc
                .perform(post(MainController.CTRL_SPACES + TextPanelController.CTRL_TEXT_PANELS
                        + "/1" + MainController.CTRL_EDIT)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.TEXT_PLAIN)
                        .param("name", textPanel.getName())
                        .param("coordinates.x", String.valueOf(textPanel.getCoordinates().getX()))
                        .param("coordinates.y", String.valueOf(textPanel.getCoordinates().getY()))
                        .param("coordinates.z", String.valueOf(textPanel.getCoordinates().getZ()))
                        .param("metaverse.name", textPanel.getMetaverse().getName()))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is3xxRedirection())
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(status().isFound())

                .andExpect(model().size(0))
                .andExpect(model().hasNoErrors())

                .andExpect(view().name("redirect:" + MainController.CTRL_SPACES
                        + TextPanelController.CTRL_TEXT_PANELS))
                .andExpect(redirectedUrl(MainController.CTRL_SPACES + TextPanelController.CTRL_TEXT_PANELS));

        verify(textPanelService).addNewTextPanel(Mockito.any());


        Mockito.clearInvocations(textPanelService);
        when(spaceService.getSpaceByCoordinatesAndMetaverse(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString()))
                .thenReturn(null);
        when(spaceService.getSpaceByNameAndMetaverse(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(textPanel);
        when(textPanelService.renameDirectory(Mockito.anyString(), Mockito.any())).thenReturn(true);

        mockMvc
                .perform(post(MainController.CTRL_SPACES + TextPanelController.CTRL_TEXT_PANELS
                        + "/1" + MainController.CTRL_EDIT)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.TEXT_PLAIN)
                        .param("name", textPanel.getName())
                        .param("coordinates.x", String.valueOf(textPanel.getCoordinates().getX()))
                        .param("coordinates.y", String.valueOf(textPanel.getCoordinates().getY()))
                        .param("coordinates.z", String.valueOf(textPanel.getCoordinates().getZ()))
                        .param("metaverse.name", textPanel.getMetaverse().getName()))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is3xxRedirection())
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(status().isFound())

                .andExpect(model().size(0))
                .andExpect(model().hasNoErrors())

                .andExpect(view().name("redirect:" + MainController.CTRL_SPACES
                        + TextPanelController.CTRL_TEXT_PANELS))
                .andExpect(redirectedUrl(MainController.CTRL_SPACES + TextPanelController.CTRL_TEXT_PANELS));

        verify(textPanelService).addNewTextPanel(Mockito.any());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void deleteTextPanel() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(textPanelController).build();

        when(textPanelService.getTextPanelById(Mockito.anyLong())).thenReturn(textPanel);
        when(userService.getUserLogged()).thenReturn(textPanel.getCreator());

        mockMvc
                .perform(get(MainController.CTRL_SPACES + TextPanelController.CTRL_TEXT_PANELS
                        + "/1" + MainController.CTRL_DELETE)
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
                        + TextPanelController.CTRL_TEXT_PANELS))
                .andExpect(redirectedUrl(MainController.CTRL_SPACES + TextPanelController.CTRL_TEXT_PANELS));

        verify(textPanelService).deleteDirectory(textPanel);
        verify(textPanelService).deleteTextPanel(textPanel);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void getText() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(textPanelController).build();

        mockMvc
                .perform(get(MainController.CTRL_SPACES + "/campus_est_supsi" + TextPanelController.CTRL_TEXT_PANELS
                        + "/text_panel_1" + MainController.CTRL_TEXTS + "/textTitle/textFileName")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is2xxSuccessful())
                .andExpect(status().is(HttpStatus.OK.value()));

        verify(textPanelService).getTextFile(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
    }
}