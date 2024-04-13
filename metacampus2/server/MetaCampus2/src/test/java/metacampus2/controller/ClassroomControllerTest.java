/*
package metacampus2.controller;

import metacampus2.model.Classroom;
import metacampus2.model.MenuItem;
import metacampus2.model.Metaverse;
import metacampus2.service.ClassroomService;
import metacampus2.service.MetaverseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class ClassroomControllerTest extends AbstractControllerTest {
    public static final String METAVERSE_NAME = "/Campus Est SUPSI";

    @Mock
    private ClassroomService classroomService;
    @Mock
    private MetaverseService metaverseService;
    @InjectMocks
    private ClassroomController classroomController;

    Metaverse metaverse;
    Classroom classroom;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        metaverse = new Metaverse();
        metaverse.setName("Campus Est SUPSI");

        classroom = new Classroom();
        classroom.setNumber("1");
        classroom.setMetaverse(metaverse);
    }

    @Test
    void classrooms() throws Exception {
        mockMvc
                .perform(get(ClassroomController.CTRL_CLASSROOMS)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.TEXT_PLAIN))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is2xxSuccessful())
                .andExpect(status().is(HttpStatus.OK.value()))

                .andExpect(model().size(3))
                .andExpect(model().hasNoErrors())

                .andExpect(model().attributeExists(MainController.MODEL_PROJECT_NAME))
                .andExpect(model().attribute(MainController.MODEL_PROJECT_NAME, any(String.class)))
                .andExpect(model().attribute(MainController.MODEL_PROJECT_NAME, notNullValue()))
                .andExpect(model().attribute(MainController.MODEL_PROJECT_NAME, equalTo("MetaCampus")))

                .andExpect(model().attributeExists(MainController.MODEL_MENU_ITEM))
                .andExpect(model().attribute(MainController.MODEL_MENU_ITEM, any(MenuItem.class)))
                .andExpect(model().attribute(MainController.MODEL_MENU_ITEM, notNullValue()))
                .andExpect(model().attribute(MainController.MODEL_MENU_ITEM, equalTo(MenuItem.CLASSROOMS)))

                .andExpect(model().attributeExists(ClassroomController.MODEL_CLASSROOMS))
                .andExpect(model().attribute(ClassroomController.MODEL_CLASSROOMS, any(List.class)))
                .andExpect(model().attribute(ClassroomController.MODEL_CLASSROOMS, everyItem(any(Classroom.class))))
                .andExpect(model().attribute(ClassroomController.MODEL_CLASSROOMS, notNullValue()))

                .andExpect(view().name(ClassroomController.VIEW_CLASSROOMS));
    }

    @Test
    void classroomsEntities() throws Exception {
        mockMvc
                .perform(get(METAVERSE_NAME + ClassroomController.CTRL_CLASSROOMS)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.TEXT_PLAIN))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is2xxSuccessful())
                .andExpect(status().is(HttpStatus.OK.value()))

                .andExpect(model().size(0))
                .andExpect(model().hasNoErrors())

                ;

    }

    @Test
    void classroomForm() throws Exception {
        mockMvc
                .perform(get(ClassroomController.CTRL_CLASSROOMS + MainController.CTRL_NEW)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.TEXT_PLAIN))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is2xxSuccessful())
                .andExpect(status().is(HttpStatus.OK.value()))

                .andExpect(model().size(4))
                .andExpect(model().hasNoErrors())

                .andExpect(model().attributeExists(MainController.MODEL_PROJECT_NAME))
                .andExpect(model().attribute(MainController.MODEL_PROJECT_NAME, any(String.class)))
                .andExpect(model().attribute(MainController.MODEL_PROJECT_NAME, notNullValue()))
                .andExpect(model().attribute(MainController.MODEL_PROJECT_NAME, equalTo("MetaCampus")))

                .andExpect(model().attributeExists(MainController.MODEL_MENU_ITEM))
                .andExpect(model().attribute(MainController.MODEL_MENU_ITEM, any(MenuItem.class)))
                .andExpect(model().attribute(MainController.MODEL_MENU_ITEM, notNullValue()))
                .andExpect(model().attribute(MainController.MODEL_MENU_ITEM, equalTo(MenuItem.CLASSROOMS)))

                .andExpect(model().attributeExists(MainController.MODEL_METAVERSES))
                .andExpect(model().attribute(MainController.MODEL_METAVERSES, any(List.class)))
                .andExpect(model().attribute(MainController.MODEL_METAVERSES, everyItem(any(Metaverse.class))))
                .andExpect(model().attribute(MainController.MODEL_METAVERSES, notNullValue()))

                .andExpect(model().attributeDoesNotExist(MainController.MODEL_ERROR))

                .andExpect(view().name(ClassroomController.VIEW_CLASSROOM_FORM));
    }

    @Test
    void classroomFormWithErrorParam() throws Exception {
        mockMvc
                .perform(get(ClassroomController.CTRL_CLASSROOMS + MainController.CTRL_NEW, "error")
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.TEXT_PLAIN)
                        .param("error", ""))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is2xxSuccessful())
                .andExpect(status().is(HttpStatus.OK.value()))

                .andExpect(model().size(4))
                .andExpect(model().hasNoErrors())

                .andExpect(model().attributeExists(MainController.MODEL_PROJECT_NAME))
                .andExpect(model().attribute(MainController.MODEL_PROJECT_NAME, any(String.class)))
                .andExpect(model().attribute(MainController.MODEL_PROJECT_NAME, notNullValue()))
                .andExpect(model().attribute(MainController.MODEL_PROJECT_NAME, equalTo("MetaCampus")))

                .andExpect(model().attributeExists(MainController.MODEL_MENU_ITEM))
                .andExpect(model().attribute(MainController.MODEL_MENU_ITEM, any(MenuItem.class)))
                .andExpect(model().attribute(MainController.MODEL_MENU_ITEM, notNullValue()))
                .andExpect(model().attribute(MainController.MODEL_MENU_ITEM, equalTo(MenuItem.CLASSROOMS)))

                .andExpect(model().attributeExists(MainController.MODEL_METAVERSES))
                .andExpect(model().attribute(MainController.MODEL_METAVERSES, any(List.class)))
                .andExpect(model().attribute(MainController.MODEL_METAVERSES, everyItem(any(Metaverse.class))))
                .andExpect(model().attribute(MainController.MODEL_METAVERSES, notNullValue()))

                .andExpect(model().attributeExists(MainController.MODEL_ERROR))
                .andExpect(model().attribute(MainController.MODEL_ERROR, any(String.class)))
                .andExpect(model().attribute(MainController.MODEL_ERROR, notNullValue()))
                .andExpect(model().attribute(MainController.MODEL_ERROR, equalTo("")))

                .andExpect(view().name(ClassroomController.VIEW_CLASSROOM_FORM));
    }

    @Test
    void classroom() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(classroomController).build();

        when(classroomService.getClassroomFromMetaverse(Mockito.any(), Mockito.any()))
                .thenReturn(null);

        mockMvc
                .perform(post(ClassroomController.CTRL_CLASSROOMS + MainController.CTRL_NEW)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.TEXT_PLAIN)
                        .param("number", classroom.getNumber())
                        .param("metaverse.name", classroom.getMetaverse().getName()))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is3xxRedirection())
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(status().isFound())

                .andExpect(model().size(0))
                .andExpect(model().hasNoErrors())

                .andExpect(view().name("redirect:" + ClassroomController.CTRL_CLASSROOMS))
                .andExpect(redirectedUrl(ClassroomController.CTRL_CLASSROOMS));
    }

    @Test
    void classroomNotCreated() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(classroomController).build();

        when(classroomService.getClassroomFromMetaverse(Mockito.any(), Mockito.any()))
                .thenReturn(classroom);

        mockMvc
                .perform(post(ClassroomController.CTRL_CLASSROOMS + MainController.CTRL_NEW)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.TEXT_PLAIN)
                        .param("number", classroom.getNumber())
                        .param("metaverse.name", classroom.getMetaverse().getName()))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is3xxRedirection())
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(status().isFound())

                .andExpect(model().size(0))
                .andExpect(model().hasNoErrors())

                .andExpect(view().name("redirect:" + ClassroomController.CTRL_CLASSROOMS
                        + MainController.CTRL_NEW + "?error"))
                .andExpect(redirectedUrl(ClassroomController.CTRL_CLASSROOMS + MainController.CTRL_NEW + "?error"));
    }
}*/
