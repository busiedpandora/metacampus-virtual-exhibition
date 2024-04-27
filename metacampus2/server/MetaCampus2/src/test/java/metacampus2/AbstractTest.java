package metacampus2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import metacampus2.model.*;
import metacampus2.repository.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.event.annotation.BeforeTestExecution;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class AbstractTest {
    @Autowired
    private IMetaverseRepository metaverseRepository;
    @Autowired
    private ITextPanelRepository textPanelRepository;
    @Autowired
    private IDisplayPanelRepository displayPanelRepository;
    @Autowired
    private ITextRepository textRepository;
    @Autowired
    private IImageRepository imageRepository;
    @Autowired
    private IAudioRepository audioRepository;
    @Autowired
    private IUserRepository userRepository;

    protected Metaverse metaverse;
    protected TextPanel textPanel;
    protected DisplayPanel displayPanel;
    protected Text text;
    protected Image image;
    protected Audio audio;

    private ObjectMapper objectMapper = new ObjectMapper();
    protected ObjectWriter objectWriter;

    private static volatile boolean isInitialized = false;

    @BeforeAll
     void init() {
            objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
            objectMapper.registerModule(new JavaTimeModule());
            objectWriter = objectMapper.writer().withDefaultPrettyPrinter();

            User user = new User();
            user.setId(1L);
            user.setUsername("test");
            user.setPassword("test");
            user.setRole(UserRole.ROLE_ADMIN);
            if(userRepository.findByUsername("test") == null) {
                userRepository.save(user);
            }

            metaverse = new Metaverse();
            metaverse.setId(1L);
            metaverse.setName("Campus Est SUPSI");
            metaverse.setUrlName("campus_est_supsi");
            metaverse.setMinXDimension(-30);
            metaverse.setMaxXDimension(30);
            metaverse.setMinYDimension(0);
            metaverse.setMaxYDimension(5);
            metaverse.setMinZDimension(-30);
            metaverse.setMaxZDimension(30);
            metaverse.setCreator(user);
            if(metaverseRepository.findByName("Campus Est SUPSI") == null) {
                metaverseRepository.save(metaverse);
            }

            textPanel = new TextPanel();
            textPanel.setId(1L);
            textPanel.setName("text panel 1");
            textPanel.setUrlName("text_panel_1");
            Coordinate coordinate = new Coordinate();
            coordinate.setX(-2);
            coordinate.setY(0);
            coordinate.setZ(2);
            textPanel.setCoordinates(coordinate);
            textPanel.setMetaverse(metaverse);
            textPanel.setCreator(user);
            if(textPanelRepository.findAll().isEmpty()) {
                textPanelRepository.save(textPanel);
            }

            displayPanel = new DisplayPanel();
            displayPanel.setId(2L);
            displayPanel.setName("one display panel exhibition");
            displayPanel.setUrlName("one_display_panel_exhibition");
            displayPanel.setType(DisplayPanelType.ONE_DISPLAY_PANEL_EXHIBITION);
            Coordinate coordinate2 = new Coordinate();
            coordinate2.setX(-2);
            coordinate2.setY(0);
            coordinate2.setZ(2);
            displayPanel.setCoordinates(coordinate2);
            displayPanel.setMetaverse(metaverse);
            displayPanel.setCreator(user);
            if(displayPanelRepository.findAll().isEmpty()) {
                displayPanelRepository.save(displayPanel);
            }

            text = new Text();
            text.setId(1L);
            text.setTitle("text1");
            text.setFileName("textFile1");
            text.setLastEditDate(LocalDateTime.now());
            text.setTextPanels(List.of(textPanel));
            text.setCreator(user);
            text.setCreationDate(LocalDateTime.now());
            if(textRepository.findAll().isEmpty()) {
                textRepository.save(text);
            }

            image = new Image();
            image.setId(2L);
            image.setTitle("image1");
            image.setFileName("imageFile1");
            image.setLastEditDate(LocalDateTime.now());
            image.setDisplayPanels(List.of(displayPanel));
            image.setCreator(user);
            image.setCreationDate(LocalDateTime.now());
            if(imageRepository.findAll().isEmpty()) {
                imageRepository.save(image);
            }

            audio = new Audio();
            audio.setId(3L);
            audio.setTitle("audio1");
            audio.setFileName("audioFile1");
            audio.setLastEditDate(LocalDateTime.now());
            audio.setImage(image);
            audio.setCreator(user);
            audio.setCreationDate(LocalDateTime.now());
            if(audioRepository.findAll().isEmpty()) {
                audioRepository.save(audio);
            }
    }
}
