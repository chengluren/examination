package org.dreamer.examination.entity;

import javax.persistence.*;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
@Entity
@Table(name="ea_roles_permissions")
public class RolePermission {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ID_PERMISSIONS")
    @TableGenerator(name = "ID_PERMISSIONS", table = "gen_ids", pkColumnName = "id_name",
            valueColumnName = "id_value", initialValue = 1)
    private Long id;

    @Column(name = "role_name",length = 50)
    private String roleName;
    @Column(length = 80)
    private String permission;
    @Column(length = 80)
    private String permissionName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }
}
