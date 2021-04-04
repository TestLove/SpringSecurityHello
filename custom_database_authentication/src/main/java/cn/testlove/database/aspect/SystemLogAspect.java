package cn.testlove.database.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author testlove
 */
@Aspect
@Slf4j
@Component
public class SystemLogAspect {
    /**
     * 切点：声明需要加入 log 连接点集合
     *
     */
    @Pointcut("@annotation(cn.testlove.database.aspect.SystemLog)")
    public void logAspectPointCut () {}

    @Around("logAspectPointCut()")
    public Object before(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
       String methodName = method.getName();

        // 请求的参数
        Object object = Arrays.stream(joinPoint.getArgs())
                .filter(t -> !(t instanceof ServletRequest) && !(t instanceof ServletResponse) && !(t instanceof MultipartFile)
                        && !(t instanceof MultipartFile[])
                        && !(t instanceof HttpSession) && !(t instanceof Model) && !(t instanceof BindingResult))
                .collect(Collectors.toList());
        log.info("================"+methodName+"LogBegin=====================");
        log.info("开始执行方法: "+method.toString());
        log.info(methodName+"参数个数为"+method.getParameterCount());
        log.info(methodName+"参数为"+Arrays.toString(joinPoint.getArgs()));
        Object obj = joinPoint.proceed();
        log.info("结束执行"+methodName+"方法");
        if (obj!=null){
            log.info("返回值为: "+obj.getClass().getName()+": "+obj.getClass().getFields());
//            log.info("返回值为: "+obj.getClass().getName()+Arrays.toString(obj.getClass().getDeclaredFields()));
            log.info("================"+methodName+"LogEnd=====================");
            return (method.getReturnType().cast(obj));
        }else {
            return null;
        }

//        log.info("开始执行+"+methodName+"方法"
//                + "signature.toLongString(): " +signature.toLongString()
//                +"signature.getParameterTypes(): "+signature.getParameterTypes()
//                        +"signature.getParameterTypes(): "+signature.getParameterNames()
//                +"signature.getDeclaringTypeName(): "+signature.getDeclaringTypeName()
//                +"signature.getDeclaringType(): "+signature.getDeclaringType()
//                        +"signature.toShortString(): "+signature.toShortString()
//                        +"method.toString()"+method.toString()
//                );


    }
//    @After("@annotation(cn.testlove.security.aspect.SystemLog)")
//    public void after(JoinPoint joinPoint) {
//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//        Method method = signature.getMethod();
//        String methodName = signature.getName();
//        signature.getName();
//        String name = method.getName();
//        method.getReturnType().toString();
//        log.info("结束执行"+methodName+"方法");
//        log.info("返回值为"+)
//        log.info("================LogEnd=====================");
//
//    }
    private String getFieldsValues(Object obj){
        if (obj == null) {
            return null;
        }
        Field[] fields = obj.getClass().getDeclaredFields();
        String info="";
        for (int j = 0; j < fields.length; j++) {
            fields[j].setAccessible(true);
            // 字段名
           info=info+fields[j].getName() + ",";
            // 字段值
            if (fields[j].getType().getName().equals(
                    String.class.getName())) {
                // String type
                try {
                  info=info+fields[j].get(obj)+";";
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else if (fields[j].getType().getName().equals(
                    Integer.class.getName())
                    || fields[j].getType().getName().equals("int")) {
                // Integer type
                try {
                    System.out.println(fields[j].getInt(obj));
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            // 其他类型。。。
        }
        return info;
    }
}
