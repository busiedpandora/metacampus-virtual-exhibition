package metacampus2.controller;

import metacampus2.model.User;
import metacampus2.model.UserRole;
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
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UserControllerTest extends AbstractControllerTest {
    @InjectMocks
    private UserController userController;
    @Mock
    private IUserService userService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loginPage() throws Exception {
        mockMvc
                .perform(get(UserController.CTRL_LOGIN)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.TEXT_PLAIN))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is2xxSuccessful())
                .andExpect(status().is(HttpStatus.OK.value()))

                .andExpect(model().hasNoErrors())

                .andExpect(model().attributeDoesNotExist(MainController.MODEL_ERROR))

                .andExpect(view().name(UserController.VIEW_LOGIN));
    }

    @Test
    void registrationPage() throws Exception {
        mockMvc
                .perform(get(UserController.CTRL_REGISTER)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.TEXT_PLAIN))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is2xxSuccessful())
                .andExpect(status().is(HttpStatus.OK.value()))

                .andExpect(model().hasNoErrors())

                .andExpect(model().attributeExists(UserController.MODEL_USER))
                .andExpect(model().attribute(UserController.MODEL_USER, any(User.class)))
                .andExpect(model().attribute(UserController.MODEL_USER, notNullValue()))

                .andExpect(model().attributeDoesNotExist(MainController.MODEL_ERROR))

                .andExpect(view().name(UserController.VIEW_REGISTRATION));
    }

    @Test
    void registration() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        when(userService.getUser(Mockito.anyString())).thenReturn(new User());

        mockMvc
                .perform(post(UserController.CTRL_REGISTER)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.TEXT_PLAIN)
                        .param("username", "username"))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is3xxRedirection())
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(status().isFound())

                .andExpect(model().size(0))
                .andExpect(model().hasNoErrors())

                .andExpect(view().name("redirect:" + UserController.CTRL_REGISTER + "?error"))
                .andExpect(redirectedUrl(UserController.CTRL_REGISTER + "?error"));

        when(userService.getUser(Mockito.anyString())).thenReturn(null);

        mockMvc
                .perform(post(UserController.CTRL_REGISTER)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.TEXT_PLAIN)
                        .param("username", "username"))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is3xxRedirection())
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(status().isFound())

                .andExpect(model().size(0))
                .andExpect(model().hasNoErrors())

                .andExpect(view().name("redirect:" + UserController.CTRL_LOGIN))
                .andExpect(redirectedUrl(UserController.CTRL_LOGIN));

        verify(userService).addUser(Mockito.any());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void manageUsers() throws Exception {
        mockMvc
                .perform(get(UserController.CTRL_USERS)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.TEXT_PLAIN))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is2xxSuccessful())
                .andExpect(status().is(HttpStatus.OK.value()))

                .andExpect(model().hasNoErrors())

                .andExpect(model().attributeExists(UserController.MODEL_USERS))
                .andExpect(model().attribute(UserController.MODEL_USERS, any(List.class)))
                .andExpect(model().attribute(UserController.MODEL_USERS, everyItem(any(User.class))))
                .andExpect(model().attribute(UserController.MODEL_USERS, notNullValue()))

                .andExpect(model().attributeExists(UserController.MODEL_USER_ROLES))
                .andExpect(model().attribute(UserController.MODEL_USER_ROLES, any(UserRole[].class)))
                .andExpect(model().attribute(UserController.MODEL_USER_ROLES, notNullValue()))

                .andExpect(model().attributeDoesNotExist(MainController.MODEL_ERROR))

                .andExpect(view().name(UserController.VIEW_MANAGE_USERS));
    }
}