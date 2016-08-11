package colin.web.monitoring.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by Administrator on 2016/5/6.
 */
@Aspect
@Component
public class CommonLoggerAspect {

	private static final Logger logger = LoggerFactory
			.getLogger(CommonLoggerAspect.class);

	@Pointcut(value = "execution(* colin.web.monitoring.controller.*.*(..))")
	public void interceptorMethod() {

	}

	@Around("interceptorMethod()")
	public void addLogInfo(ProceedingJoinPoint call) {
		MethodSignature signature = (MethodSignature) call.getSignature();
		Method requetMethod=signature.getMethod();
		logger.info("请求方法名是" +requetMethod.getName());
		RequestMapping requestAnno=requetMethod.getAnnotation(RequestMapping.class);
        if(!ObjectUtils.isEmpty(requestAnno)){
        	if(!ObjectUtils.isEmpty(requestAnno.value())&&requestAnno.value().length>0){
        		//获取目标类
        		Class<?> targetClazz=call.getTarget().getClass();
        		RequestMapping requestTargetAnno=targetClazz.getAnnotation(RequestMapping.class);
        		if(!ObjectUtils.isEmpty(requestTargetAnno)){
        			logger.info("进入访问请求" +requestTargetAnno.value()[0]+requestAnno.value()[0]);
        		}else{
        			logger.info("进入访问请求" +requestAnno.value()[0]);
        		}
        	}
        }
        try {
			call.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		logger.info("结束请求访问！");
		
	}
}
