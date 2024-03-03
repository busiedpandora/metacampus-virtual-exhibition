package metacampus2.initializer;

import metacampus2.model.Classroom;
import metacampus2.model.Metaverse;
import metacampus2.model.Office;
import metacampus2.service.ClassroomService;
import metacampus2.service.MetaverseService;
import metacampus2.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Component
public class EntitiesInitializer implements CommandLineRunner {
    @Autowired
    private MetaverseService metaverseService;
    @Autowired
    private ClassroomService classroomService;
    @Autowired
    private OfficeService officeService;


    @Override
    public void run(String... args) throws Exception {
        Yaml yaml = new Yaml();

        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream("static/project-data.yml");

        Map<String, Object> data = yaml.load(inputStream);

        List<Map<String, Object>> entities = (List<Map<String, Object>>) data.get("startupEntities");

        for(Map<String, Object> entity : entities) {
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

    private void createMetaverse(Map<String, Object> m) {
        String name = (String) m.get("name");

        if(metaverseService.getMetaverse(name) == null) {
            Metaverse metaverse = new Metaverse();
            metaverse.setName((String) m.get("name"));

            metaverseService.addNewMetaverse(metaverse);
        }
    }

    private void createClassroom(Map<String, Object> c) {
        String number = (String) c.get("number");
        String metaverseName = (String) c.get("metaverse");
        Metaverse metaverse = metaverseService.getMetaverse(metaverseName);

        if(metaverse != null &&
                classroomService.getClassroomFromMetaverse(number, metaverseName) == null) {
            Classroom classroom = new Classroom();
            classroom.setNumber(number);
            classroom.setMetaverse(metaverse);

            classroomService.addNewClassroom(classroom);
        }
    }

    private void createOffice(Map<String, Object> o) {
        String number = (String) o.get("number");
        String metaverseName = (String) o.get("metaverse");
        Metaverse metaverse = metaverseService.getMetaverse(metaverseName);

        if(metaverse != null &&
                officeService.getOfficeFromMetaverse(number, metaverseName) == null) {
            Office office = new Office();
            office.setNumber(number);
            office.setMetaverse(metaverse);

            officeService.addNewOffice(office);
        }
    }
}
