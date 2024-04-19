package metacampus2.initializer;

import com.sun.tools.javac.Main;
import metacampus2.controller.MainController;
import metacampus2.model.*;
import metacampus2.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Component
public class EntitiesInitializer implements CommandLineRunner {
    private IMetaverseService metaverseService;
    private ITextPanelService textPanelService;
    private IDisplayPanelService displayPanelService;
    private ISpaceService spaceService;


    @Autowired
    public EntitiesInitializer(IMetaverseService metaverseService,
                               ITextPanelService textPanelService,
                               IDisplayPanelService displayPanelService,
                               ISpaceService spaceService) {
        this.metaverseService = metaverseService;
        this.textPanelService = textPanelService;
        this.displayPanelService = displayPanelService;
        this.spaceService = spaceService;
    }

    @Override
    public void run(String... args) throws Exception {
        /*Yaml yaml = new Yaml();

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
                    }
                }
            }
        }*/
    }

    protected void createMetaverse(Map<String, Object> m) {
        String name = (String) m.get("name");

        if (metaverseService.getMetaverse(name) == null) {
            Metaverse metaverse = new Metaverse();
            metaverse.setName((String) m.get("name"));

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
        Metaverse metaverse = metaverseService.getMetaverse(metaverseName);

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
        Metaverse metaverse = metaverseService.getMetaverse(metaverseName);

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

            if(displayPanelService.createDirectory(displayPanel)) {
                displayPanelService.addNewDisplayPanel(displayPanel);
            }
        }
    }
}

