package cn.testlove.database.aspect;


import cn.testlove.database.enums.MethodType;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface SystemLog {
    String value() default "";
    MethodType type() default MethodType.SERVICE;
}
