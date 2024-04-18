package metacampus2.service;

import metacampus2.model.DisplayPanel;
import metacampus2.model.Image;
import metacampus2.repository.IDisplayPanelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.List;

@Service
public class DisplayPanelService extends AbstractService implements IDisplayPanelService {
    private IDisplayPanelRepository displayPanelRepository;


    @Autowired
    public DisplayPanelService(IDisplayPanelRepository displayPanelRepository) {
        this.displayPanelRepository = displayPanelRepository;
    }

    @Override
    public void addNewDisplayPanel(DisplayPanel displayPanel) {
        displayPanel.setUrlName(getUrlName(displayPanel.getName()));

        List<Image> images = displayPanel.getImages();
        if(images != null && images.size() > displayPanel.getType().getCapacity()) {
            images = images.subList(0, displayPanel.getType().getCapacity());
            displayPanel.setImages(images);
        }

        displayPanelRepository.save(displayPanel);
    }

    @Override
    public boolean createDirectory(DisplayPanel displayPanel) {
        File displayPanelDirectory = new File(METAVERSES_PATH
                + displayPanel.getMetaverse().getUrlName() + SEPARATOR
                + DISPLAY_PANELS_PATH + getUrlName(displayPanel.getName()));

        return !displayPanelDirectory.exists() && displayPanelDirectory.mkdirs();
    }

    @Override
    public boolean renameDirectory(String oldName, DisplayPanel displayPanel) {
        File displayPanelDirectory = new File(METAVERSES_PATH
                + displayPanel.getMetaverse().getUrlName() + SEPARATOR
                + DISPLAY_PANELS_PATH + getUrlName(oldName));

        File displayPanelRenamedDirectory = new File(METAVERSES_PATH
                + displayPanel.getMetaverse().getUrlName() + SEPARATOR
                + DISPLAY_PANELS_PATH + getUrlName(displayPanel.getName()));

        return displayPanelDirectory.renameTo(displayPanelRenamedDirectory);
    }

    @Override
    public List<DisplayPanel> getAllDisplayPanels() {
        return displayPanelRepository.findAll();
    }

    @Override
    public List<DisplayPanel> getAllDisplayPanelsFromMetaverse(String metaverseName) {
        return displayPanelRepository.findAllByMetaverseName(metaverseName);
    }

    @Override
    public List<DisplayPanel> getAllDisplayPanelsFromMetaverseByUrlName(String metaverseUrlName) {
        return displayPanelRepository.findAllByMetaverseUrlName(metaverseUrlName);
    }

    @Override
    public String getImageFile(String metaverseUrlName, String displayPanelUrlName, String imageName) {
        try {
            String imageNameWithoutExtension = imageName.substring(0, imageName.lastIndexOf('.'));
            File imagesDirectory = new File(METAVERSES_PATH + metaverseUrlName +
                    SEPARATOR + DISPLAY_PANELS_PATH + displayPanelUrlName + SEPARATOR +
                    IMAGES_PATH + imageNameWithoutExtension);
            if (!imagesDirectory.exists()) {
                return null;
            }

            Path imagePath = Path.of(imagesDirectory.getPath() + SEPARATOR + imageName);
            if (!Files.exists(imagePath)) {
                return null;
            }

            return Base64.getEncoder().encodeToString(Files.readAllBytes(imagePath));

        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public byte[] getAudioFile(String metaverseUrlName, String displayPanelUrlName, String imageName,
                               String audioName) {

        try {
            String imageNameWithoutExtension = imageName.substring(0, imageName.lastIndexOf('.'));
            File audioDirectory = new File(METAVERSES_PATH + metaverseUrlName +
                    SEPARATOR + DISPLAY_PANELS_PATH + displayPanelUrlName + SEPARATOR + IMAGES_PATH +
                    imageNameWithoutExtension + SEPARATOR + AUDIO_PATH);
            if (!audioDirectory.exists()) {
                return null;
            }

            Path audioPath = Path.of(audioDirectory.getPath() + SEPARATOR + audioName);

            if (!Files.exists(audioPath)) {
                return null;
            }

            return Files.readAllBytes(audioPath);

        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public DisplayPanel getDisplayPanelById(Long id) {
        return displayPanelRepository.findById(id).orElse(null);
    }
}
