package metacampus2.model;

import lombok.Getter;

@Getter
public enum DisplayPanelType {
    SINGLE("single", "can contain up to two images", 2),
    SIX_PACK("six-pack", "can contain up to twelve images", 12);

    private final String name;
    private final String description;
    private final int capacity;


    DisplayPanelType(String name, String description, int capacity) {
        this.name = name;
        this.description = description;
        this.capacity = capacity;
    }

    public static DisplayPanelType[] getAllDisplayPanelTypes() {
        return DisplayPanelType.values();
    }
}
