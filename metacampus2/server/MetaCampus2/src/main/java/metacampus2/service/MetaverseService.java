package metacampus2.service;

import metacampus2.model.Metaverse;
import metacampus2.repository.IMetaverseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;


@Service
public class MetaverseService extends AbstractService implements IMetaverseService {
    private IMetaverseRepository IMetaverseRepository;


    @Autowired
    public MetaverseService(IMetaverseRepository IMetaverseRepository) {
        this.IMetaverseRepository = IMetaverseRepository;
    }

    @Override
    public void addNewMetaverse(Metaverse metaverse) {
        metaverse.setUrlName(getUrlName(metaverse.getName()));

        IMetaverseRepository.save(metaverse);
    }

    @Override
    public boolean createDirectory(Metaverse metaverse) {
        File metaverseDirectory = new File(METAVERSES_PATH + getUrlName(metaverse.getName()));

        return !metaverseDirectory.exists() && metaverseDirectory.mkdirs();
    }

    @Override
    public Metaverse getMetaverse(String name) {
        return IMetaverseRepository.findByName(name);
    }

    @Override
    public List<Metaverse> getAllMetaverses() {
        return IMetaverseRepository.findAll();
    }
}


