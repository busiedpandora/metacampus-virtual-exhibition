package metacampus2.initializer;

import metacampus2.model.Classroom;
import metacampus2.model.Location;
import metacampus2.model.Metaverse;
import metacampus2.model.Office;
import metacampus2.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Component
public class EntitiesInitializer implements CommandLineRunner {
    private IMetaverseService metaverseService;
    private IClassroomService classroomService;
    private IOfficeService officeService;

    @Autowired
    public EntitiesInitializer(IMetaverseService metaverseService, IClassroomService classroomService,
                               IOfficeService officeService) {
        this.metaverseService = metaverseService;
        this.classroomService = classroomService;
        this.officeService = officeService;
    }

    @Override
    public void run(String... args) throws Exception {
        Yaml yaml = new Yaml();

        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream("static/project-data.yml");

        if(inputStream != null) {
            Map<String, Object> data = yaml.load(inputStream);

            List<Map<String, Object>> entities = (List<Map<String, Object>>) data.get("startupEntities");

            if(entities != null) {
                for (Map<String, Object> entity : entities) {
                    String type = (String) entity.get("type");

                    switch (type) {
                        case "metaverse":
                            createMetaverse(entity);
                            break;
                        case "classroom":
                            createClassroom(entity);
                            break;
                        case "office":
                            createOffice(entity);
                            break;
                    }
                }
            }
        }
    }

    protected void createMetaverse(Map<String, Object> m) {
        String name = (String) m.get("name");

        if(metaverseService.getMetaverse(name) == null) {
            Metaverse metaverse = new Metaverse();
            metaverse.setName((String) m.get("name"));

            metaverseService.addNewMetaverse(metaverse);
        }
    }

    protected void createClassroom(Map<String, Object> c) {
        String number = (String) c.get("number");
        Map<String, Object> location = (Map<String, Object>) c.get("location");
        int floorNumber = (int) location.get("floorNumber");
        int xPosition = (int) location.get("xPosition");
        int zPosition = (int) location.get("zPosition");
        String metaverseName = (String) c.get("metaverse");
        Metaverse metaverse = metaverseService.getMetaverse(metaverseName);

        if(metaverse != null &&
                classroomService.getClassroomFromMetaverse(number, metaverseName) == null) {
            Classroom classroom = new Classroom();
            classroom.setNumber(number);
            classroom.setMetaverse(metaverse);

            Location l = new Location();
            l.setFloorNumber(floorNumber);
            l.setXPosition(xPosition);
            l.setZPosition(zPosition);
            classroom.setLocation(l);

            classroomService.addNewClassroom(classroom);
        }
    }

    protected void createOffice(Map<String, Object> o) {
        String number = (String) o.get("number");
        Map<String, Object> location = (Map<String, Object>) o.get("location");
        int floorNumber = (int) location.get("floorNumber");
        int xPosition = (int) location.get("xPosition");
        int zPosition = (int) location.get("zPosition");
        String metaverseName = (String) o.get("metaverse");
        Metaverse metaverse = metaverseService.getMetaverse(metaverseName);

        if(metaverse != null &&
                officeService.getOfficeFromMetaverse(number, metaverseName) == null) {
            Office office = new Office();
            office.setNumber(number);
            office.setMetaverse(metaverse);

            Location l = new Location();
            l.setFloorNumber(floorNumber);
            l.setXPosition(xPosition);
            l.setZPosition(zPosition);
            office.setLocation(l);

            officeService.addNewOffice(office);
        }
    }
}
