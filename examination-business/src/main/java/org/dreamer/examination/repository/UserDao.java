package org.dreamer.examination.repository;

import org.dreamer.examination.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
public interface UserDao extends JpaRepository<User,Long> {
}
