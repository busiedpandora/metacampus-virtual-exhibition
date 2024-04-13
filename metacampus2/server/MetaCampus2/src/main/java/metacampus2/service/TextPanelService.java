package metacampus2.service;

import metacampus2.model.TextPanel;
import metacampus2.repository.ITextPanelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TextPanelService implements ITextPanelService {
    private ITextPanelRepository textPanelRepository;


    @Autowired
    public TextPanelService(ITextPanelRepository textPanelRepository) {
        this.textPanelRepository = textPanelRepository;
    }

    @Override
    public void addNewTextPanel(TextPanel textPanel) {
        textPanelRepository.save(textPanel);
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
}
