package org.dreamer.examination.repository;

import org.dreamer.examination.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
public interface UserDao extends JpaRepository<User,Long> {

    public List<User> findByUserName(String userName);
}
