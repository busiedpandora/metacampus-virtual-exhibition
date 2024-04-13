package metacampus2.service;

import metacampus2.model.Metaverse;
import metacampus2.repository.IMetaverseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MetaverseService implements IMetaverseService {
    private IMetaverseRepository IMetaverseRepository;


    @Autowired
    public MetaverseService(IMetaverseRepository IMetaverseRepository) {
        this.IMetaverseRepository = IMetaverseRepository;
    }

    @Override
    public void addNewMetaverse(Metaverse metaverse) {
        IMetaverseRepository.save(metaverse);
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


