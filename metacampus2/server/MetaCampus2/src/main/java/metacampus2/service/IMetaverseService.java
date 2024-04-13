package metacampus2.service;

import metacampus2.model.*;

import java.util.List;


public interface IMetaverseService {
    void addNewMetaverse(Metaverse metaverse);

    Metaverse getMetaverse(String name);

    List<Metaverse> getAllMetaverses();
}


