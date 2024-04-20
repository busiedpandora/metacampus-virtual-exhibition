package metacampus2.service;

import metacampus2.model.*;
import org.springframework.data.jpa.repository.Meta;

import java.util.List;


public interface IMetaverseService {
    void addNewMetaverse(Metaverse metaverse);
    void deleteMetaverse(Metaverse metaverse);
    boolean createDirectory(Metaverse metaverse);
    boolean renameDirectory(String oldName, Metaverse metaverse);
    void deleteDirectory(Metaverse metaverse);
    Metaverse getMetaverseByName(String name);
    Metaverse getMetaverseByUrlName(String urlName);
    List<Metaverse> getAllMetaverses();
    Metaverse getMetaverseById(Long id);
}


