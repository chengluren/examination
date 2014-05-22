package org.dreamer.examination.rbac;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by lcheng on 2014/5/20.
 */
public class ShiroV9Filter extends AuthenticatingFilter {

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
        String userName = request.getParameter("u");
        String password = request.getParameter("p");
        AesCipherService aes = new AesCipherService();
        aes.setKeySize(128);
        byte[] key = getKey();
        password = new String(aes.decrypt(Base64.decode(password), key).getBytes());
        assert password.length() > 6;
        password = password.substring(0,(password.length()-6));
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        return token;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        return executeLogin(request, response);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return false;
    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
                                     ServletResponse response) throws Exception {
        issueSuccessRedirect(request, response);
        return false;
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e,
                                     ServletRequest request, ServletResponse response) {
        return false;
    }

    private byte[] getKey() {
        InputStream is = this.getClass().getResourceAsStream("/key.txt");
        if (is != null) {
            try {
                byte[] buffer = new byte[is.available()];
                is.read(buffer);
                return buffer;
            } catch (IOException e) {
                e.printStackTrace();
                return "by-jd_123".getBytes();
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            return "by-jd_123".getBytes();
        }
    }
}
