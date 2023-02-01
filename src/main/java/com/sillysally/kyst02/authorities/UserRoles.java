package com.sillysally.kyst02.authorities;

import java.util.ArrayList;
import java.util.List;

import static com.sillysally.kyst02.authorities.UserPermissions.*;

public enum UserRoles {
    USER(List.of(USER_READ, USER_WRITE, USER_DELETE)),
    ADMIN(List.of(ADMIN_READ, ADMIN_WRITE, ADMIN_DELETE));

    // Variable
    private final List<UserPermissions> permissionsList;

    // Constructor
    UserRoles(List<UserPermissions> permissions) {
        this.permissionsList = permissions;
    }

    // Getter
    public List<UserPermissions> getPermissions() {
        return permissionsList;
    }

    // Create list: [ROLE & PERMISSIONS]
    public List<String> getGrantedAuthorities() {

        // Loop
        List<String> permissionsList = new ArrayList<>(getPermissions().stream().map(
                UserPermissions::getUserPermission
        ).toList());

        // Add Role      (example ROLE_ADMIN)
        permissionsList.add(("ROLE_" + this.name()));

        return permissionsList;
    }
}
