package metacampus2.service;

import metacampus2.model.Text;
import metacampus2.repository.ITextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TextService implements ITextService {
    private ITextRepository textRepository;


    @Autowired
    public TextService(ITextRepository textRepository) {
        this.textRepository = textRepository;
    }

    @Override
    public void addNewText(Text text) {
        textRepository.save(text);
    }

    @Override
    public List<Text> getAllTexts() {
        return textRepository.findAll();
    }

    @Override
    public Text getTextbyName(String name) {
        return textRepository.findByName(name);
    }
}
