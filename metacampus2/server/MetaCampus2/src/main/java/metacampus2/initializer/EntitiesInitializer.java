package metacampus2.initializer;

import metacampus2.model.*;
import metacampus2.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@Profile("!test")
public class EntitiesInitializer implements CommandLineRunner, Ordered {
    protected static final String SEPARATOR = FileSystems.getDefault().getSeparator();
    protected static final String BASE_PATH = "." + SEPARATOR + "resourcesToLoadAtStart" + SEPARATOR;
    protected static final String TEXTS_PATH = SEPARATOR + "texts" + SEPARATOR;
    protected static final String IMAGES_PATH = SEPARATOR + "images" + SEPARATOR;
    protected static final String AUDIOS_PATH = SEPARATOR + "audios" + SEPARATOR;


    @Value("${loadMetaverse}")
    private String loadMetaverseName;

    private IMetaverseService metaverseService;
    private ITextPanelService textPanelService;
    private IDisplayPanelService displayPanelService;
    private ISpaceService spaceService;
    private ITextService textService;
    private IImageService imageService;
    private IAudioService audioService;
    private IUserService userService;


    @Autowired
    public EntitiesInitializer(IMetaverseService metaverseService,
                               ITextPanelService textPanelService,
                               IDisplayPanelService displayPanelService,
                               ISpaceService spaceService,
                               ITextService textService,
                               IImageService imageService,
                               IAudioService audioService,
                               IUserService userService) {
        this.metaverseService = metaverseService;
        this.textPanelService = textPanelService;
        this.displayPanelService = displayPanelService;
        this.spaceService = spaceService;
        this.textService = textService;
        this.imageService = imageService;
        this.audioService = audioService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        if(loadMetaverseName != null && !loadMetaverseName.isEmpty()) {
            initEntities();
        }
    }

    private void initEntities() {
        Yaml yaml = new Yaml();

        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream("static/project-data.yml");

        if (inputStream != null) {
            Map<String, Object> data = yaml.load(inputStream);

            List<Map<String, Object>> entities = (List<Map<String, Object>>) data.get("startupEntities");

            if (entities != null) {
                for (Map<String, Object> entity : entities) {
                    String type = (String) entity.get("type");

                    switch (type) {
                        case "metaverse":
                            createMetaverse(entity);
                            break;
                        case "textPanel":
                            createTextPanel(entity);
                            break;
                        case "displayPanel":
                            createDisplayPanel(entity);
                            break;
                        case "text":
                            createText(entity);
                            break;
                        case "image":
                            createImage(entity);
                            break;
                        case "audio":
                            createAudio(entity);
                            break;
                    }
                }
            }
        }
    }

    protected void createMetaverse(Map<String, Object> m) {
        String name = (String) m.get("name");
        int minXDimension = (int) m.get("minXDimension");
        int maxXDimension = (int) m.get("maxXDimension");
        int minYDimension = (int) m.get("minYDimension");
        int maxYDimension = (int) m.get("maxYDimension");
        int minZDimension = (int) m.get("minZDimension");
        int maxZDimension = (int) m.get("maxZDimension");

        if (metaverseService.getMetaverseByName(name) == null) {
            Metaverse metaverse = new Metaverse();
            metaverse.setName((String) m.get("name"));
            metaverse.setMinXDimension(minXDimension);
            metaverse.setMaxXDimension(maxXDimension);
            metaverse.setMinYDimension(minYDimension);
            metaverse.setMaxYDimension(maxYDimension);
            metaverse.setMinZDimension(minZDimension);
            metaverse.setMaxZDimension(maxZDimension);
            metaverse.setCreator(userService.getUser(UserService.ADMIN1));

            if(metaverseService.createDirectory(metaverse)) {
                metaverseService.addNewMetaverse(metaverse);
            }
        }
    }

    protected void createTextPanel(Map<String, Object> tp) {
        String name = (String) tp.get("name");
        Map<String, Object> coords = (Map<String, Object>) tp.get("coordinates");
        int x = (int) coords.get("x");
        int y = (int) coords.get("y");
        int z = (int) coords.get("z");
        String metaverseName = (String) tp.get("metaverse");
        Metaverse metaverse = metaverseService.getMetaverseByName(metaverseName);

        if(metaverse != null && spaceService.getSpaceByNameAndMetaverse(name, metaverseName) == null
                && spaceService.getSpaceByCoordinatesAndMetaverse(x, y, z, metaverseName) == null) {
            TextPanel textPanel = new TextPanel();
            textPanel.setName(name);
            textPanel.setMetaverse(metaverse);

            Coordinate coordinates = new Coordinate();
            coordinates.setX(x);
            coordinates.setY(y);
            coordinates.setZ(z);
            textPanel.setCoordinates(coordinates);

            textPanel.setCreator(userService.getUser(UserService.ADMIN1));

            if(textPanelService.createDirectory(textPanel)) {
                textPanelService.addNewTextPanel(textPanel);
            }
        }
    }

    protected void createDisplayPanel(Map<String, Object> tp) {
        String name = (String) tp.get("name");
        String typeName = (String) tp.get("displayPanelType");
        DisplayPanelType type = DisplayPanelType.getDisplayPanelTypeByName(typeName);
        Map<String, Object> coords = (Map<String, Object>) tp.get("coordinates");
        int x = (int) coords.get("x");
        int y = (int) coords.get("y");
        int z = (int) coords.get("z");
        String metaverseName = (String) tp.get("metaverse");
        Metaverse metaverse = metaverseService.getMetaverseByName(metaverseName);

        if(metaverse != null && spaceService.getSpaceByNameAndMetaverse(name, metaverseName) == null
                && spaceService.getSpaceByCoordinatesAndMetaverse(x, y, z, metaverseName) == null) {
            DisplayPanel displayPanel = new DisplayPanel();
            displayPanel.setName(name);
            displayPanel.setType(type);
            displayPanel.setMetaverse(metaverse);

            Coordinate coordinates = new Coordinate();
            coordinates.setX(x);
            coordinates.setY(y);
            coordinates.setZ(z);
            displayPanel.setCoordinates(coordinates);

            displayPanel.setCreator(userService.getUser(UserService.ADMIN1));

            if(displayPanelService.createDirectory(displayPanel)) {
                displayPanelService.addNewDisplayPanel(displayPanel);
            }
        }
    }

    //The text is associated to a single text panel only
    @Transactional
    public void createText(Map<String, Object> t) {
        String title = (String) t.get("title");
        String fileName = (String) t.get("fileName");
        String textPanelName = (String) t.get("textPanel");
        String panelMetaverseName = (String) t.get("panelMetaverse");

        Space panel = spaceService.getSpaceByNameAndMetaverse(textPanelName, panelMetaverseName);

        if(textService.getTextByTitle(title) == null && panel instanceof TextPanel) {
            TextPanel textPanel = (TextPanel) panel;
            if(textPanel.getText() != null) {
                return;
            }

            File file = new File(BASE_PATH + loadMetaverseName + TEXTS_PATH + fileName);
            if(!file.exists()) {
                return;
            }

            byte[] fileContent = new byte[0];
            String originalFilename = null;
            String contentType = null;
            try {
                fileContent = Files.readAllBytes(file.toPath());
                originalFilename  = file.getName();
                contentType = Files.probeContentType(file.toPath());
            } catch (IOException e) {
                throw new RuntimeException("Error in text creation: " + fileName);
            }

            MultipartFile multipartFile = new CustomMultipartFile(fileContent, originalFilename, contentType);

            Text text = new Text();
            text.setTitle(title);
            text.setFileName(originalFilename);
            text.setTextPanels(List.of(textPanel));

            text.setCreator(userService.getUser(UserService.ADMIN1));

            if(textService.createFile(text, multipartFile, textPanel)) {
                textService.addNewText(text);
                textPanelService.addNewTextPanel(textPanel);
            }
        }
    }

    //The image is associated to a single display panel only
    @Transactional
    public void createImage(Map<String, Object> i) {
        String title = (String) i.get("title");
        String fileName = (String) i.get("fileName");
        String displayPanelName = (String) i.get("displayPanel");
        String panelMetaverseName = (String) i.get("panelMetaverse");

        Space panel = spaceService.getSpaceByNameAndMetaverse(displayPanelName, panelMetaverseName);

        if(imageService.getImageByTitle(title) == null && panel instanceof DisplayPanel) {
            DisplayPanel displayPanel = (DisplayPanel) panel;
            if(displayPanel.getImages() == null) {
                displayPanel.setImages(new ArrayList<>());
            }
            else if(displayPanel.getImages().size() >= displayPanel.getType().getCapacity()) {
                return;
            }

            File file = new File(BASE_PATH + loadMetaverseName + IMAGES_PATH + fileName);
            if(!file.exists()) {
                return;
            }

            byte[] fileContent = new byte[0];
            String originalFilename = null;
            String contentType = null;
            try {
                fileContent = Files.readAllBytes(file.toPath());
                originalFilename  = file.getName();
                contentType = Files.probeContentType(file.toPath());
            } catch (IOException e) {
                throw new RuntimeException("Error in image creation: " + fileName);
            }

            MultipartFile multipartFile = new CustomMultipartFile(fileContent, originalFilename, contentType);

            Image image = new Image();
            image.setTitle(title);
            image.setFileName(originalFilename);
            image.setDisplayPanels(List.of(displayPanel));
            displayPanel.getImages().add(image);

            image.setCreator(userService.getUser(UserService.ADMIN1));

            if(imageService.createFile(image, multipartFile, displayPanel)) {
                imageService.addNewImage(image);
                displayPanelService.addNewDisplayPanel(displayPanel);
            }
        }
    }

    @Transactional
    public void createAudio(Map<String, Object> a) {
        String title = (String) a.get("title");
        String fileName = (String) a.get("fileName");
        String imageTitle = (String) a.get("image");

        Image image = imageService.getImageByTitle(imageTitle);
        if(audioService.getAudioByTitle(title) == null && image != null) {
            if(image.getAudio() != null) {
                return;
            }

            File file = new File(BASE_PATH + loadMetaverseName + AUDIOS_PATH + fileName);
            if(!file.exists()) {
                return;
            }

            byte[] fileContent = new byte[0];
            String originalFilename = null;
            String contentType = null;
            try {
                fileContent = Files.readAllBytes(file.toPath());
                originalFilename  = file.getName();
                contentType = Files.probeContentType(file.toPath());
            } catch (IOException e) {
                throw new RuntimeException("Error in audio creation: " + fileName);
            }

            MultipartFile multipartFile = new CustomMultipartFile(fileContent, originalFilename, contentType);

            Audio audio = new Audio();
            audio.setTitle(title);
            audio.setFileName(originalFilename);
            audio.setImage(image);
            image.setAudio(audio);

            audio.setCreator(userService.getUser(UserService.ADMIN1));

            for(DisplayPanel displayPanel : image.getDisplayPanels()) {
                if(!audioService.createFile(audio, multipartFile, image, displayPanel)) {
                    return;
                }
            }

            audioService.addNewAudio(audio);
            imageService.addNewImage(image);
        }
    }

    @Override
    public int getOrder() {
        return 2;
    }
}

