package metacampus2.service;

import metacampus2.model.Text;
import metacampus2.model.TextPanel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ITextService {
    void addNewText(Text text);
    void editText(Text text);
    boolean createFile(Text text, MultipartFile textFile, TextPanel textPanel);
    boolean renameFile(String oldName, Text text, TextPanel textPanel);
    List<Text> getAllTexts();
    Text getTextByTitle(String title);
    Text getTextByFileName(String fileName);
    Text getTextById(Long id);
}
