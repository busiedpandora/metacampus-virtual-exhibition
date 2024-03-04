package metacampus2.service;

import metacampus2.model.Resource;
import metacampus2.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceService implements IResourceService {
    @Autowired
    private ResourceRepository resourceRepository;


    @Override
    public List<Resource> getAllResources() {
        return resourceRepository.findAll();
    }

    @Override
    public List<Resource> getAllResourcesFromMetaverse(String metaverseName) {
        return resourceRepository.findAllByMetaverseName(metaverseName);
    }
}
