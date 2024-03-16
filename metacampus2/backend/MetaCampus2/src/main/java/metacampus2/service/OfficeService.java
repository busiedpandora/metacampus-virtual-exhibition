/*
package metacampus2.service;

import metacampus2.model.Office;
import metacampus2.repository.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OfficeService implements IOfficeService {
    private OfficeRepository officeRepository;


    @Autowired
    public OfficeService(OfficeRepository officeRepository) {
        this.officeRepository = officeRepository;
    }

    @Override
    public void addNewOffice(Office office) {
        officeRepository.save(office);
    }

    @Override
    public Office getOfficeFromMetaverse(String officeNumber, String metaverseName) {
        return officeRepository.findByNumberAndMetaverseName(officeNumber, metaverseName);
    }

    @Override
    public List<Office> getAllOffices() {
        return officeRepository.findAll();
    }

    @Override
    public List<Office> getAllOfficesFromMetaverse(String metaverseName) {
        return officeRepository.findAllByMetaverseName(metaverseName);
    }
}

*/
