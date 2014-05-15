package org.dreamer.examination.repository;

import org.dreamer.examination.entity.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
public interface RolePermissionDao extends JpaRepository<RolePermission,Long>{

    public List<RolePermission> findByRoleName(String roleName);
}
