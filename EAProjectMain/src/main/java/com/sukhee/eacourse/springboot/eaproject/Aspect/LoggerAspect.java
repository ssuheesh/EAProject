package com.sukhee.eacourse.springboot.eaproject.Aspect;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;

@Aspect
@Component
public class LoggerAspect {

    private String prefix = "MYLOGGER | ";
    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void restControllerClasses() {}

    // Log HTTP request details before the controller method execution
    @Before("restControllerClasses()")
    public void logBefore(JoinPoint joinPoint) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            System.out.println(prefix + "HTTP Method: " + request.getMethod());
            System.out.println(prefix + "Request URL: " + request.getRequestURL().toString());
            System.out.println(prefix + "Controller: " + joinPoint.getSignature().getDeclaringTypeName());
            System.out.println(prefix + "Method: " + joinPoint.getSignature().getName());
            System.out.println(prefix + "Arguments: " + Arrays.toString(joinPoint.getArgs()));
        }
    }

    //No exception response logs.
    @AfterReturning(pointcut = "restControllerClasses()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.println(prefix + "Method executed: " + joinPoint.getSignature().getName());
        System.out.println(prefix + "Returned value: " + result);
    }

    //With Exception response logs
    @AfterThrowing(pointcut = "restControllerClasses()", throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        System.out.println(prefix + "Exception in method: " + joinPoint.getSignature().getName());
        System.out.println(prefix + "Exception: " + error.getMessage());
    }
}
