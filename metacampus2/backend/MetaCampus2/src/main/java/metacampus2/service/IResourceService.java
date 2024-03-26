package metacampus2.service;

import metacampus2.model.Resource;

import java.util.List;

public interface IResourceService {
    List<Resource> getAllResources();

    List<Resource> getAllResourcesFromMetaverse(String metaverseName);
}
