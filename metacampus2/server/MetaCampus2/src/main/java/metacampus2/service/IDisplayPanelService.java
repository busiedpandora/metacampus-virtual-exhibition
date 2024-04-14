package metacampus2.service;

import metacampus2.model.DisplayPanel;

import java.util.List;

public interface IDisplayPanelService {
    void addNewDisplayPanel(DisplayPanel displayPanel);
    boolean createDirectory(DisplayPanel displayPanel);
    List<DisplayPanel> getAllDisplayPanels();
    List<DisplayPanel> getAllDisplayPanelsFromMetaverse(String metaverseName);
    List<DisplayPanel> getAllDisplayPanelsFromMetaverseByUrlName(String metaverseUrlName);
    String getImageFile(String metaverseUrlName, String displayPanelUrlName, String imageName);
    byte[] getAudioFile(String metaverseUrlName, String displayPanelUrlName, String imageName, String audioName);
}
