package org.dreamer.examination.repository;

import org.dreamer.examination.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
public interface UserRoleDao extends JpaRepository<UserRole,Long>{

    public List<UserRole> findByUserName(String userName);
}
