package org.dreamer.examination.entity;

import javax.persistence.*;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
@Entity
@Table(name="ea_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ID_USERS")
    @TableGenerator(name = "ID_USERS", table = "gen_ids", pkColumnName = "id_name",
            valueColumnName = "id_value", initialValue = 1)
    private Long id;

    @Column(name = "username",length = 50)
    private String userName;
    @Column(name = "password",length = 80)
    private String password;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
