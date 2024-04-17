package metacampus2.service;

import java.nio.file.FileSystems;

public abstract class AbstractService {
    protected static final String SEPARATOR = FileSystems.getDefault().getSeparator();
    protected static final String RESOURCES_PATH = "." + SEPARATOR + "resources" + SEPARATOR;
    protected static final String METAVERSES_PATH = RESOURCES_PATH + "metaverses" + SEPARATOR;
    protected static final String TEXT_PANELS_PATH = "text_panels" + SEPARATOR;
    protected static final String DISPLAY_PANELS_PATH = "display_panels" + SEPARATOR;
    protected static final String TEXT_PATH = "text" + SEPARATOR;
    protected static final String IMAGES_PATH = "images" + SEPARATOR;
    protected static final String AUDIO_PATH = "audio" + SEPARATOR;


    protected static String getUrlName(String name) {
        return name
                .toLowerCase()
                .replaceAll("[-*!?,.+/]+", "_")
                .replaceAll(" ", "_");
    }
}
