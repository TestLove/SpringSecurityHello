package cn.testlove.database.aspect;


import cn.testlove.database.enums.MethodType;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.*;

/**
 * @author admin
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited

public @interface SystemLog {
    String value() default "";
    MethodType type() default MethodType.SERVICE;
}
