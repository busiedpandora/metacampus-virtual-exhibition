package metacampus2.service;

import metacampus2.model.Text;
import metacampus2.model.TextPanel;
import metacampus2.repository.ITextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TextService extends AbstractService implements ITextService {
    private ITextRepository textRepository;


    @Autowired
    public TextService(ITextRepository textRepository) {
        this.textRepository = textRepository;
    }

    @Override
    public void addNewText(Text text) {
        text.setLastEditDate(LocalDateTime.now());

        textRepository.save(text);
    }

    @Override
    public boolean createFile(Text text, MultipartFile textFile, TextPanel textPanel) {
        File textDirectory = new File(METAVERSES_PATH + textPanel.getMetaverse().getUrlName() +
                SEPARATOR + TEXT_PANELS_PATH + textPanel.getUrlName() + SEPARATOR + TEXT_PATH);

        if(!textDirectory.exists()) {
            if(!textDirectory.mkdirs()) {
                return false;
            }
        }

        Path textPath = Path.of(textDirectory.getPath() + SEPARATOR + textFile.getOriginalFilename());

        try {
            Files.copy(textFile.getInputStream(), textPath, StandardCopyOption.REPLACE_EXISTING);

            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public List<Text> getAllTexts() {
        return textRepository.findAll();
    }

    @Override
    public Text getTextbyName(String name) {
        return textRepository.findByFileName(name);
    }
}
