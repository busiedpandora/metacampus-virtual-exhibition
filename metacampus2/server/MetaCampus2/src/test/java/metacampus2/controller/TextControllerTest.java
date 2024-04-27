package metacampus2.controller;

import metacampus2.model.*;
import metacampus2.service.TextService;
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

class TextControllerTest extends AbstractControllerTest {
    @InjectMocks
    private TextController textController;
    @Mock
    private TextService textService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void texts() throws Exception {
        mockMvc
                .perform(get(MainController.CTRL_RESOURCES + TextController.CTRL_TEXTS)
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
                .andExpect(model().attribute(MainController.MODEL_MENU_CATEGORY, equalTo(MenuCategory.RESOURCES)))

                .andExpect(model().attributeExists(MainController.MODEL_MENU_ENTITY))
                .andExpect(model().attribute(MainController.MODEL_MENU_ENTITY, any(MenuEntity.class)))
                .andExpect(model().attribute(MainController.MODEL_MENU_ENTITY, notNullValue()))
                .andExpect(model().attribute(MainController.MODEL_MENU_ENTITY, equalTo(MenuEntity.TEXT)))

                .andExpect(model().attributeExists(TextController.MODEL_TEXTS))
                .andExpect(model().attribute(TextController.MODEL_TEXTS, any(List.class)))
                .andExpect(model().attribute(TextController.MODEL_TEXTS, everyItem(any(Text.class))))
                .andExpect(model().attribute(TextController.MODEL_TEXTS, notNullValue()))

                .andExpect(view().name(TextController.VIEW_TEXTS));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void newTextForm() throws Exception {
        mockMvc
                .perform(get(MainController.CTRL_RESOURCES + TextController.CTRL_TEXTS + MainController.CTRL_NEW)
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
                .andExpect(model().attribute(MainController.MODEL_MENU_CATEGORY, equalTo(MenuCategory.RESOURCES)))

                .andExpect(model().attributeExists(MainController.MODEL_MENU_ENTITY))
                .andExpect(model().attribute(MainController.MODEL_MENU_ENTITY, any(MenuEntity.class)))
                .andExpect(model().attribute(MainController.MODEL_MENU_ENTITY, notNullValue()))
                .andExpect(model().attribute(MainController.MODEL_MENU_ENTITY, equalTo(MenuEntity.TEXT)))

                .andExpect(model().attributeExists(TextController.MODEL_TEXT))
                .andExpect(model().attribute(TextController.MODEL_TEXT, any(Text.class)))
                .andExpect(model().attribute(TextController.MODEL_TEXT, notNullValue()))

                .andExpect(model().attributeExists(MainController.MODEL_ON_EDIT))
                .andExpect(model().attribute(MainController.MODEL_ON_EDIT, any(Boolean.class)))
                .andExpect(model().attribute(MainController.MODEL_ON_EDIT, notNullValue()))
                .andExpect(model().attribute(MainController.MODEL_ON_EDIT, false))

                .andExpect(model().attributeExists(MainController.MODEL_TITLE))
                .andExpect(model().attribute(MainController.MODEL_TITLE, any(String.class)))
                .andExpect(model().attribute(MainController.MODEL_TITLE, notNullValue()))
                .andExpect(model().attribute(MainController.MODEL_TITLE, equalTo("New text:")))

                .andExpect(model().attributeExists(MainController.MODEL_FORM_HREF))
                .andExpect(model().attribute(MainController.MODEL_FORM_HREF, any(String.class)))
                .andExpect(model().attribute(MainController.MODEL_FORM_HREF, notNullValue()))
                .andExpect(model().attribute(MainController.MODEL_FORM_HREF, equalTo("/resources/texts/new")))

                .andExpect(model().attributeExists(TextPanelController.MODEL_TEXT_PANELS))
                .andExpect(model().attribute(TextPanelController.MODEL_TEXT_PANELS, any(List.class)))
                .andExpect(model().attribute(TextPanelController.MODEL_TEXT_PANELS, everyItem(any(TextPanel.class))))
                .andExpect(model().attribute(TextPanelController.MODEL_TEXT_PANELS, notNullValue()))

                .andExpect(model().attributeDoesNotExist(MainController.MODEL_ERROR))

                .andExpect(view().name(TextController.VIEW_TEXT_FORM));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void editTextForm() throws Exception {
        mockMvc
                .perform(get(MainController.CTRL_RESOURCES + TextController.CTRL_TEXTS
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
                .andExpect(model().attribute(MainController.MODEL_MENU_CATEGORY, equalTo(MenuCategory.RESOURCES)))

                .andExpect(model().attributeExists(MainController.MODEL_MENU_ENTITY))
                .andExpect(model().attribute(MainController.MODEL_MENU_ENTITY, any(MenuEntity.class)))
                .andExpect(model().attribute(MainController.MODEL_MENU_ENTITY, notNullValue()))
                .andExpect(model().attribute(MainController.MODEL_MENU_ENTITY, equalTo(MenuEntity.TEXT)))

                .andExpect(model().attributeExists(TextController.MODEL_TEXT))
                .andExpect(model().attribute(TextController.MODEL_TEXT, any(Text.class)))
                .andExpect(model().attribute(TextController.MODEL_TEXT, notNullValue()))

                .andExpect(model().attributeExists(MainController.MODEL_ON_EDIT))
                .andExpect(model().attribute(MainController.MODEL_ON_EDIT, any(Boolean.class)))
                .andExpect(model().attribute(MainController.MODEL_ON_EDIT, notNullValue()))
                .andExpect(model().attribute(MainController.MODEL_ON_EDIT, true))

                .andExpect(model().attributeExists(MainController.MODEL_TITLE))
                .andExpect(model().attribute(MainController.MODEL_TITLE, any(String.class)))
                .andExpect(model().attribute(MainController.MODEL_TITLE, notNullValue()))
                .andExpect(model().attribute(MainController.MODEL_TITLE, equalTo("Edit text:")))

                .andExpect(model().attributeExists(MainController.MODEL_FORM_HREF))
                .andExpect(model().attribute(MainController.MODEL_FORM_HREF, any(String.class)))
                .andExpect(model().attribute(MainController.MODEL_FORM_HREF, notNullValue()))
                .andExpect(model().attribute(MainController.MODEL_FORM_HREF, equalTo("/resources/texts/1/edit")))

                .andExpect(model().attributeDoesNotExist(MainController.MODEL_ERROR))

                .andExpect(view().name(TextController.VIEW_TEXT_FORM));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void editText() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(textController).build();

        when(textService.getTextByTitle(Mockito.anyString())).thenReturn(null);
        when(textService.getTextById(Mockito.anyLong())).thenReturn(text);
        when(textService.renameFile(Mockito.anyString(), Mockito.any(), Mockito.any())).thenReturn(false);

        mockMvc
                .perform(post(MainController.CTRL_RESOURCES + TextController.CTRL_TEXTS
                        + "/1" + MainController.CTRL_EDIT)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("title", text.getTitle())
                        .param("fileName", text.getFileName())
                        .param("textPanels[0].id", "1"))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is3xxRedirection())
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(status().isFound())

                .andExpect(model().size(0))
                .andExpect(model().hasNoErrors())

                .andExpect(view().name("redirect:" + MainController.CTRL_RESOURCES
                        + TextController.CTRL_TEXTS + "/1" + MainController.CTRL_EDIT + "?error=error occured while renaming the text file"))
                .andExpect(redirectedUrl(MainController.CTRL_RESOURCES
                        + TextController.CTRL_TEXTS + "/1" + MainController.CTRL_EDIT + "?error=error occured while renaming the text file"));


        when(textService.getTextByTitle(Mockito.anyString())).thenReturn(text);

        mockMvc
                .perform(post(MainController.CTRL_RESOURCES + TextController.CTRL_TEXTS
                        + "/2" + MainController.CTRL_EDIT)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("title", text.getTitle())
                        .param("fileName", text.getFileName())
                        .param("textPanels[0].id", "1"))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is3xxRedirection())
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(status().isFound())

                .andExpect(model().size(0))
                .andExpect(model().hasNoErrors())

                .andExpect(view().name("redirect:" + MainController.CTRL_RESOURCES
                        + TextController.CTRL_TEXTS + "/2" + MainController.CTRL_EDIT + "?error=a text with this title already exists"))
                .andExpect(redirectedUrl(MainController.CTRL_RESOURCES
                        + TextController.CTRL_TEXTS + "/2" + MainController.CTRL_EDIT + "?error=a text with this title already exists"));


        when(textService.getTextByTitle(Mockito.anyString())).thenReturn(null);
        when(textService.getTextById(Mockito.anyLong())).thenReturn(text);
        when(textService.renameFile(Mockito.anyString(), Mockito.any(), Mockito.any())).thenReturn(true);

        mockMvc
                .perform(post(MainController.CTRL_RESOURCES + TextController.CTRL_TEXTS
                        + "/1" + MainController.CTRL_EDIT)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("title", text.getTitle())
                        .param("fileName", text.getFileName())
                        .param("textPanels[0].id", "1"))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is3xxRedirection())
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(status().isFound())

                .andExpect(model().size(0))
                .andExpect(model().hasNoErrors())

                .andExpect(view().name("redirect:" + MainController.CTRL_RESOURCES
                        + TextController.CTRL_TEXTS))
                .andExpect(redirectedUrl(MainController.CTRL_RESOURCES
                        + TextController.CTRL_TEXTS));

        verify(textService).editText(Mockito.any());
    }
}