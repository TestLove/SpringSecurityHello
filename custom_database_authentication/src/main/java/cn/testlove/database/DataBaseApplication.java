package cn.testlove.database;

import cn.testlove.database.util.SpringBeanUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author testlove
 */
@SpringBootApplication
public class DataBaseApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(DataBaseApplication.class, args);
        SpringBeanUtils.setApplicationContext(run);
    }
}
