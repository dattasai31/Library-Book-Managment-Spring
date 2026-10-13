package com.example.demo.AOP;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
@Aspect
public class ExecutionTimeAspect {
	@Around("execution(* com.example.demo.Controller.LibraryController.*(..))")
	public Object executiontime(ProceedingJoinPoint jp) throws Throwable
	{
		String method=jp.getSignature().getName();
		System.out.println("Execution started for this method: "+method);
		long start=System.currentTimeMillis();
		try 
		{
			return jp.proceed();
		}
		finally 
		{
			long end=System.currentTimeMillis();
			System.out.println("Execution completed in:"+(end-start)+" ms");
			System.out.println("---------------------------------------------");
			
		}
		
	}
	
	
}
