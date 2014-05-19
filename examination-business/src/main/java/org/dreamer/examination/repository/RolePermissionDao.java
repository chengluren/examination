package org.dreamer.examination.repository;

import org.dreamer.examination.entity.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
public interface RolePermissionDao extends JpaRepository<RolePermission,Long>{

    public List<RolePermission> findByRoleName(String roleName);

    @Query("select p.permission from RolePermission p where p.roleName = ?1")
    public List<String> findRolePermissions(String roleName);
}
