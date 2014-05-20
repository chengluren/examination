package org.dreamer.examination.rbac;

import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.dreamer.examination.entity.User;
import org.dreamer.examination.entity.UserRole;
import org.dreamer.examination.service.RBACService;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.List;

/**
 * Created by lcheng on 2014/5/2.
 */
public class ShiroDatabaseRealm extends AuthorizingRealm {

    private static final String HASH_ALGORITHM = "md5";
    private static final int HASH_ITERATION = 2;

    private RBACService rbacService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
        String userName = shiroUser.getUserName();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        List<String> userRoleStr = rbacService.getUserRoleStr(userName);
        info.addRoles(userRoleStr);
        for (String roleName : userRoleStr) {
            info.addStringPermissions(rbacService.getRolePermissionStr(roleName));
        }
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
            throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String userName = token.getUsername();
        List<User> users = rbacService.getUserByUserName(userName);
        if (users != null && users.size() > 0) {
            User user = users.get(0);
            ShiroUser shiroUser = new ShiroUser(user.getId(), user.getUserName(), user.getRealName());
            SimpleAuthenticationInfo authenInfo = new SimpleAuthenticationInfo(shiroUser, user.getPassword(),
                    ByteSource.Util.bytes(user.getSalt()), getName());
            return authenInfo;
        }
        return null;
    }

    @PostConstruct
    public void initCredentialsMatcher() {
        CredentialsMatcher matcher = new InternalCredentialMatcher();
        setCredentialsMatcher(matcher);
    }

    public RBACService getRbacService() {
        return rbacService;
    }

    public void setRbacService(RBACService rbacService) {
        this.rbacService = rbacService;
    }

    public static class ShiroUser implements Serializable {
        private Long id;
        private String userName;
        private String realName;

        public ShiroUser(Long id, String userName, String realName) {
            this.id = id;
            this.userName = userName;
            this.realName = realName;
        }

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

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }
    }
}
