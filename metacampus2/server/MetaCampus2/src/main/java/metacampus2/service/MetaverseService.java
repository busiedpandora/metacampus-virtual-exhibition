package metacampus2.service;

import metacampus2.model.Metaverse;
import metacampus2.repository.IMetaverseRepository;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
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
    public void deleteMetaverse(Metaverse metaverse) {
        metaverseRepository.delete(metaverse);
    }

    @Override
    public boolean createDirectory(Metaverse metaverse) {
        File metaverseDirectory = new File(METAVERSES_PATH + getUrlName(metaverse.getName()));

        return !metaverseDirectory.exists() && metaverseDirectory.mkdirs();
    }

    @Override
    public boolean renameDirectory(String oldName, Metaverse metaverse) {
        File metaverseDirectory = new File(METAVERSES_PATH + getUrlName(oldName));
        File metaverseRenamedDirectory = new File(METAVERSES_PATH + getUrlName(metaverse.getName()));

        return metaverseDirectory.renameTo(metaverseRenamedDirectory);
    }

    @Override
    public void deleteDirectory(Metaverse metaverse) {
        File metaverseDirectory = new File(METAVERSES_PATH + metaverse.getUrlName());
        try {
            FileUtils.deleteDirectory(metaverseDirectory);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

    @Override
    public Metaverse getMetaverseById(Long id) {
        return metaverseRepository.findById(id).orElse(null);
    }
}


