package metacampus2.controller;

import metacampus2.model.Image;
import metacampus2.model.MenuCategory;
import metacampus2.model.MenuEntity;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class MainControllerTest extends AbstractControllerTest {

    @Test
    @WithMockUser(roles = "ADMIN")
    void addDefaultAttributes() throws Exception {
        mockMvc
                .perform(get(MainController.CTRL_RESOURCES + TextController.CTRL_TEXTS)
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.TEXT_PLAIN))
                .andDo(log())
                .andDo(print())

                .andExpect(status().is2xxSuccessful())
                .andExpect(status().is(HttpStatus.OK.value()))

                .andExpect(model().hasNoErrors())

                .andExpect(model().attributeExists(MainController.MODEL_PROJECT_NAME))
                .andExpect(model().attribute(MainController.MODEL_PROJECT_NAME, any(String.class)))
                .andExpect(model().attribute(MainController.MODEL_PROJECT_NAME, notNullValue()))
                .andExpect(model().attribute(MainController.MODEL_PROJECT_NAME, equalTo("MetaCampus")))

                .andExpect(model().attributeExists("loginUrl"))
                .andExpect(model().attribute("loginUrl", any(String.class)))
                .andExpect(model().attribute("loginUrl", notNullValue()))
                .andExpect(model().attribute("loginUrl", equalTo(UserController.CTRL_LOGIN)))

                .andExpect(model().attributeExists("registerUrl"))
                .andExpect(model().attribute("registerUrl", any(String.class)))
                .andExpect(model().attribute("registerUrl", notNullValue()))
                .andExpect(model().attribute("registerUrl", equalTo(UserController.CTRL_REGISTER)))

                .andExpect(model().attributeExists("logoutUrl"))
                .andExpect(model().attribute("logoutUrl", any(String.class)))
                .andExpect(model().attribute("logoutUrl", notNullValue()))
                .andExpect(model().attribute("logoutUrl", equalTo(UserController.CTRL_LOGOUT)))

                .andExpect(model().attributeExists("manageUsersUrl"))
                .andExpect(model().attribute("manageUsersUrl", any(String.class)))
                .andExpect(model().attribute("manageUsersUrl", notNullValue()))
                .andExpect(model().attribute("manageUsersUrl", equalTo(UserController.CTRL_USERS)));
    }
}