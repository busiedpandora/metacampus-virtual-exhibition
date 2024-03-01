package metacampus2.service;

import metacampus2.model.Metaverse;
import metacampus2.repository.MetaverseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceMetaverse implements IServiceMetaverse {

    @Autowired
    private MetaverseRepository metaverseRepository;


    @Override
    public Metaverse addMetaverse(Metaverse metaverse) {
        return metaverseRepository.save(metaverse);
    }

    @Override
    public Optional<Metaverse> findMetaverse(Long id) {
        return metaverseRepository.findById(id);
    }

    @Override
    public Optional<Metaverse> updateMetaverse(Metaverse metaverse, Long id) {

        Optional<Metaverse> checkMetaverse = metaverseRepository.findById(id);

        if(checkMetaverse.isPresent()){

            if(!metaverse.getName().isEmpty()){
                checkMetaverse.get().setName(metaverse.getName());
            }

            metaverseRepository.save(checkMetaverse.get());

            return checkMetaverse;
        }


        return Optional.empty();
    }

    @Override
    public List<Metaverse> getAllMetaverses() {
        return metaverseRepository.findAll();
    }
}
