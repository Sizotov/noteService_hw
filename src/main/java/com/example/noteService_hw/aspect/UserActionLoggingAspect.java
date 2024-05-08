package com.example.noteService_hw.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
//Имплементируем аспект для логирования
@Aspect
public class UserActionLoggingAspect {

    @Before("@annotation(com.example.noteService_hw.TrackUserAction)")
    public void logUserAction(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.println("Пользователь выполнил действие: " + methodName + " с параметрами: " + args);
    }
}
