package metacampus2.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class HomeControllerTest extends AbstractControllerTest {

    @Test
    void home() throws Exception {
        mockMvc
                .perform(get("/")
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.TEXT_PLAIN))
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
    }
}