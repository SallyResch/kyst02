package com.sillysally.kyst02.authorities;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Set;
import java.util.stream.Collectors;

import static com.sillysally.kyst02.authorities.UserPermissions.*;

public enum UserRoles {
    USER(Set.of(USER_READ, USER_WRITE, USER_DELETE_ACCOUNT)),
    COMPANY(Set.of(COMPANY_WRITE, COMPANY_READ)),
    ADMIN(Set.of(ADMIN_READ, ADMIN_WRITE, ADMIN_DELETE_POST));

    // Variable
    private final Set<UserPermissions> permissionsList;

    // Constructor
    UserRoles(Set<UserPermissions> permissions) {
        this.permissionsList = permissions;
    }

    // Getter
    public Set<UserPermissions> getPermissions() {
        return permissionsList;
    }

    // Create list: [ROLE & PERMISSIONS]
    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {

        // Loop
        Set<SimpleGrantedAuthority> permissionsSet = getPermissions().stream().map(index ->
                new SimpleGrantedAuthority(index.getUserPermission())).collect(Collectors.toSet());

        // Add Role      (example ROLE_ADMIN)
        permissionsSet.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return permissionsSet;
    }
}
