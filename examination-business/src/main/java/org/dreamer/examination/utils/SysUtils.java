package org.dreamer.examination.utils;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
@Component
public class SysUtils implements ApplicationContextAware {

    private static String APP_CONFIG_PROP = "appConfig";
    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    /**
     * 获得系统的某一个Bean
     *
     * @param name
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return SysUtils.context.getBean(name, clazz);
    }

    /**
     * 按类型获得Bean
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clazz){
        return SysUtils.context.getBean(clazz);
    }

    /**
     * 获得应用系统配置信息
     *
     * @return
     */
    public static Properties getAppConfigProp() {
        return getBean(APP_CONFIG_PROP, Properties.class);
    }

    /**
     * 获得具体的配置值
     *
     * @param propName
     * @return
     */
    public static String getConfigValue(String propName) {
        return SysUtils.getAppConfigProp().getProperty(propName);
    }

    /**
     * 获得具体的配置值，当配置项不存在的时候返回指定的默认值
     *
     * @param propName
     * @param defaultValue
     * @return
     */
    public static String getConfigValue(String propName, String defaultValue) {
        return SysUtils.getAppConfigProp().getProperty(propName, defaultValue);
    }

    public static String hashPwd(String pwd,String salt){
        Md5Hash hash = new Md5Hash(pwd);
        String p2 = hash.toString()+salt;
        hash = new Md5Hash(p2);
        return hash.toString();
    }
}
