package com.pte.project.aspect;

import java.util.logging.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    
    private static final Logger logger = Logger.getLogger("com.pte.project.aspect.LoggingAspect");
    
    @Pointcut("execution(* com.pte.project.*.*.*(..))")
    
    public void loggingPointcut() {
    }

//the following advice does the logging for every method from every class from every package inside of com.pte (it is about the whole block)    
    // @Around("com.pte.project.aspect.LoggingAspect") 
    @Around("loggingPointcut()")
    public Object loggingAdvice(ProceedingJoinPoint pjp) throws Throwable {
        
        String className = pjp.getTarget().getClass().toString();
        String methodName = pjp.getSignature().getName();
        
        logger.log(java.util.logging.Level.INFO, "Inside " + className + " class " + methodName + "method.");
        
        return pjp.proceed();
    }
//  (*any kind of return type)           package, class, method
    @Before("execution(* com.pte.project.controller.*.get*(..))") //includes all get methods
    public void logStatementBefore(){
        logger.log(java.util.logging.Level.INFO, "Execution");
    }

    @After("execution(* com.pte.project.*.*.*(..))")
    public void logStatementAfter() {
        logger.log(java.util.logging.Level.INFO, "Execution completed.");
    }
    
}
