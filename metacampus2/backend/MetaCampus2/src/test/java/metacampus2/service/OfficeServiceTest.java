package metacampus2.service;

import metacampus2.AbstractTest;

import metacampus2.model.Office;
import metacampus2.repository.IOfficeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OfficeServiceTest extends AbstractTest {

    @Mock
    private IOfficeRepository officeRepository;

    private OfficeService officeService;

    @BeforeEach
    public void setUp(){
        officeService = new OfficeService(officeRepository);
    }

    @Test
    void getOfficeFromMetaverse() {

        Office office = new Office();
        office.setNumber("B5.01");

        when(officeRepository.findByNumberAndMetaverseName(Mockito.anyString(),Mockito.anyString())).thenReturn(office);

        assertEquals(office,officeService.getOfficeFromMetaverse("B5.01","Campus Est SUPSI"));

    }

    @Test
    void getAllOffices() {

        List<Office> officeList = new ArrayList<>();
        Office office = new Office();
        office.setNumber("B5.01");

        officeList.add(office);
        officeList.add(new Office());

        when(officeRepository.findAll()).thenReturn(officeList);

        assertEquals(officeList.size(), officeService.getAllOffices().size());
    }

    @Test
    void getAllOfficesFromMetaverse() {

        List<Office> officeList = new ArrayList<>();
        Office office = new Office();
        office.setNumber("B5.01");

        officeList.add(office);
        officeList.add(new Office());
        officeList.add(new Office());

        when(officeRepository.findAllByMetaverseName("Campus Est SUPSI")).thenReturn(officeList);

        assertEquals(office.getNumber(),officeService.getAllOfficesFromMetaverse("Campus Est SUPSI").get(0).getNumber());
    }
}