package metacampus2.service;

import metacampus2.model.DisplayPanel;

import java.util.List;

public interface IDisplayPanelService {
    void addNewDisplayPanel(DisplayPanel displayPanel);
    List<DisplayPanel> getAllDisplayPanels();
    List<DisplayPanel> getAllDisplayPanelsFromMetaverse(String metaverseName);
    List<DisplayPanel> getAllDisplayPanelsFromMetaverseByUrlName(String metaverseUrlName);
}
