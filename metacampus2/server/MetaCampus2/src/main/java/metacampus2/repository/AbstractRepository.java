package metacampus2.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

import java.nio.file.FileSystems;

@Getter
public abstract class AbstractRepository {
    protected static final String SEPARATOR = FileSystems.getDefault().getSeparator();
    protected static final String RESOURCES_PATH = "." + SEPARATOR + "resources" + SEPARATOR;
    protected static final String METAVERSES_PATH = RESOURCES_PATH + "metaverses" + SEPARATOR;
    protected static final String TEXT_PANELS_PATH = "text-panels" + SEPARATOR;
    protected static final String DISPLAY_PANELS_PATH = "display-panels" + SEPARATOR;
    protected static final String TEXT_PATH = "text" + SEPARATOR;
    protected static final String IMAGES_PATH = "images" + SEPARATOR;
    protected static final String AUDIO_PATH = "audio" + SEPARATOR;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public String getPathName(String name) {
        return name
                .toLowerCase()
                .replaceAll("[*!?,.+/]+", "-")
                .replaceAll(" ", "-");
    }
}
