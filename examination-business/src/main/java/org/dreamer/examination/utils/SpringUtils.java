package org.dreamer.examination.utils;

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
public class SpringUtils implements ApplicationContextAware {

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
        return SpringUtils.context.getBean(name, clazz);
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
        return SpringUtils.getAppConfigProp().getProperty(propName);
    }

    /**
     * 获得具体的配置值，当配置项不存在的时候返回指定的默认值
     *
     * @param propName
     * @param defaultValue
     * @return
     */
    public static String getConfigValue(String propName, String defaultValue) {
        return SpringUtils.getAppConfigProp().getProperty(propName, defaultValue);
    }
}
