package cn.testlove.database.util;

import org.springframework.context.ApplicationContext;

/**
 * @author admin
 */
public class SpringBeanUtils {
    private static ApplicationContext applicationContext;
    public static void setApplicationContext(ApplicationContext applicationContext){
        SpringBeanUtils.applicationContext = applicationContext;
    }
    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }


}
