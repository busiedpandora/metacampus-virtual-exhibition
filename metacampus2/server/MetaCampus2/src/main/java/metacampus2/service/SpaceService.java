package metacampus2.service;

import metacampus2.model.Coordinate;
import metacampus2.model.Space;
import metacampus2.repository.ISpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpaceService implements ISpaceService {
    private ISpaceRepository spaceRepository;


    @Autowired
    public SpaceService(ISpaceRepository spaceRepository) {
        this.spaceRepository = spaceRepository;
    }

    @Override
    public Space getSpaceByCoordinatesAndMetaverse(int x, int y, int z, String metaverseName) {
        return spaceRepository.findByCoordinatesAndMetaverseName(x, y, z, metaverseName);
    }

    @Override
    public Space getSpaceByNameAndMetaverse(String name, String metaverseName) {
        return spaceRepository.findByNameAndMetaverseName(name, metaverseName);
    }

    @Override
    public Space getSpaceById(Long id) {
        return spaceRepository.findById(id).orElse(null);
    }
}
