package org.abhibane.spring.learning.aop.myFirstAspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TracingAspect {

	boolean isEntered = false;
	public boolean isEnteringCalled() {
		return isEntered;
	}

	boolean isExit = false;
	public boolean isExitCalled() {
		return isExit;
	}

	boolean isSuccessfulReturned = false;
	public boolean isSuccessfulReturnedCalled() {
		return isSuccessfulReturned;
	}
	
	boolean isThrowingReturned = false;
	public boolean isThrowingReturnedCalled() {
		return isThrowingReturned;
	}

	@Before("execution(void doSomething())")
	//@Before("execution(* *(..))")
	public void entering(JoinPoint joinPoint) {
		isEntered = true;
		//System.out.println("Entering " + joinPoint.getStaticPart().getSignature().toString());
		//System.out.println("Entering " + joinPoint.getStaticPart().getKind().toString());
		System.out.println("Entering " + joinPoint.getSignature().getName());
	}

	@After("execution(void doSomething())")
	//@After("execution(* *(..))")
	public void exit(JoinPoint joinPoint) {
		isExit = true;
		//System.out.println("Exiting " + joinPoint.getStaticPart().getSignature().toString());
		//System.out.println("Exiting " + joinPoint.getStaticPart().getKind().toString());
		System.out.println("Exiting " + joinPoint.getSignature().getName());
	}

	@AfterReturning("execution(void doSomething())")
	//@AfterReturning("execution(* *(..))")
	public void afterSuccessfulReturning(JoinPoint joinPoint) {
		isSuccessfulReturned = true;
		System.out.println("Exiting Successfully" + joinPoint.getSignature().getName());
	}

	@AfterThrowing(pointcut = "execution(void doException())", throwing = "ex")
	//@AfterThrowing("execution(* *(..))")
	public void afterThrowingReturning(Exception ex) {
		isThrowingReturned = true;
		System.out.println("Exiting With Exception" + ex);
	}
	
	
	boolean isAround = false;
	public boolean isAroundCalled() {
		return isAround;
	}
	@Around("execution(void doSomething())")
	//@Around("execution(* *(..))")
	public void isAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		isAround = true;
		System.out.println("Enetering Successfully" + proceedingJoinPoint.getSignature().getName());
		proceedingJoinPoint.proceed();
		System.out.println("Exiting Successfully" + proceedingJoinPoint.getSignature().getName());
	}
}
