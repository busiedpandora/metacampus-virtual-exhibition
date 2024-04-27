package metacampus2.controller;

import metacampus2.model.Audio;
import metacampus2.model.Image;
import metacampus2.model.MenuCategory;
import metacampus2.model.MenuEntity;
import metacampus2.service.AudioService;
import org.junit.jupiter.api.BeforeEach;
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

class AudioControllerTest extends AbstractControllerTest {
    @InjectMocks
    private AudioController audioController;
    @Mock
    private AudioService audioService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void audios() throws Exception {
        mockMvc
                .perform(get(MainController.CTRL_RESOURCES + AudioController.CTRL_AUDIOS)
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
                .andExpect(model().attribute(MainController.MODEL_MENU_ENTITY, equalTo(MenuEntity.AUDIO)))

                .andExpect(model().attributeExists(AudioController.MODEL_AUDIOS))
                .andExpect(model().attribute(AudioController.MODEL_AUDIOS, any(List.class)))
                .andExpect(model().attribute(AudioController.MODEL_AUDIOS, everyItem(any(Audio.class))))
                .andExpect(model().attribute(AudioController.MODEL_AUDIOS, notNullValue()))

                .andExpect(view().name(AudioController.VIEW_AUDIOS));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void newAudioForm() throws Exception {
        mockMvc
                .perform(get(MainController.CTRL_RESOURCES + AudioController.CTRL_AUDIOS + MainController.CTRL_NEW)
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
                .andExpect(model().attribute(MainController.MODEL_MENU_ENTITY, equalTo(MenuEntity.AUDIO)))

                .andExpect(model().attributeExists(AudioController.MODEL_AUDIO))
                .andExpect(model().attribute(AudioController.MODEL_AUDIO, any(Audio.class)))
                .andExpect(model().attribute(AudioController.MODEL_AUDIO, notNullValue()))

                .andExpect(model().attributeExists(MainController.MODEL_ON_EDIT))
                .andExpect(model().attribute(MainController.MODEL_ON_EDIT, any(Boolean.class)))
                .andExpect(model().attribute(MainController.MODEL_ON_EDIT, notNullValue()))
                .andExpect(model().attribute(MainController.MODEL_ON_EDIT, false))

                .andExpect(model().attributeExists(MainController.MODEL_TITLE))
                .andExpect(model().attribute(MainController.MODEL_TITLE, any(String.class)))
                .andExpect(model().attribute(MainController.MODEL_TITLE, notNullValue()))
                .andExpect(model().attribute(MainController.MODEL_TITLE, equalTo("New audio:")))

                .andExpect(model().attributeExists(MainController.MODEL_FORM_HREF))
                .andExpect(model().attribute(MainController.MODEL_FORM_HREF, any(String.class)))
                .andExpect(model().attribute(MainController.MODEL_FORM_HREF, notNullValue()))
                .andExpect(model().attribute(MainController.MODEL_FORM_HREF, equalTo("/resources/audios/new")))

                .andExpect(model().attributeExists(ImageController.MODEL_IMAGES))
                .andExpect(model().attribute(ImageController.MODEL_IMAGES, any(List.class)))
                .andExpect(model().attribute(ImageController.MODEL_IMAGES, everyItem(any(Image.class))))
                .andExpect(model().attribute(ImageController.MODEL_IMAGES, notNullValue()))

                .andExpect(model().attributeDoesNotExist(MainController.MODEL_ERROR))

                .andExpect(view().name(AudioController.VIEW_AUDIO_FORM));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void editAudioForm() throws Exception {
        mockMvc
                .perform(get(MainController.CTRL_RESOURCES + AudioController.CTRL_AUDIOS + "/3" + MainController.CTRL_EDIT)
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
                .andExpect(model().attribute(MainController.MODEL_MENU_ENTITY, equalTo(MenuEntity.AUDIO)))

                .andExpect(model().attributeExists(AudioController.MODEL_AUDIO))
                .andExpect(model().attribute(AudioController.MODEL_AUDIO, any(Audio.class)))
                .andExpect(model().attribute(AudioController.MODEL_AUDIO, notNullValue()))

                .andExpect(model().attributeExists(MainController.MODEL_ON_EDIT))
                .andExpect(model().attribute(MainController.MODEL_ON_EDIT, any(Boolean.class)))
                .andExpect(model().attribute(MainController.MODEL_ON_EDIT, notNullValue()))
                .andExpect(model().attribute(MainController.MODEL_ON_EDIT, true))

                .andExpect(model().attributeExists(MainController.MODEL_TITLE))
                .andExpect(model().attribute(MainController.MODEL_TITLE, any(String.class)))
                .andExpect(model().attribute(MainController.MODEL_TITLE, notNullValue()))
                .andExpect(model().attribute(MainController.MODEL_TITLE, equalTo("Edit audio:")))

                .andExpect(model().attributeExists(MainController.MODEL_FORM_HREF))
                .andExpect(model().attribute(MainController.MODEL_FORM_HREF, any(String.class)))
                .andExpect(model().attribute(MainController.MODEL_FORM_HREF, notNullValue()))
                .andExpect(model().attribute(MainController.MODEL_FORM_HREF, equalTo("/resources/audios/3/edit")))

                .andExpect(model().attributeDoesNotExist(MainController.MODEL_ERROR))

                .andExpect(view().name(AudioController.VIEW_AUDIO_FORM));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void editAudio() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(audioController).build();

        when(audioService.getAudioByTitle(Mockito.anyString())).thenReturn(null);
        when(audioService.getAudioById(Mockito.anyLong())).thenReturn(audio);
        when(audioService.renameFile(Mockito.anyString(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(false);

        mockMvc
                .perform(post(MainController.CTRL_RESOURCES + AudioController.CTRL_AUDIOS
                        + "/3" + MainController.CTRL_EDIT)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("title", text.getTitle())
                        .param("fileName", text.getFileName())
                        .param("image.id", "2")
                        .param("image.displayPanels[0].id", "2"))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is3xxRedirection())
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(status().isFound())

                .andExpect(model().size(0))
                .andExpect(model().hasNoErrors())

                .andExpect(view().name("redirect:" + MainController.CTRL_RESOURCES
                        + AudioController.CTRL_AUDIOS + "/3" + MainController.CTRL_EDIT + "?error=error occured while renaming the audio file"))
                .andExpect(redirectedUrl(MainController.CTRL_RESOURCES
                        + AudioController.CTRL_AUDIOS + "/3" + MainController.CTRL_EDIT + "?error=error occured while renaming the audio file"));


        when(audioService.getAudioByTitle(Mockito.anyString())).thenReturn(audio);

        mockMvc
                .perform(post(MainController.CTRL_RESOURCES + AudioController.CTRL_AUDIOS
                        + "/4" + MainController.CTRL_EDIT)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("title", text.getTitle())
                        .param("fileName", text.getFileName())
                        .param("image.id", "2")
                        .param("image.displayPanels[0].id", "2"))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is3xxRedirection())
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(status().isFound())

                .andExpect(model().size(0))
                .andExpect(model().hasNoErrors())

                .andExpect(view().name("redirect:" + MainController.CTRL_RESOURCES
                        + AudioController.CTRL_AUDIOS + "/4" + MainController.CTRL_EDIT + "?error=an audio with this title already exists"))
                .andExpect(redirectedUrl(MainController.CTRL_RESOURCES
                        + AudioController.CTRL_AUDIOS + "/4" + MainController.CTRL_EDIT + "?error=an audio with this title already exists"));


        when(audioService.getAudioByTitle(Mockito.anyString())).thenReturn(null);
        when(audioService.getAudioById(Mockito.anyLong())).thenReturn(audio);
        when(audioService.renameFile(Mockito.anyString(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);

        mockMvc
                .perform(post(MainController.CTRL_RESOURCES + AudioController.CTRL_AUDIOS
                        + "/3" + MainController.CTRL_EDIT)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("title", text.getTitle())
                        .param("fileName", text.getFileName())
                        .param("image.id", "2")
                        .param("image.displayPanels[0].id", "2"))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is3xxRedirection())
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(status().isFound())

                .andExpect(model().size(0))
                .andExpect(model().hasNoErrors())

                .andExpect(view().name("redirect:" + MainController.CTRL_RESOURCES
                        + AudioController.CTRL_AUDIOS))
                .andExpect(redirectedUrl(MainController.CTRL_RESOURCES
                        + AudioController.CTRL_AUDIOS));

        verify(audioService).editAudio(Mockito.any());
    }
}