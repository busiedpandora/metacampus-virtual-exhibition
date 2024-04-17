package metacampus2.service;

import metacampus2.model.Metaverse;
import metacampus2.repository.IMetaverseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;


@Service
public class MetaverseService extends AbstractService implements IMetaverseService {
    private IMetaverseRepository metaverseRepository;


    @Autowired
    public MetaverseService(IMetaverseRepository metaverseRepository) {
        this.metaverseRepository = metaverseRepository;
    }

    @Override
    public void addNewMetaverse(Metaverse metaverse) {
        metaverse.setUrlName(getUrlName(metaverse.getName()));

        metaverseRepository.save(metaverse);
    }

    @Override
    public boolean createDirectory(Metaverse metaverse) {
        File metaverseDirectory = new File(METAVERSES_PATH + getUrlName(metaverse.getName()));

        return !metaverseDirectory.exists() && metaverseDirectory.mkdirs();
    }

    @Override
    public Metaverse getMetaverseByName(String name) {
        return metaverseRepository.findByName(name);
    }

    @Override
    public Metaverse getMetaverseByUrlName(String urlName) {
        return metaverseRepository.findByUrlName(urlName);
    }

    @Override
    public List<Metaverse> getAllMetaverses() {
        return metaverseRepository.findAll();
    }
}


