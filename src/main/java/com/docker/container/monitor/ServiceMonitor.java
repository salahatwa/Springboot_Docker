package com.docker.container.monitor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author atwa Jun 24, 2018
 */
@Aspect
@Component
public class ServiceMonitor {
	
	private static Logger logger=LoggerFactory.getLogger(ServiceMonitor.class);

	@Pointcut("execution(* com..*Controller.*(..)) || execution(* org.springframework.security.oauth2.provider.endpoint.TokenEndpoint.*(..))")
	public void decryptEncrypt() {
	}

	@Around("decryptEncrypt()")
	public Object logServiceAccess(ProceedingJoinPoint joinPoint) throws Throwable {

		Object object;
		logger.info(">>Before Method: {}" , joinPoint.getSignature().getName());
		try {
			object = joinPoint.proceed();
			
		} finally {
			// Do Something useful, If you have
		}

		logger.info(">>After Method: {} , Result is :{}" , joinPoint.getSignature().getName(),object);
		return object;
	}

}