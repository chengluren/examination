package org.dreamer.examination.repository;

import org.dreamer.examination.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
public interface UserDao extends JpaRepository<User,Long> {

    public List<User> findByUserName(String userName);

    @Modifying
    @Query("update User set password = ?1 where id = ?2")
    public void updateUserPwd(String hashedPwd,Long uid);
}
