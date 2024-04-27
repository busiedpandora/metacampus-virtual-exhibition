package metacampus2.controller;

import metacampus2.model.MenuCategory;
import metacampus2.model.MenuEntity;
import metacampus2.model.Metaverse;
import metacampus2.model.TextPanel;
import metacampus2.service.IMetaverseService;
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

class MetaverseControllerTest extends AbstractControllerTest {
    @InjectMocks
    private MetaverseController metaverseController;
    @Mock
    private IMetaverseService metaverseService;
    @Mock
    private IUserService userService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void metaverses() throws Exception {
        mockMvc
                .perform(get(MainController.CTRL_METAVERSES)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.TEXT_PLAIN))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is2xxSuccessful())
                .andExpect(status().is(HttpStatus.OK.value()))

                .andExpect(model().size(7))
                .andExpect(model().hasNoErrors())

                .andExpect(model().attributeExists(MainController.MODEL_PROJECT_NAME))
                .andExpect(model().attribute(MainController.MODEL_PROJECT_NAME, any(String.class)))
                .andExpect(model().attribute(MainController.MODEL_PROJECT_NAME, notNullValue()))
                .andExpect(model().attribute(MainController.MODEL_PROJECT_NAME, equalTo("MetaCampus")))

                .andExpect(model().attributeExists(MainController.MODEL_MENU_CATEGORY))
                .andExpect(model().attribute(MainController.MODEL_MENU_CATEGORY, any(MenuCategory.class)))
                .andExpect(model().attribute(MainController.MODEL_MENU_CATEGORY, notNullValue()))
                .andExpect(model().attribute(MainController.MODEL_MENU_CATEGORY, equalTo(MenuCategory.METAVERSES)))

                .andExpect(model().attributeExists(MetaverseController.MODEL_METAVERSES))
                .andExpect(model().attribute(MetaverseController.MODEL_METAVERSES, any(List.class)))
                .andExpect(model().attribute(MetaverseController.MODEL_METAVERSES, everyItem(any(Metaverse.class))))
                .andExpect(model().attribute(MetaverseController.MODEL_METAVERSES, notNullValue()))

                .andExpect(view().name(MetaverseController.VIEW_METAVERSES));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void newMetaverseForm() throws Exception {
        mockMvc
                .perform(get(MainController.CTRL_METAVERSES + MainController.CTRL_NEW)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.TEXT_PLAIN))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is2xxSuccessful())
                .andExpect(status().is(HttpStatus.OK.value()))

                .andExpect(model().size(10))
                .andExpect(model().hasNoErrors())

                .andExpect(model().attributeExists(MainController.MODEL_PROJECT_NAME))
                .andExpect(model().attribute(MainController.MODEL_PROJECT_NAME, any(String.class)))
                .andExpect(model().attribute(MainController.MODEL_PROJECT_NAME, notNullValue()))
                .andExpect(model().attribute(MainController.MODEL_PROJECT_NAME, equalTo("MetaCampus")))

                .andExpect(model().attributeExists(MainController.MODEL_MENU_CATEGORY))
                .andExpect(model().attribute(MainController.MODEL_MENU_CATEGORY, any(MenuCategory.class)))
                .andExpect(model().attribute(MainController.MODEL_MENU_CATEGORY, notNullValue()))
                .andExpect(model().attribute(MainController.MODEL_MENU_CATEGORY, equalTo(MenuCategory.METAVERSES)))

                .andExpect(model().attributeExists(MetaverseController.MODEL_METAVERSE))
                .andExpect(model().attribute(MetaverseController.MODEL_METAVERSE, any(Metaverse.class)))
                .andExpect(model().attribute(MetaverseController.MODEL_METAVERSE, notNullValue()))

                .andExpect(model().attributeExists(MainController.MODEL_TITLE))
                .andExpect(model().attribute(MainController.MODEL_TITLE, any(String.class)))
                .andExpect(model().attribute(MainController.MODEL_TITLE, notNullValue()))
                .andExpect(model().attribute(MainController.MODEL_TITLE, equalTo("New metaverse:")))

                .andExpect(model().attributeExists(MainController.MODEL_FORM_HREF))
                .andExpect(model().attribute(MainController.MODEL_FORM_HREF, any(String.class)))
                .andExpect(model().attribute(MainController.MODEL_FORM_HREF, notNullValue()))
                .andExpect(model().attribute(MainController.MODEL_FORM_HREF, equalTo("/metaverses/new")))

                .andExpect(model().attributeDoesNotExist(MainController.MODEL_ERROR))

                .andExpect(view().name(MetaverseController.VIEW_METAVERSE_FORM));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void editMetaverseForm() throws Exception {
        mockMvc
                .perform(get(MainController.CTRL_METAVERSES + "/1" + MainController.CTRL_EDIT)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.TEXT_PLAIN))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is2xxSuccessful())
                .andExpect(status().is(HttpStatus.OK.value()))

                .andExpect(model().size(10))
                .andExpect(model().hasNoErrors())

                .andExpect(model().attributeExists(MainController.MODEL_PROJECT_NAME))
                .andExpect(model().attribute(MainController.MODEL_PROJECT_NAME, any(String.class)))
                .andExpect(model().attribute(MainController.MODEL_PROJECT_NAME, notNullValue()))
                .andExpect(model().attribute(MainController.MODEL_PROJECT_NAME, equalTo("MetaCampus")))

                .andExpect(model().attributeExists(MainController.MODEL_MENU_CATEGORY))
                .andExpect(model().attribute(MainController.MODEL_MENU_CATEGORY, any(MenuCategory.class)))
                .andExpect(model().attribute(MainController.MODEL_MENU_CATEGORY, notNullValue()))
                .andExpect(model().attribute(MainController.MODEL_MENU_CATEGORY, equalTo(MenuCategory.METAVERSES)))

                .andExpect(model().attributeExists(MetaverseController.MODEL_METAVERSE))
                .andExpect(model().attribute(MetaverseController.MODEL_METAVERSE, any(Metaverse.class)))
                .andExpect(model().attribute(MetaverseController.MODEL_METAVERSE, notNullValue()))

                .andExpect(model().attributeExists(MainController.MODEL_TITLE))
                .andExpect(model().attribute(MainController.MODEL_TITLE, any(String.class)))
                .andExpect(model().attribute(MainController.MODEL_TITLE, notNullValue()))
                .andExpect(model().attribute(MainController.MODEL_TITLE, equalTo("Edit metaverse:")))

                .andExpect(model().attributeExists(MainController.MODEL_FORM_HREF))
                .andExpect(model().attribute(MainController.MODEL_FORM_HREF, any(String.class)))
                .andExpect(model().attribute(MainController.MODEL_FORM_HREF, notNullValue()))
                .andExpect(model().attribute(MainController.MODEL_FORM_HREF, equalTo("/metaverses/1/edit")))

                .andExpect(model().attributeDoesNotExist(MainController.MODEL_ERROR))

                .andExpect(view().name(MetaverseController.VIEW_METAVERSE_FORM));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void newMetaverse() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(metaverseController).build();

        mockMvc
                .perform(post(MainController.CTRL_METAVERSES + MainController.CTRL_NEW)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.TEXT_PLAIN)
                        .param("name", metaverse.getName())
                        .param("minXDimension", String.valueOf(3))
                        .param("maxXDimension", String.valueOf(1)))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is3xxRedirection())
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(status().isFound())

                .andExpect(model().size(0))
                .andExpect(model().hasNoErrors())

                .andExpect(view().name("redirect:" + MainController.CTRL_METAVERSES +  MainController.CTRL_NEW
                        + "?error=minimum dimension cannot be greater than maximum dimension"))
                .andExpect(redirectedUrl(MainController.CTRL_METAVERSES +  MainController.CTRL_NEW
                        + "?error=minimum dimension cannot be greater than maximum dimension"));

        when(metaverseService.getMetaverseByName(Mockito.anyString())).thenReturn(null);
        when(metaverseService.createDirectory(Mockito.any())).thenReturn(true);
        when(userService.getUserLogged()).thenReturn(metaverse.getCreator());

        mockMvc
                .perform(post(MainController.CTRL_METAVERSES + MainController.CTRL_NEW)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.TEXT_PLAIN)
                        .param("name", metaverse.getName())
                        .param("minXDimension", String.valueOf(metaverse.getMinXDimension()))
                        .param("maxXDimension", String.valueOf(metaverse.getMaxXDimension())))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is3xxRedirection())
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(status().isFound())

                .andExpect(model().size(0))
                .andExpect(model().hasNoErrors())

                .andExpect(view().name("redirect:" + MainController.CTRL_METAVERSES))
                .andExpect(redirectedUrl(MainController.CTRL_METAVERSES));

        verify(metaverseService).addNewMetaverse(Mockito.any());

        when(metaverseService.getMetaverseByName(Mockito.anyString())).thenReturn(metaverse);

        mockMvc
                .perform(post(MainController.CTRL_METAVERSES + MainController.CTRL_NEW)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.TEXT_PLAIN)
                        .param("name", metaverse.getName())
                        .param("minXDimension", String.valueOf(metaverse.getMinXDimension()))
                        .param("maxXDimension", String.valueOf(metaverse.getMaxXDimension())))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is3xxRedirection())
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(status().isFound())

                .andExpect(model().size(0))
                .andExpect(model().hasNoErrors())

                .andExpect(view().name("redirect:" + MainController.CTRL_METAVERSES +  MainController.CTRL_NEW
                        + "?error=a metaverse with this name already exists"))
                .andExpect(redirectedUrl(MainController.CTRL_METAVERSES +  MainController.CTRL_NEW
                        + "?error=a metaverse with this name already exists"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void editMetaverse() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(metaverseController).build();

        mockMvc
                .perform(post(MainController.CTRL_METAVERSES + "/1" + MainController.CTRL_EDIT)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.TEXT_PLAIN)
                        .param("name", metaverse.getName())
                        .param("minXDimension", String.valueOf(3))
                        .param("maxXDimension", String.valueOf(1)))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is3xxRedirection())
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(status().isFound())

                .andExpect(model().size(0))
                .andExpect(model().hasNoErrors())

                .andExpect(view().name("redirect:" + MainController.CTRL_METAVERSES + "/1" + MainController.CTRL_EDIT
                        + "?error=minimum dimension cannot be greater than maximum dimension"))
                .andExpect(redirectedUrl(MainController.CTRL_METAVERSES + "/1" + MainController.CTRL_EDIT
                        + "?error=minimum dimension cannot be greater than maximum dimension"));

        when(metaverseService.getMetaverseByName(Mockito.anyString())).thenReturn(null);
        when(metaverseService.renameDirectory(Mockito.anyString(), Mockito.any())).thenReturn(true);
        when(metaverseService.getMetaverseById(Mockito.anyLong())).thenReturn(metaverse);
        when(userService.getUserLogged()).thenReturn(metaverse.getCreator());

        mockMvc
                .perform(post(MainController.CTRL_METAVERSES + "/1" + MainController.CTRL_EDIT)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.TEXT_PLAIN)
                        .param("name", metaverse.getName())
                        .param("minXDimension", String.valueOf(metaverse.getMinXDimension()))
                        .param("maxXDimension", String.valueOf(metaverse.getMaxXDimension())))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is3xxRedirection())
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(status().isFound())

                .andExpect(model().size(0))
                .andExpect(model().hasNoErrors())

                .andExpect(view().name("redirect:" + MainController.CTRL_METAVERSES))
                .andExpect(redirectedUrl(MainController.CTRL_METAVERSES));

        verify(metaverseService).addNewMetaverse(Mockito.any());

        when(metaverseService.getMetaverseByName(Mockito.anyString())).thenReturn(metaverse);

        mockMvc
                .perform(post(MainController.CTRL_METAVERSES + "/2" + MainController.CTRL_EDIT)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.TEXT_PLAIN)
                        .param("name", metaverse.getName())
                        .param("minXDimension", String.valueOf(metaverse.getMinXDimension()))
                        .param("maxXDimension", String.valueOf(metaverse.getMaxXDimension())))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is3xxRedirection())
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(status().isFound())

                .andExpect(model().size(0))
                .andExpect(model().hasNoErrors())

                .andExpect(view().name("redirect:" + MainController.CTRL_METAVERSES + "/2" + MainController.CTRL_EDIT
                        + "?error=a metaverse with this name already exists"))
                .andExpect(redirectedUrl(MainController.CTRL_METAVERSES + "/2" + MainController.CTRL_EDIT
                        + "?error=a metaverse with this name already exists"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void metaversesList() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(metaverseController).build();

        mockMvc
                .perform(get(MainController.CTRL_METAVERSES + MetaverseController.CTRL_METAVERSES_LIST)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is2xxSuccessful())
                .andExpect(status().is(HttpStatus.OK.value()))

                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());

        verify(metaverseService, Mockito.times(1)).getAllMetaverses();
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void metaverse() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(metaverseController).build();

        mockMvc
                .perform(get(MainController.CTRL_METAVERSES + "/campus_est_supsi")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is2xxSuccessful())
                .andExpect(status().is(HttpStatus.OK.value()));

        verify(metaverseService, Mockito.times(1)).getMetaverseByUrlName(Mockito.anyString());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void deleteMetaverse() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(metaverseController).build();

        when(metaverseService.getMetaverseById(Mockito.anyLong())).thenReturn(metaverse);
        when(userService.getUserLogged()).thenReturn(metaverse.getCreator());

        mockMvc
                .perform(get(MainController.CTRL_METAVERSES
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

                .andExpect(view().name("redirect:" + MainController.CTRL_METAVERSES))
                .andExpect(redirectedUrl(MainController.CTRL_METAVERSES));

        verify(metaverseService).deleteDirectory(metaverse);
        verify(metaverseService).deleteMetaverse(metaverse);
    }
}