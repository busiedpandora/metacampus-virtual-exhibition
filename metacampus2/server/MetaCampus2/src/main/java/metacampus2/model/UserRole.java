package metacampus2.model;

import lombok.Getter;

@Getter
public enum UserRole {
    ROLE_VISITOR("Visitor"), ROLE_CREATOR("Creator"), ROLE_ADMIN("Admin");

    private final String name;


    UserRole(String name) {
        this.name = name;
    }

    public static UserRole[] getAllUserRoles() {
        return UserRole.values();
    }

    public static UserRole getUserRoleByName(String name) {
        for (UserRole userRole : UserRole.values()) {
            if (userRole.name.equalsIgnoreCase(name)) {
                return userRole;
            }
        }

        return null;
    }
}
