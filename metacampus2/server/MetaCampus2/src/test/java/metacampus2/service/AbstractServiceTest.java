package metacampus2.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AbstractServiceTest {

    @Test
    void getUrlName() {
        String urlName1 = AbstractService.getUrlName("Campus Est SUPSI");
        assertEquals("campus_est_supsi", urlName1);

        String urlName2 = AbstractService.getUrlName("Campus-Est-SUPSI");
        assertEquals("campus_est_supsi", urlName2);
    }
}