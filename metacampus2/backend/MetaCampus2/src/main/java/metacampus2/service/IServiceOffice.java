package metacampus2.service;

import metacampus2.model.*;

import java.util.List;
import java.util.Optional;

public interface IServiceOffice {

    Office addOfficeByMetaverse(Office office, Metaverse metaverse);

    Optional<Office> findOffice(String id);

    Optional<Office> updateOffice(Office office, String id);

    List<Office> getAllOffices();
}
