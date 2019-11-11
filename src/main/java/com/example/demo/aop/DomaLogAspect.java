package com.example.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DomaLogAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.example.demo.doma.dao.BaseDao+.*(..))")
    public void BaseDaoMethods() {
    }

    //    @Before("execution(* com.example.demo.doma.dao.BaseDao+.*(..))")
    //    @Before("target(com.example.demo.doma.dao.BaseDao) && @target(org.springframework.stereotype.Repository)")
    @Before("BaseDaoMethods()")
    public void BeforeInsert(JoinPoint joinPoint) {
        logger.info("dddddddd");
    }
}
