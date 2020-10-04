package com.spring.utils;


import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Logger {
    @Before("execution(* com.spring.controller..*.*(..))")
    public void logBegin(){
        System.out.println("*************** Started processing request ***********");
    }

    @After("execution(* com.spring.controller..*.*(..))")
    public void logEnd(){
        System.out.println("************ Finished processing Request *************");
    }
}
