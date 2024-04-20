package metacampus2.service;

import metacampus2.model.DisplayPanel;
import metacampus2.model.Image;
import metacampus2.repository.IDisplayPanelRepository;
import org.apache.tomcat.util.http.fileupload.FileUtils;
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
    public void deleteDisplayPanel(DisplayPanel displayPanel) {
        displayPanelRepository.delete(displayPanel);
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
    public void deleteDirectory(DisplayPanel displayPanel) {
        File displayPanelDirectory = new File(METAVERSES_PATH
                + displayPanel.getMetaverse().getUrlName() + SEPARATOR
                + DISPLAY_PANELS_PATH + displayPanel.getUrlName());

        try {
            FileUtils.deleteDirectory(displayPanelDirectory);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
    public String getImageFile(String metaverseUrlName, String displayPanelUrlName, String imageTitle,
                               String imageFileName) {
        try {
            //String imageNameWithoutExtension = imageFileName.substring(0, imageFileName.lastIndexOf('.'));
            File imagesDirectory = new File(METAVERSES_PATH + metaverseUrlName +
                    SEPARATOR + DISPLAY_PANELS_PATH + displayPanelUrlName + SEPARATOR +
                    IMAGES_PATH + imageTitle);
            if (!imagesDirectory.exists()) {
                return null;
            }

            Path imagePath = Path.of(imagesDirectory.getPath() + SEPARATOR + imageFileName);
            if (!Files.exists(imagePath)) {
                return null;
            }

            return Base64.getEncoder().encodeToString(Files.readAllBytes(imagePath));

        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public byte[] getAudioFile(String metaverseUrlName, String displayPanelUrlName, String imageTitle,
                               String audioTitle, String audioFileName) {

        try {
            File audioDirectory = new File(METAVERSES_PATH + metaverseUrlName +
                    SEPARATOR + DISPLAY_PANELS_PATH + displayPanelUrlName + SEPARATOR + IMAGES_PATH +
                    imageTitle + SEPARATOR + AUDIO_PATH + audioTitle);
            if (!audioDirectory.exists()) {
                return null;
            }

            Path audioPath = Path.of(audioDirectory.getPath() + SEPARATOR + audioFileName);

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
