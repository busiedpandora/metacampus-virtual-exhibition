package metacampus2.service;

import metacampus2.model.Text;

import java.util.List;

public interface ITextService {
    void addNewText(Text text);
    List<Text> getAllTexts();
    Text getTextbyName(String name);
}
