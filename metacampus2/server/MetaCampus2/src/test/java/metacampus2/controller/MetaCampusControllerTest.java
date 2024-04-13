/*
package metacampus2.controller;

import metacampus2.model.*;
import metacampus2.service.ClassroomService;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class MetaCampusControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Mock
    private ClassroomService classroomService;


    @Test
    void homepage1() throws Exception {
        mockMvc
                .perform(get("/")
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.TEXT_PLAIN))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is2xxSuccessful())
                .andExpect(status().is(HttpStatus.OK.value()))

                .andExpect(model().size(8))
                .andExpect(model().hasNoErrors())

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_PROJECT_NAME))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PROJECT_NAME, any(String.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PROJECT_NAME, notNullValue()))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PROJECT_NAME, equalTo("MetaCampus")))

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_MENU_ITEM))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_MENU_ITEM, any(MenuItem.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_MENU_ITEM, notNullValue()))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_MENU_ITEM, equalTo(MenuItem.HOME)))

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_CLASSROOMS))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_CLASSROOMS, any(List.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_CLASSROOMS, everyItem(any(Classroom.class))))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_CLASSROOMS, notNullValue()))

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_EVENTS))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_EVENTS, any(List.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_EVENTS, everyItem(any(Event.class))))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_EVENTS, notNullValue()))

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_LECTURES))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_LECTURES, any(List.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_LECTURES, everyItem(any(Lecture.class))))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_LECTURES, notNullValue()))

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_OFFICES))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_OFFICES, any(List.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_OFFICES, everyItem(any(Office.class))))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_OFFICES, notNullValue()))

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_PEOPLE))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PEOPLE, any(List.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PEOPLE, everyItem(any(Person.class))))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PEOPLE, notNullValue()))

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_METAVERSES))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_METAVERSES, any(List.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_METAVERSES, everyItem(any(Metaverse.class))))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_METAVERSES, notNullValue()))

                .andExpect(view().name(MetaCampusControllerOld.VIEW_HOME));
    }

    @Test
    void homepage2() throws Exception {
        mockMvc
                .perform(get(MetaCampusControllerOld.CTRL_HOME)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.TEXT_PLAIN))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is2xxSuccessful())
                .andExpect(status().is(HttpStatus.OK.value()))

                .andExpect(model().size(8))
                .andExpect(model().hasNoErrors())

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_PROJECT_NAME))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PROJECT_NAME, any(String.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PROJECT_NAME, notNullValue()))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PROJECT_NAME, equalTo("MetaCampus")))

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_MENU_ITEM))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_MENU_ITEM, any(MenuItem.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_MENU_ITEM, notNullValue()))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_MENU_ITEM, equalTo(MenuItem.HOME)))

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_CLASSROOMS))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_CLASSROOMS, any(List.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_CLASSROOMS, everyItem(any(Classroom.class))))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_CLASSROOMS, notNullValue()))

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_EVENTS))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_EVENTS, any(List.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_EVENTS, everyItem(any(Event.class))))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_EVENTS, notNullValue()))

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_LECTURES))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_LECTURES, any(List.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_LECTURES, everyItem(any(Lecture.class))))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_LECTURES, notNullValue()))

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_OFFICES))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_OFFICES, any(List.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_OFFICES, everyItem(any(Office.class))))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_OFFICES, notNullValue()))

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_PEOPLE))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PEOPLE, any(List.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PEOPLE, everyItem(any(Person.class))))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PEOPLE, notNullValue()))

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_METAVERSES))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_METAVERSES, any(List.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_METAVERSES, everyItem(any(Metaverse.class))))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_METAVERSES, notNullValue()))

                .andExpect(view().name(MetaCampusControllerOld.VIEW_HOME));
    }

    @Test
    void classrooms() throws Exception {
        mockMvc
                .perform(get(MetaCampusControllerOld.CTRL_CLASSROOMS)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.TEXT_PLAIN))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is2xxSuccessful())
                .andExpect(status().is(HttpStatus.OK.value()))

                .andExpect(model().size(3))
                .andExpect(model().hasNoErrors())

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_PROJECT_NAME))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PROJECT_NAME, any(String.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PROJECT_NAME, notNullValue()))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PROJECT_NAME, equalTo("MetaCampus")))

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_MENU_ITEM))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_MENU_ITEM, any(MenuItem.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_MENU_ITEM, notNullValue()))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_MENU_ITEM, equalTo(MenuItem.CLASSROOMS)))

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_CLASSROOMS))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_CLASSROOMS, any(List.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_CLASSROOMS, everyItem(any(Classroom.class))))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_CLASSROOMS, notNullValue()))

                .andExpect(view().name(MetaCampusControllerOld.VIEW_CLASSROOMS));
    }

    @Test
    void classroomForm() throws Exception {
        mockMvc
                .perform(get(MetaCampusControllerOld.CTRL_CLASSROOMS + MetaCampusControllerOld.CTRL_NEW)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.TEXT_PLAIN))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is2xxSuccessful())
                .andExpect(status().is(HttpStatus.OK.value()))

                .andExpect(model().size(4))
                .andExpect(model().hasNoErrors())

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_PROJECT_NAME))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PROJECT_NAME, any(String.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PROJECT_NAME, notNullValue()))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PROJECT_NAME, equalTo("MetaCampus")))

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_MENU_ITEM))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_MENU_ITEM, any(MenuItem.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_MENU_ITEM, notNullValue()))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_MENU_ITEM, equalTo(MenuItem.CLASSROOMS)))

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_METAVERSES))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_METAVERSES, any(List.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_METAVERSES, everyItem(any(Metaverse.class))))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_METAVERSES, notNullValue()))

                .andExpect(model().attributeDoesNotExist(MetaCampusControllerOld.MODEL_ERROR))

                .andExpect(view().name(MetaCampusControllerOld.VIEW_CLASSROOM_FORM));
    }

    @Test
    void classroomFormWithErrorParam() throws Exception {
        mockMvc
                .perform(get(MetaCampusControllerOld.CTRL_CLASSROOMS + MetaCampusControllerOld.CTRL_NEW, "error")
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.TEXT_PLAIN)
                        .param("error", ""))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is2xxSuccessful())
                .andExpect(status().is(HttpStatus.OK.value()))

                .andExpect(model().size(4))
                .andExpect(model().hasNoErrors())

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_PROJECT_NAME))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PROJECT_NAME, any(String.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PROJECT_NAME, notNullValue()))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PROJECT_NAME, equalTo("MetaCampus")))

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_MENU_ITEM))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_MENU_ITEM, any(MenuItem.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_MENU_ITEM, notNullValue()))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_MENU_ITEM, equalTo(MenuItem.CLASSROOMS)))

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_METAVERSES))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_METAVERSES, any(List.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_METAVERSES, everyItem(any(Metaverse.class))))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_METAVERSES, notNullValue()))

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_ERROR))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_ERROR, any(String.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_ERROR, notNullValue()))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_ERROR, equalTo("")))

                .andExpect(view().name(MetaCampusControllerOld.VIEW_CLASSROOM_FORM));
    }

    @Test
    void classroom() throws Exception {
        when(classroomService.getClassroomFromMetaverse(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(null);

        mockMvc
                .perform(post(MetaCampusControllerOld.CTRL_CLASSROOMS + MetaCampusControllerOld.CTRL_NEW)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.TEXT_PLAIN))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is3xxRedirection())
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(status().isFound())

                .andExpect(model().size(0))
                .andExpect(model().hasNoErrors());

    }

    @Test
    void events() throws Exception {
        mockMvc
                .perform(get(MetaCampusControllerOld.CTRL_EVENTS)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.TEXT_PLAIN))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is2xxSuccessful())
                .andExpect(status().is(HttpStatus.OK.value()))

                .andExpect(model().size(3))
                .andExpect(model().hasNoErrors())

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_PROJECT_NAME))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PROJECT_NAME, any(String.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PROJECT_NAME, notNullValue()))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PROJECT_NAME, equalTo("MetaCampus")))

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_MENU_ITEM))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_MENU_ITEM, any(MenuItem.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_MENU_ITEM, notNullValue()))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_MENU_ITEM, equalTo(MenuItem.EVENTS)))

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_EVENTS))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_EVENTS, any(List.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_EVENTS, everyItem(any(Event.class))))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_EVENTS, notNullValue()))

                .andExpect(view().name(MetaCampusControllerOld.VIEW_EVENTS));
    }

    @Test
    void eventForm() throws Exception {
        mockMvc
                .perform(get(MetaCampusControllerOld.CTRL_EVENTS + MetaCampusControllerOld.CTRL_NEW)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.TEXT_PLAIN))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is2xxSuccessful())
                .andExpect(status().is(HttpStatus.OK.value()))

                .andExpect(model().size(4))
                .andExpect(model().hasNoErrors())

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_PROJECT_NAME))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PROJECT_NAME, any(String.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PROJECT_NAME, notNullValue()))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PROJECT_NAME, equalTo("MetaCampus")))

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_MENU_ITEM))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_MENU_ITEM, any(MenuItem.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_MENU_ITEM, notNullValue()))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_MENU_ITEM, equalTo(MenuItem.EVENTS)))

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_METAVERSES))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_METAVERSES, any(List.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_METAVERSES, everyItem(any(Metaverse.class))))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_METAVERSES, notNullValue()))

                .andExpect(model().attributeDoesNotExist(MetaCampusControllerOld.MODEL_ERROR))

                .andExpect(view().name(MetaCampusControllerOld.VIEW_EVENT_FORM));
    }

    @Test
    void eventFormWithErrorParam() throws Exception {
        mockMvc
                .perform(get(MetaCampusControllerOld.CTRL_EVENTS + MetaCampusControllerOld.CTRL_NEW)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.TEXT_PLAIN)
                        .param("error", ""))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is2xxSuccessful())
                .andExpect(status().is(HttpStatus.OK.value()))

                .andExpect(model().size(4))
                .andExpect(model().hasNoErrors())

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_PROJECT_NAME))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PROJECT_NAME, any(String.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PROJECT_NAME, notNullValue()))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PROJECT_NAME, equalTo("MetaCampus")))

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_MENU_ITEM))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_MENU_ITEM, any(MenuItem.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_MENU_ITEM, notNullValue()))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_MENU_ITEM, equalTo(MenuItem.EVENTS)))

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_METAVERSES))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_METAVERSES, any(List.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_METAVERSES, everyItem(any(Metaverse.class))))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_METAVERSES, notNullValue()))

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_ERROR))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_ERROR, any(String.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_ERROR, notNullValue()))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_ERROR, equalTo("")))

                .andExpect(view().name(MetaCampusControllerOld.VIEW_EVENT_FORM));
    }

    @Test
    void event() {
    }

    @Test
    void lectures() throws Exception {
        mockMvc
                .perform(get(MetaCampusControllerOld.CTRL_LECTURES)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.TEXT_PLAIN))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is2xxSuccessful())
                .andExpect(status().is(HttpStatus.OK.value()))

                .andExpect(model().size(3))
                .andExpect(model().hasNoErrors())

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_PROJECT_NAME))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PROJECT_NAME, any(String.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PROJECT_NAME, notNullValue()))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PROJECT_NAME, equalTo("MetaCampus")))

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_MENU_ITEM))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_MENU_ITEM, any(MenuItem.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_MENU_ITEM, notNullValue()))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_MENU_ITEM, equalTo(MenuItem.LECTURES)))

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_LECTURES))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_LECTURES, any(List.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_LECTURES, everyItem(any(Lecture.class))))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_LECTURES, notNullValue()))

                .andExpect(view().name(MetaCampusControllerOld.VIEW_LECTURES));
    }

    @Test
    void lectureForm() throws Exception {
        mockMvc
                .perform(get(MetaCampusControllerOld.CTRL_LECTURES + MetaCampusControllerOld.CTRL_NEW)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.TEXT_PLAIN))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is2xxSuccessful())
                .andExpect(status().is(HttpStatus.OK.value()))

                .andExpect(model().size(6))
                .andExpect(model().hasNoErrors())

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_PROJECT_NAME))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PROJECT_NAME, any(String.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PROJECT_NAME, notNullValue()))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PROJECT_NAME, equalTo("MetaCampus")))

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_MENU_ITEM))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_MENU_ITEM, any(MenuItem.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_MENU_ITEM, notNullValue()))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_MENU_ITEM, equalTo(MenuItem.LECTURES)))

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_METAVERSES))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_METAVERSES, any(List.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_METAVERSES, everyItem(any(Metaverse.class))))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_METAVERSES, notNullValue()))

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_CLASSROOMS))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_CLASSROOMS, any(List.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_CLASSROOMS, everyItem(any(Classroom.class))))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_CLASSROOMS, notNullValue()))

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_LECTURERS))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_LECTURERS, any(List.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_LECTURERS, everyItem(any(Person.class))))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_LECTURERS, notNullValue()))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_LECTURERS, new TypeSafeMatcher<List<Person>>() {
                    @Override
                    public void describeTo(Description description) {
                        description.appendText("All persons should have the role of a lecturer.");
                    }

                    @Override
                    protected boolean matchesSafely(List<Person> people) {
                        for (Person person : people) {
                            if (person.getRole() != Role.LECTURER) {
                                return false;
                            }
                        }
                        return true;
                    }
                }))

                .andExpect(model().attributeDoesNotExist(MetaCampusControllerOld.MODEL_ERROR))

                .andExpect(view().name(MetaCampusControllerOld.VIEW_LECTURE_FORM));
    }

    @Test
    void lectureFormWithErrorParam() throws Exception {
        mockMvc
                .perform(get(MetaCampusControllerOld.CTRL_LECTURES + MetaCampusControllerOld.CTRL_NEW)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.TEXT_PLAIN)
                        .param("error", ""))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is2xxSuccessful())
                .andExpect(status().is(HttpStatus.OK.value()))

                .andExpect(model().size(6))
                .andExpect(model().hasNoErrors())

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_PROJECT_NAME))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PROJECT_NAME, any(String.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PROJECT_NAME, notNullValue()))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PROJECT_NAME, equalTo("MetaCampus")))

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_MENU_ITEM))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_MENU_ITEM, any(MenuItem.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_MENU_ITEM, notNullValue()))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_MENU_ITEM, equalTo(MenuItem.LECTURES)))

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_METAVERSES))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_METAVERSES, any(List.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_METAVERSES, everyItem(any(Metaverse.class))))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_METAVERSES, notNullValue()))

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_CLASSROOMS))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_CLASSROOMS, any(List.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_CLASSROOMS, everyItem(any(Classroom.class))))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_CLASSROOMS, notNullValue()))

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_LECTURERS))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_LECTURERS, any(List.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_LECTURERS, everyItem(any(Person.class))))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_LECTURERS, notNullValue()))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_LECTURERS, new TypeSafeMatcher<List<Person>>() {
                    @Override
                    public void describeTo(Description description) {
                        description.appendText("All persons should have the role of a lecturer.");
                    }

                    @Override
                    protected boolean matchesSafely(List<Person> people) {
                        for (Person person : people) {
                            if (person.getRole() != Role.LECTURER) {
                                return false;
                            }
                        }
                        return true;
                    }
                }))

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_ERROR))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_ERROR, any(String.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_ERROR, notNullValue()))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_ERROR, equalTo("")))

                .andExpect(view().name(MetaCampusControllerOld.VIEW_LECTURE_FORM));
    }

    @Test
    void lecture() {
    }

    @Test
    void metaverses() throws Exception {
        mockMvc
                .perform(get(MetaCampusControllerOld.CTRL_METAVERSES)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.TEXT_PLAIN))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is2xxSuccessful())
                .andExpect(status().is(HttpStatus.OK.value()))

                .andExpect(model().size(3))
                .andExpect(model().hasNoErrors())

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_PROJECT_NAME))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PROJECT_NAME, any(String.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PROJECT_NAME, notNullValue()))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PROJECT_NAME, equalTo("MetaCampus")))

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_MENU_ITEM))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_MENU_ITEM, any(MenuItem.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_MENU_ITEM, notNullValue()))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_MENU_ITEM, equalTo(MenuItem.METAVERSES)))

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_METAVERSES))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_METAVERSES, any(List.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_METAVERSES, everyItem(any(Metaverse.class))))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_METAVERSES, notNullValue()))

                .andExpect(view().name(MetaCampusControllerOld.VIEW_METAVERSES));
    }

    @Test
    void metaverseForm() throws Exception {
        mockMvc
                .perform(get(MetaCampusControllerOld.CTRL_METAVERSES + MetaCampusControllerOld.CTRL_NEW)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.TEXT_PLAIN))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is2xxSuccessful())
                .andExpect(status().is(HttpStatus.OK.value()))

                .andExpect(model().size(3))
                .andExpect(model().hasNoErrors())

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_PROJECT_NAME))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PROJECT_NAME, any(String.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PROJECT_NAME, notNullValue()))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PROJECT_NAME, equalTo("MetaCampus")))

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_MENU_ITEM))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_MENU_ITEM, any(MenuItem.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_MENU_ITEM, notNullValue()))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_MENU_ITEM, equalTo(MenuItem.METAVERSES)))

                .andExpect(model().attributeDoesNotExist(MetaCampusControllerOld.MODEL_ERROR))

                .andExpect(view().name(MetaCampusControllerOld.VIEW_METAVERSE_FORM));
    }

    @Test
    void metaverseFormWithErrorParam() throws Exception {
        mockMvc
                .perform(get(MetaCampusControllerOld.CTRL_METAVERSES + MetaCampusControllerOld.CTRL_NEW)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.TEXT_PLAIN)
                        .param("error", ""))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is2xxSuccessful())
                .andExpect(status().is(HttpStatus.OK.value()))

                .andExpect(model().size(3))
                .andExpect(model().hasNoErrors())

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_PROJECT_NAME))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PROJECT_NAME, any(String.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PROJECT_NAME, notNullValue()))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PROJECT_NAME, equalTo("MetaCampus")))

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_MENU_ITEM))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_MENU_ITEM, any(MenuItem.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_MENU_ITEM, notNullValue()))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_MENU_ITEM, equalTo(MenuItem.METAVERSES)))

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_ERROR))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_ERROR, any(String.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_ERROR, notNullValue()))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_ERROR, equalTo("")))

                .andExpect(view().name(MetaCampusControllerOld.VIEW_METAVERSE_FORM));
    }

    @Test
    void metaverse() {

    }

    @Test
    void offices() throws Exception {
        mockMvc
                .perform(get(MetaCampusControllerOld.CTRL_OFFICES)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.TEXT_PLAIN))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is2xxSuccessful())
                .andExpect(status().is(HttpStatus.OK.value()))

                .andExpect(model().size(3))
                .andExpect(model().hasNoErrors())

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_PROJECT_NAME))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PROJECT_NAME, any(String.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PROJECT_NAME, notNullValue()))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PROJECT_NAME, equalTo("MetaCampus")))

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_MENU_ITEM))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_MENU_ITEM, any(MenuItem.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_MENU_ITEM, notNullValue()))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_MENU_ITEM, equalTo(MenuItem.OFFICES)))

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_OFFICES))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_OFFICES, any(List.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_OFFICES, everyItem(any(Office.class))))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_OFFICES, notNullValue()))

                .andExpect(view().name(MetaCampusControllerOld.VIEW_OFFICES));
    }

    @Test
    void officeForm() throws Exception {
        mockMvc
                .perform(get(MetaCampusControllerOld.CTRL_OFFICES + MetaCampusControllerOld.CTRL_NEW)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.TEXT_PLAIN))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is2xxSuccessful())
                .andExpect(status().is(HttpStatus.OK.value()))

                .andExpect(model().size(4))
                .andExpect(model().hasNoErrors())

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_PROJECT_NAME))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PROJECT_NAME, any(String.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PROJECT_NAME, notNullValue()))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PROJECT_NAME, equalTo("MetaCampus")))

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_MENU_ITEM))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_MENU_ITEM, any(MenuItem.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_MENU_ITEM, notNullValue()))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_MENU_ITEM, equalTo(MenuItem.OFFICES)))

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_METAVERSES))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_METAVERSES, any(List.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_METAVERSES, everyItem(any(Metaverse.class))))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_METAVERSES, notNullValue()))

                .andExpect(model().attributeDoesNotExist(MetaCampusControllerOld.MODEL_ERROR))

                .andExpect(view().name(MetaCampusControllerOld.VIEW_OFFICE_FORM));
    }

    @Test
    void officeFormWithErrorParam() throws Exception {
        mockMvc
                .perform(get(MetaCampusControllerOld.CTRL_OFFICES + MetaCampusControllerOld.CTRL_NEW)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.TEXT_PLAIN)
                        .param("error", ""))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is2xxSuccessful())
                .andExpect(status().is(HttpStatus.OK.value()))

                .andExpect(model().size(4))
                .andExpect(model().hasNoErrors())

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_PROJECT_NAME))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PROJECT_NAME, any(String.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PROJECT_NAME, notNullValue()))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PROJECT_NAME, equalTo("MetaCampus")))

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_MENU_ITEM))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_MENU_ITEM, any(MenuItem.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_MENU_ITEM, notNullValue()))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_MENU_ITEM, equalTo(MenuItem.OFFICES)))

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_METAVERSES))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_METAVERSES, any(List.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_METAVERSES, everyItem(any(Metaverse.class))))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_METAVERSES, notNullValue()))

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_ERROR))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_ERROR, any(String.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_ERROR, notNullValue()))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_ERROR, equalTo("")))

                .andExpect(view().name(MetaCampusControllerOld.VIEW_OFFICE_FORM));
    }

    @Test
    void office() {

    }

    @Test
    void people() throws Exception {
        mockMvc
                .perform(get(MetaCampusControllerOld.CTRL_PEOPLE)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.TEXT_PLAIN))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is2xxSuccessful())
                .andExpect(status().is(HttpStatus.OK.value()))

                .andExpect(model().size(3))
                .andExpect(model().hasNoErrors())

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_PROJECT_NAME))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PROJECT_NAME, any(String.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PROJECT_NAME, notNullValue()))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PROJECT_NAME, equalTo("MetaCampus")))

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_MENU_ITEM))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_MENU_ITEM, any(MenuItem.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_MENU_ITEM, notNullValue()))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_MENU_ITEM, equalTo(MenuItem.PEOPLE)))

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_PEOPLE))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PEOPLE, any(List.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PEOPLE, everyItem(any(Person.class))))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PEOPLE, notNullValue()))

                .andExpect(view().name(MetaCampusControllerOld.VIEW_PEOPLE));
    }

    @Test
    void personForm() throws Exception {
        mockMvc
                .perform(get(MetaCampusControllerOld.CTRL_PEOPLE + MetaCampusControllerOld.CTRL_NEW)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.TEXT_PLAIN))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is2xxSuccessful())
                .andExpect(status().is(HttpStatus.OK.value()))

                .andExpect(model().size(6))
                .andExpect(model().hasNoErrors())

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_PROJECT_NAME))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PROJECT_NAME, any(String.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PROJECT_NAME, notNullValue()))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PROJECT_NAME, equalTo("MetaCampus")))

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_MENU_ITEM))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_MENU_ITEM, any(MenuItem.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_MENU_ITEM, notNullValue()))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_MENU_ITEM, equalTo(MenuItem.PEOPLE)))

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_METAVERSES))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_METAVERSES, any(List.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_METAVERSES, everyItem(any(Metaverse.class))))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_METAVERSES, notNullValue()))

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_ROLES))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_ROLES, any(Role[].class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_ROLES, arrayContainingInAnyOrder(Role.values())))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_ROLES, notNullValue()))

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_OFFICES))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_OFFICES, any(List.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_OFFICES, everyItem(any(Office.class))))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_OFFICES, notNullValue()))

                .andExpect(model().attributeDoesNotExist(MetaCampusControllerOld.MODEL_ERROR))

                .andExpect(view().name(MetaCampusControllerOld.VIEW_PERSON_FORM));
    }

    @Test
    void personFormWithErrorParam() throws Exception {
        mockMvc
                .perform(get(MetaCampusControllerOld.CTRL_PEOPLE + MetaCampusControllerOld.CTRL_NEW)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.TEXT_PLAIN)
                        .param("error", ""))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is2xxSuccessful())
                .andExpect(status().is(HttpStatus.OK.value()))

                .andExpect(model().size(6))
                .andExpect(model().hasNoErrors())

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_PROJECT_NAME))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PROJECT_NAME, any(String.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PROJECT_NAME, notNullValue()))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_PROJECT_NAME, equalTo("MetaCampus")))

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_MENU_ITEM))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_MENU_ITEM, any(MenuItem.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_MENU_ITEM, notNullValue()))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_MENU_ITEM, equalTo(MenuItem.PEOPLE)))

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_METAVERSES))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_METAVERSES, any(List.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_METAVERSES, everyItem(any(Metaverse.class))))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_METAVERSES, notNullValue()))

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_ROLES))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_ROLES, any(Role[].class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_ROLES, arrayContainingInAnyOrder(Role.values())))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_ROLES, notNullValue()))

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_OFFICES))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_OFFICES, any(List.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_OFFICES, everyItem(any(Office.class))))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_OFFICES, notNullValue()))

                .andExpect(model().attributeExists(MetaCampusControllerOld.MODEL_ERROR))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_ERROR, any(String.class)))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_ERROR, notNullValue()))
                .andExpect(model().attribute(MetaCampusControllerOld.MODEL_ERROR, equalTo("")))

                .andExpect(view().name(MetaCampusControllerOld.VIEW_PERSON_FORM));
    }

    @Test
    void person() {

    }
}

 */