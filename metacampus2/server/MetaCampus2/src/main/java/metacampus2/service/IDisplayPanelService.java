package metacampus2.service;

import metacampus2.model.DisplayPanel;

import java.util.List;

public interface IDisplayPanelService {
    void addNewDisplayPanel(DisplayPanel displayPanel);
    void deleteDisplayPanel(DisplayPanel displayPanel);
    boolean createDirectory(DisplayPanel displayPanel);
    boolean renameDirectory(String oldName, DisplayPanel displayPanel);
    void deleteDirectory(DisplayPanel displayPanel);
    List<DisplayPanel> getAllDisplayPanels();
    List<DisplayPanel> getAllDisplayPanelsFromMetaverse(String metaverseName);
    List<DisplayPanel> getAllDisplayPanelsFromMetaverseByUrlName(String metaverseUrlName);
    String getImageFile(String metaverseUrlName, String displayPanelUrlName, String imageTitle, String imageFileName);
    byte[] getAudioFile(String metaverseUrlName, String displayPanelUrlName, String imageTitle, String audioTitle,
                        String audioFileName);
    DisplayPanel getDisplayPanelById(Long id);
}
