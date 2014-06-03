package org.dreamer.examination.repository;

import org.dreamer.examination.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
public interface UserRoleDao extends JpaRepository<UserRole,Long>{

    public List<UserRole> findByUserName(String userName);

    @Query("select r.roleName from UserRole r where r.userName = ?1")
    public List<String> findUserRoleNames(String userName);

    @Query(value = "select r.rolename from jiaoda_admin_role r where r.roleid = ?1",nativeQuery = true)
    List<String> findAdminUserCollege(Long roleId);
}
