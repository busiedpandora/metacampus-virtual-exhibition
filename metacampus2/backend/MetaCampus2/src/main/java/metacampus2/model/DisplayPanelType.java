package metacampus2.model;

import lombok.Getter;

@Getter
public enum DisplayPanelType {
    ONE_DISPLAY_PANEL_EXHIBITION("one-display-panel-exhibition", "can contain up to two images", 2),
    SIX_DISPLAY_PANEL_DIAGONAL_EXHIBITION("six-display-panel-diagonal-exhibition", "can contain up to twelve images", 12),
    SIX_DISPLAY_PANEL_CIRCULAR_EXHIBITION("six-display-panel-circular-exhibition", "can contain up to twelve images", 12);

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

    public static DisplayPanelType getDisplayPanelTypeByName(String name) {
        for (DisplayPanelType panelType : DisplayPanelType.values()) {
            if (panelType.name.equalsIgnoreCase(name)) {
                return panelType;
            }
        }

        return null;
    }
}
