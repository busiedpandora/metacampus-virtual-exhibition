package metacampus2.service;

import metacampus2.model.*;

import java.util.List;


public interface IMetaverseService {
    void addNewMetaverse(Metaverse metaverse);
    boolean createDirectory(Metaverse metaverse);
    Metaverse getMetaverseByName(String name);
    Metaverse getMetaverseByUrlName(String urlName);
    List<Metaverse> getAllMetaverses();
}


