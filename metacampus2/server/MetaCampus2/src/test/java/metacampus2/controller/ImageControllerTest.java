package metacampus2.controller;

import metacampus2.model.*;
import metacampus2.service.ImageService;
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

class ImageControllerTest extends AbstractControllerTest {
    @InjectMocks
    private ImageController imageController;
    @Mock
    private ImageService imageService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void images() throws Exception {
        mockMvc
                .perform(get(MainController.CTRL_RESOURCES + ImageController.CTRL_IMAGES)
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
                .andExpect(model().attribute(MainController.MODEL_MENU_ENTITY, equalTo(MenuEntity.IMAGE)))

                .andExpect(model().attributeExists(ImageController.MODEL_IMAGES))
                .andExpect(model().attribute(ImageController.MODEL_IMAGES, any(List.class)))
                .andExpect(model().attribute(ImageController.MODEL_IMAGES, everyItem(any(Image.class))))
                .andExpect(model().attribute(ImageController.MODEL_IMAGES, notNullValue()))

                .andExpect(view().name(ImageController.VIEW_IMAGES));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void newImageForm() throws Exception {
        mockMvc
                .perform(get(MainController.CTRL_RESOURCES + ImageController.CTRL_IMAGES + MainController.CTRL_NEW)
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
                .andExpect(model().attribute(MainController.MODEL_MENU_ENTITY, equalTo(MenuEntity.IMAGE)))

                .andExpect(model().attributeExists(ImageController.MODEL_IMAGE))
                .andExpect(model().attribute(ImageController.MODEL_IMAGE, any(Image.class)))
                .andExpect(model().attribute(ImageController.MODEL_IMAGE, notNullValue()))

                .andExpect(model().attributeExists(MainController.MODEL_ON_EDIT))
                .andExpect(model().attribute(MainController.MODEL_ON_EDIT, any(Boolean.class)))
                .andExpect(model().attribute(MainController.MODEL_ON_EDIT, notNullValue()))
                .andExpect(model().attribute(MainController.MODEL_ON_EDIT, false))

                .andExpect(model().attributeExists(MainController.MODEL_TITLE))
                .andExpect(model().attribute(MainController.MODEL_TITLE, any(String.class)))
                .andExpect(model().attribute(MainController.MODEL_TITLE, notNullValue()))
                .andExpect(model().attribute(MainController.MODEL_TITLE, equalTo("New image:")))

                .andExpect(model().attributeExists(MainController.MODEL_FORM_HREF))
                .andExpect(model().attribute(MainController.MODEL_FORM_HREF, any(String.class)))
                .andExpect(model().attribute(MainController.MODEL_FORM_HREF, notNullValue()))
                .andExpect(model().attribute(MainController.MODEL_FORM_HREF, equalTo("/resources/images/new")))

                .andExpect(model().attributeExists(DisplayPanelController.MODEL_DISPLAY_PANELS))
                .andExpect(model().attribute(DisplayPanelController.MODEL_DISPLAY_PANELS, any(List.class)))
                .andExpect(model().attribute(DisplayPanelController.MODEL_DISPLAY_PANELS, everyItem(any(DisplayPanel.class))))
                .andExpect(model().attribute(DisplayPanelController.MODEL_DISPLAY_PANELS, notNullValue()))

                .andExpect(model().attributeDoesNotExist(MainController.MODEL_ERROR))

                .andExpect(view().name(ImageController.VIEW_IMAGE_FORM));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void editImageForm() throws Exception {
        mockMvc
                .perform(get(MainController.CTRL_RESOURCES + ImageController.CTRL_IMAGES
                        + "/2" + MainController.CTRL_EDIT)
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
                .andExpect(model().attribute(MainController.MODEL_MENU_ENTITY, equalTo(MenuEntity.IMAGE)))

                .andExpect(model().attributeExists(ImageController.MODEL_IMAGE))
                .andExpect(model().attribute(ImageController.MODEL_IMAGE, any(Image.class)))
                .andExpect(model().attribute(ImageController.MODEL_IMAGE, notNullValue()))

                .andExpect(model().attributeExists(MainController.MODEL_ON_EDIT))
                .andExpect(model().attribute(MainController.MODEL_ON_EDIT, any(Boolean.class)))
                .andExpect(model().attribute(MainController.MODEL_ON_EDIT, notNullValue()))
                .andExpect(model().attribute(MainController.MODEL_ON_EDIT, true))

                .andExpect(model().attributeExists(MainController.MODEL_TITLE))
                .andExpect(model().attribute(MainController.MODEL_TITLE, any(String.class)))
                .andExpect(model().attribute(MainController.MODEL_TITLE, notNullValue()))
                .andExpect(model().attribute(MainController.MODEL_TITLE, equalTo("Edit image:")))

                .andExpect(model().attributeExists(MainController.MODEL_FORM_HREF))
                .andExpect(model().attribute(MainController.MODEL_FORM_HREF, any(String.class)))
                .andExpect(model().attribute(MainController.MODEL_FORM_HREF, notNullValue()))
                .andExpect(model().attribute(MainController.MODEL_FORM_HREF, equalTo("/resources/images/2/edit")))

                .andExpect(model().attributeDoesNotExist(MainController.MODEL_ERROR))

                .andExpect(view().name(ImageController.VIEW_IMAGE_FORM));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void editImage() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(imageController).build();

        when(imageService.getImageByTitle(Mockito.anyString())).thenReturn(null);
        when(imageService.getImageById(Mockito.anyLong())).thenReturn(image);
        when(imageService.renameFile(Mockito.anyString(), Mockito.any(), Mockito.any())).thenReturn(false);

        mockMvc
                .perform(post(MainController.CTRL_RESOURCES + ImageController.CTRL_IMAGES
                        + "/2" + MainController.CTRL_EDIT)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("title", image.getTitle())
                        .param("fileName", image.getFileName())
                        .param("displayPanels[0].id", "2"))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is3xxRedirection())
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(status().isFound())

                .andExpect(model().size(0))
                .andExpect(model().hasNoErrors())

                .andExpect(view().name("redirect:" + MainController.CTRL_RESOURCES
                        + ImageController.CTRL_IMAGES + "/2" + MainController.CTRL_EDIT + "?error=error occured while renaming the image file"))
                .andExpect(redirectedUrl(MainController.CTRL_RESOURCES
                        + ImageController.CTRL_IMAGES + "/2" + MainController.CTRL_EDIT + "?error=error occured while renaming the image file"));


        when(imageService.getImageByTitle(Mockito.anyString())).thenReturn(image);

        mockMvc
                .perform(post(MainController.CTRL_RESOURCES + ImageController.CTRL_IMAGES
                        + "/3" + MainController.CTRL_EDIT)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("title", image.getTitle())
                        .param("fileName", image.getFileName())
                        .param("displayPanels[0].id", "2"))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is3xxRedirection())
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(status().isFound())

                .andExpect(model().size(0))
                .andExpect(model().hasNoErrors())

                .andExpect(view().name("redirect:" + MainController.CTRL_RESOURCES
                        + ImageController.CTRL_IMAGES + "/3" + MainController.CTRL_EDIT + "?error=an image with this title already exists"))
                .andExpect(redirectedUrl(MainController.CTRL_RESOURCES
                        + ImageController.CTRL_IMAGES + "/3" + MainController.CTRL_EDIT + "?error=an image with this title already exists"));


        when(imageService.getImageByTitle(Mockito.anyString())).thenReturn(null);
        when(imageService.getImageById(Mockito.anyLong())).thenReturn(image);
        when(imageService.renameFile(Mockito.anyString(), Mockito.any(), Mockito.any())).thenReturn(true);

        mockMvc
                .perform(post(MainController.CTRL_RESOURCES + ImageController.CTRL_IMAGES
                        + "/2" + MainController.CTRL_EDIT)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("title", image.getTitle())
                        .param("fileName", image.getFileName())
                        .param("displayPanels[0].id", "2"))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is3xxRedirection())
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(status().isFound())

                .andExpect(model().size(0))
                .andExpect(model().hasNoErrors())

                .andExpect(view().name("redirect:" + MainController.CTRL_RESOURCES
                        + ImageController.CTRL_IMAGES))
                .andExpect(redirectedUrl(MainController.CTRL_RESOURCES
                        + ImageController.CTRL_IMAGES));

        verify(imageService).editImage(Mockito.any());
    }
}