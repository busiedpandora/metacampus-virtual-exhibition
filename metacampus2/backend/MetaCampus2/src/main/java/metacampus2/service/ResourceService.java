package metacampus2.service;

import metacampus2.model.Resource;
import metacampus2.repository.IResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceService implements IResourceService {
    private IResourceRepository IResourceRepository;


    @Autowired
    public ResourceService(IResourceRepository IResourceRepository) {
        this.IResourceRepository = IResourceRepository;
    }

    @Override
    public List<Resource> getAllResources() {
        return IResourceRepository.findAll();
    }

    @Override
    public List<Resource> getAllResourcesFromMetaverse(String metaverseName) {
        return IResourceRepository.findAllByMetaverseName(metaverseName);
    }
}
