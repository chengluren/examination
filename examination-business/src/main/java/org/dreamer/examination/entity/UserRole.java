package org.dreamer.examination.entity;

import javax.persistence.*;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
@Entity
@Table(name="ea_user_roles")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ID_USER_ROLES")
    @TableGenerator(name = "ID_USER_ROLES", table = "gen_ids", pkColumnName = "id_name",
            valueColumnName = "id_value", initialValue = 1)
    private Long id;

    @Column(name ="username",length = 50)
    private String userName;
    @Column(name="role_name",length = 50)
    private String roleName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
