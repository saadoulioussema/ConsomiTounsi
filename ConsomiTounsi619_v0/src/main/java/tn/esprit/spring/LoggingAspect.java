package tn.esprit.spring;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class LoggingAspect {

	private static final Logger logger = LogManager.getLogger(LoggingAspect.class);
	@Before("execution(* tn.esprit.spring.sevice.impl.PanierServiceImpl.*(..))")
	public void logMethodEntry(JoinPoint joinPoint) {
	String name = joinPoint.getSignature().getName();
	logger.debug("In method " + name + " : ");
	}
	@After("execution(* tn.esprit.spring.sevice.impl.PanierServiceImpl.*(..))")
	public void logMethodExit(JoinPoint joinPoint) {
	String name = joinPoint.getSignature().getName();
	logger.debug("Out of " + name );
	}
	
	
	@Before("execution(* tn.esprit.spring.sevice.impl.Product_LineServiceImpl.*(..))")
	public void logMethodEntry1(JoinPoint joinPoint) {
	String name = joinPoint.getSignature().getName();
	logger.debug("In method " + name + " : ");
	}
	@After("execution(* tn.esprit.spring.sevice.impl.Product_LineServiceImpl.*(..))")
	public void logMethodExit1(JoinPoint joinPoint) {
	String name = joinPoint.getSignature().getName();
	logger.debug("Out of " + name );
	}
	
	@Before("execution(* tn.esprit.spring.sevice.impl.SubjectService.*(..))")
	public void logMethodEntryy(JoinPoint joinPoint) {
	String name = joinPoint.getSignature().getName();
	logger.debug("In method " + name + " : ");
	}
	
	
	@Around("execution(* tn.esprit.spring.sevice.impl.SubjectService.*(..))")
	public Object profileAllMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable 
    {

     
    //Get intercepted method details
     
    final StopWatch stopWatch = new StopWatch();
     
    //Measure method execution time
    stopWatch.start();
    Object result = proceedingJoinPoint.proceed();
    stopWatch.stop();

    //Log method execution time
    logger.info("Execution time : "  + stopWatch.getTotalTimeMillis() + " ms");

    return result;
}
	
	@After("execution(* tn.esprit.spring.sevice.impl.SubjectService.*(..))")
	public void logMethodExitt(JoinPoint joinPoint) {
	String name = joinPoint.getSignature().getName();
	logger.debug("Out of " + name );
	}
	
	

	
	
}
