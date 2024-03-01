package metacampus2.service;

import metacampus2.model.*;

import java.util.List;
import java.util.Optional;

public interface IServiceMetaverse {

    Metaverse addMetaverse(Metaverse metaverse);

    Optional<Metaverse> findMetaverse(Long id);

    Optional<Metaverse> updateMetaverse(Metaverse metaverse, Long id);

    List<Metaverse> getAllMetaverses();
}
