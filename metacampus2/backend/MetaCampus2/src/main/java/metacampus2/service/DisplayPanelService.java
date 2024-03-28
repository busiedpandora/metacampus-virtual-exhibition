package metacampus2.service;

import metacampus2.model.DisplayPanel;
import metacampus2.repository.IDisplayPanelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisplayPanelService implements IDisplayPanelService {
    private IDisplayPanelRepository displayPanelRepository;


    @Autowired
    public DisplayPanelService(IDisplayPanelRepository displayPanelRepository) {
        this.displayPanelRepository = displayPanelRepository;
    }

    @Override
    public void addNewDisplayPanel(DisplayPanel displayPanel) {
        displayPanelRepository.save(displayPanel);
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
}
