package metacampus2.service;

import metacampus2.model.Coordinate;
import metacampus2.model.Space;

public interface ISpaceService {
    Space getSpaceByCoordinatesAndMetaverse(int x, int y, int z, String metaverseName);
    Space getSpaceByNameAndMetaverse(String name, String metaverseName);
    Space getSpaceById(Long id);
}
