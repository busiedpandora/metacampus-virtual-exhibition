package metacampus2.service;

import metacampus2.model.Metaverse;
import metacampus2.model.Office;
import metacampus2.repository.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceOffice implements IServiceOffice {

    @Autowired
    private OfficeRepository officeRepository;


    @Override
    public Office addOfficeByMetaverse(Office office, Metaverse metaverse) {

        metaverse.getOffices().add(office);

        office.setMetaverseOffice(metaverse);

        return officeRepository.save(office);
    }

    @Override
    public Optional<Office> findOffice(String id) {
        return officeRepository.findById(id);
    }

    @Override
    public Optional<Office> updateOffice(Office office, String id) {

        Optional<Office> checkOffice = officeRepository.findById(id);

        if(checkOffice.isPresent()){

            if(!office.getCoordinates().isEmpty()){
                checkOffice.get().setCoordinates(office.getCoordinates());
            }
            if(!office.getNumber().isEmpty()){
                checkOffice.get().setNumber(office.getNumber());
            }

            officeRepository.save(checkOffice.get());

            return checkOffice;
        }

        return Optional.empty();
    }

    @Override
    public List<Office> getAllOffices() {
        return officeRepository.findAll();
    }
}
