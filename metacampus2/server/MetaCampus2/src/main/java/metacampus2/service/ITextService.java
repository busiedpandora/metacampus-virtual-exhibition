package metacampus2.service;

import metacampus2.model.Text;
import metacampus2.model.TextPanel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ITextService {
    void addNewText(Text text);
    boolean createFile(Text text, MultipartFile textFile, TextPanel textPanel);
    List<Text> getAllTexts();
    Text getTextbyName(String name);
}
