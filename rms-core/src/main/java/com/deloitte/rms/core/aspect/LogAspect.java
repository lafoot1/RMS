package com.deloitte.rms.core.aspect;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.deloitte.rms.persistence.core.entity.AuditEntity;
import com.deloitte.rms.persistence.repo.AuditRepository;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@Aspect
public class LogAspect {
	private static final Logger LOG = LoggerFactory.getLogger(LogAspect.class);

	@Autowired ObjectMapper objectMapper;
	@Autowired AuditRepository auditReposiroty;
	
	@Around("execution(public * com.deloitte.rms.core.controller.*.*(..))")
	public Object logAfter(ProceedingJoinPoint joinPoint) {
		long started = System.currentTimeMillis();
		Object object = null;
		StringBuilder sb = new StringBuilder();
		Collection<String> requests = new ArrayList<>(joinPoint.getArgs().length);
		try {
			sb.append("Before: "+joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
			for(Object arg : joinPoint.getArgs()) {
				try {
				requests.add(objectMapper.writeValueAsString(arg));
				sb.append("("+objectMapper.writeValueAsString(arg)+", ");
				}catch (JsonMappingException e) {
					LOG.info("Cannot serialize: " + e.getMessage());
				}
			}
			if(sb.indexOf(", ") > -1) {
				sb.delete(sb.length()-2, sb.length()-1);
				sb.append(")");
			}
			LOG.trace("arguements: "+sb);
			object = joinPoint.proceed();
			long duration = System.currentTimeMillis() - started;
			String response = objectMapper.writeValueAsString(object);
			LOG.trace("Completed in ["+duration+" ms] "+joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName()
				+" [Returned: " + response +"]");
			log(joinPoint, duration, requests, response);
		} catch (Throwable e) {
			LOG.error(e.getMessage());
		}
		 return object;
	}
	
	@Async
	public void log(ProceedingJoinPoint joinPoint, long responseTime, Collection<String> requests, String response) {
		final String methodName = joinPoint.getSignature().getName();
		final MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
		Method method = methodSignature.getMethod();
	    try {
			method = joinPoint.getTarget().getClass().getDeclaredMethod(methodName, method.getParameterTypes());
			RequestMapping mappingAnnot = method.getDeclaredAnnotation(RequestMapping.class);
			if (mappingAnnot != null) {
				RequestMethod[] methods = mappingAnnot.method();
				
				//log only controller level classes
				AuditEntity audit = new AuditEntity();
				audit.setResponseTime(responseTime);
				audit.setServiceName(joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
				audit.setCreatedBy("AUDIT_LOGGER");
				audit.setResponse(response);
				audit.setRequest(Arrays.toString(requests.toArray()));
				audit.setSucceeded(Boolean.TRUE);
				audit.setRequestMethod(Arrays.toString(methods));
				audit.setPath(Arrays.toString(mappingAnnot.value()));
				
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				audit.setUserId(auth.getName());
				
				HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder
						.currentRequestAttributes()).getRequest(); 
				audit.setClientIp(request.getRemoteAddr());
				String userAgent = request.getHeader("User-Agent");
				audit.setUserAgent(userAgent);
				audit.setCreatedTs(Timestamp.valueOf(LocalDateTime.now()));
				auditReposiroty.save(audit);
			}
		} catch (NoSuchMethodException | SecurityException e) {
			LOG.info("Error while audit logging: " + e.getMessage());
		}   
	}
}
