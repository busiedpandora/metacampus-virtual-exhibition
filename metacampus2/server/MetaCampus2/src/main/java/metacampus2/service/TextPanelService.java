package metacampus2.service;

import metacampus2.model.TextPanel;
import metacampus2.repository.ITextPanelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.List;

@Service
public class TextPanelService extends AbstractService implements ITextPanelService {
    private ITextPanelRepository textPanelRepository;


    @Autowired
    public TextPanelService(ITextPanelRepository textPanelRepository) {
        this.textPanelRepository = textPanelRepository;
    }

    @Override
    public void addNewTextPanel(TextPanel textPanel) {
        textPanel.setUrlName(getUrlName(textPanel.getName()));

        textPanelRepository.save(textPanel);
    }

    @Override
    public boolean createDirectory(TextPanel textPanel) {
        File textPanelPanelDirectory = new File(METAVERSES_PATH
                + textPanel.getMetaverse().getUrlName() + SEPARATOR
                + TEXT_PANELS_PATH + getUrlName(textPanel.getName()));

        return !textPanelPanelDirectory.exists() && textPanelPanelDirectory.mkdirs();
    }

    @Override
    public boolean renameDirectory(String oldName, TextPanel textPanel) {
        File textPanelDirectory = new File(METAVERSES_PATH
                + textPanel.getMetaverse().getUrlName() + SEPARATOR
                + TEXT_PANELS_PATH + getUrlName(oldName));

        File textPanelRenamedDirectory = new File(METAVERSES_PATH
                + textPanel.getMetaverse().getUrlName() + SEPARATOR
                + TEXT_PANELS_PATH + getUrlName(textPanel.getName()));

        return textPanelDirectory.renameTo(textPanelRenamedDirectory);
    }


    @Override
    public List<TextPanel> getAllTextPanels() {
        return textPanelRepository.findAll();
    }

    @Override
    public List<TextPanel> getAllTextPanelsFromMetaverse(String metaverseName) {
        return textPanelRepository.findAllByMetaverseName(metaverseName);
    }

    @Override
    public List<TextPanel> getAllTextPanelsFromMetaverseByUrlName(String metaverseUrlName) {
        return textPanelRepository.findAllByMetaverseUrlName(metaverseUrlName);
    }

    @Override
    public String getTextFile(String metaverseUrlName, String textPanelUrlName, String textName) {
        try {
            File textDirectory = new File(METAVERSES_PATH + metaverseUrlName +
                    SEPARATOR + TEXT_PANELS_PATH + textPanelUrlName + SEPARATOR + TEXT_PATH);
            if(!textDirectory.exists()) {
                return null;
            }

            Path textPath = Path.of(textDirectory + SEPARATOR + textName);
            if(!Files.exists(textPath)) {
                return null;
            }

            return Base64.getEncoder().encodeToString(Files.readAllBytes(textPath));

        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public TextPanel getTextPanelById(Long id) {
        return textPanelRepository.findById(id).orElse(null);
    }
}
