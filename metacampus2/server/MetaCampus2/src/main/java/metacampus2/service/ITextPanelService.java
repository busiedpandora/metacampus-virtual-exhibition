package metacampus2.service;

import metacampus2.model.TextPanel;

import java.util.List;

public interface ITextPanelService {
    void addNewTextPanel(TextPanel textPanel);
    void deleteTextPanel(TextPanel textPanel);
    boolean createDirectory(TextPanel textPanel);
    boolean renameDirectory(String oldName, TextPanel textPanel);
    void deleteDirectory(TextPanel textPanel);
    List<TextPanel> getAllTextPanels();
    List<TextPanel> getAllTextPanelsFromMetaverse(String metaverseName);
    List<TextPanel> getAllTextPanelsFromMetaverseByUrlName(String metaverseUrlName);
    String getTextFile(String metaverseUrlName, String textPanelUrlName, String textTitle, String textFileName);
    TextPanel getTextPanelById(Long id);
}
