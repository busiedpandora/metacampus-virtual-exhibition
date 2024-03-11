package metacampus2.service;

import metacampus2.model.Office;
import metacampus2.repository.IOfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OfficeService implements IOfficeService {
    private IOfficeRepository IOfficeRepository;


    @Autowired
    public OfficeService(IOfficeRepository IOfficeRepository) {
        this.IOfficeRepository = IOfficeRepository;
    }

    @Override
    public void addNewOffice(Office office) {
        IOfficeRepository.save(office);
    }

    @Override
    public Office getOfficeFromMetaverse(String officeNumber, String metaverseName) {
        return IOfficeRepository.findByNumberAndMetaverseName(officeNumber, metaverseName);
    }

    @Override
    public List<Office> getAllOffices() {
        return IOfficeRepository.findAll();
    }

    @Override
    public List<Office> getAllOfficesFromMetaverse(String metaverseName) {
        return IOfficeRepository.findAllByMetaverseName(metaverseName);
    }
}

