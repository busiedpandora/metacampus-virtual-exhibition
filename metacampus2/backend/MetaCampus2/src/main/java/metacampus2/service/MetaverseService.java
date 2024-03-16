package metacampus2.service;

import metacampus2.model.Metaverse;
import metacampus2.repository.IMetaverseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MetaverseService implements IMetaverseService {
    private IMetaverseRepository metaverseRepository;


    @Autowired
    public MetaverseService(IMetaverseRepository metaverseRepository) {
        this.metaverseRepository = metaverseRepository;
    }

    @Override
    public void addNewMetaverse(Metaverse metaverse) {
        metaverseRepository.save(metaverse);
    }

    @Override
    public Metaverse getMetaverse(String name) {
        return metaverseRepository.findByName(name);
    }

    @Override
    public List<Metaverse> getAllMetaverses() {
        return metaverseRepository.findAll();
    }
}


