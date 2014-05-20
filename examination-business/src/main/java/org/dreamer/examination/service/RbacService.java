package org.dreamer.examination.service;

import org.dreamer.examination.entity.RolePermission;
import org.dreamer.examination.entity.User;
import org.dreamer.examination.entity.UserRole;
import org.dreamer.examination.repository.RolePermissionDao;
import org.dreamer.examination.repository.UserDao;
import org.dreamer.examination.repository.UserRoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
@Service("rbacService")
public class RBACService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRoleDao userRoleDao;
    @Autowired
    private RolePermissionDao rolePermissionDao;

    public Page<User> getAllUser(Pageable page) {
        return userDao.findAll(page);
    }

    public List<User> getUserByUserName(String userName) {
        return userDao.findByUserName(userName);
    }

    public List<UserRole> getUserRole(String userName) {
        return userRoleDao.findByUserName(userName);
    }

    public List<String> getUserRoleStr(String userName){
         return userRoleDao.findUserRoleNames(userName);
    }

    public List<RolePermission> getRolePermissions(String roleName) {
        return rolePermissionDao.findByRoleName(roleName);
    }

    public List<String> getRolePermissionStr(String roleName){
        return rolePermissionDao.findRolePermissions(roleName);
    }

    public void addUser(User user) {
        userDao.save(user);
    }

    public void addUserRole(UserRole userRole) {
        userRoleDao.save(userRole);
    }

    public void addRolePermission(RolePermission rolePermission) {
        rolePermissionDao.save(rolePermission);
    }

    public void deleteUser(Long id) {
        userDao.delete(id);
    }

    public void deleteUserRole(Long id) {
        userRoleDao.delete(id);
    }

    public void deleteRolePermission(Long id) {
        rolePermissionDao.delete(id);
    }
}
