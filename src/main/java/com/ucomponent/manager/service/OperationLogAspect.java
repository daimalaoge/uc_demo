package com.ucomponent.manager.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ucomponent.base.annotation.ActionName;
import com.ucomponent.po.UcmOperationLog;
import com.ucomponent.repository.UcmOperationLogRepository;
import com.ucomponent.utils.JsonUtil;
/**
 * 2018年9月30日
 * 代码老哥
 * NAME:系统日志记录
 * Descp:
**/
@Aspect
@Component
public class OperationLogAspect {
  //定义切点 @Pointcut
  private HttpServletRequest request = null;
  @Autowired
  private UcmOperationLogRepository ucmOperationLogRepository;
  private static final ThreadLocal<Date> beginTimeThreadLocal = new NamedThreadLocal<Date>("ThreadLocal beginTime");
  private String title = "";
  private String exception = "";
  private String result = "";
  
  //@Before:  前置通知
  @Before("execution (* com.ucomponent..controller..*.*(..))")
  public void before(JoinPoint joinPoint){  
    request = getHttpServletRequest();  
  	beginTimeThreadLocal.set(new Date());
  }  
  
  @Pointcut("@annotation( com.ucomponent.base.annotation.ActionName)")
  public void logPoinCut() {
    System.out.println("OperationLogAspect - logPoinCut");
  }
  
	/**
	 * 环绕通知需要携带ProceedingJoinPoint类型的参数
	 * 环绕通知类似于动态代理的全过程：ProceedingJoinPoint类型的参数可以决定是否执行目标方法。
	 * 而且环绕通知必须有返回值，返回值即为目标方法的返回值
	 */
	@Around("execution(public String com.ucomponent..controller..*(..)) && @annotation(actionname)")
	public Object aroundMethod(ProceedingJoinPoint joinPoint,ActionName actionname) {
		Object result = null;
		this.title = actionname.value();
		// 执行目标方法
		try {
			result = joinPoint.proceed();
			// 返回通知
		} catch (Throwable e) {
			// 异常通知
			this.exception = e.toString();
			throw new RuntimeException(e);
		}
		// 后置通知
		this.result = result.toString();
		return result;
	}

  @After("logPoinCut()")  
  public  void after(JoinPoint joinPoint) {  
  	List<Object> args = Arrays.asList(joinPoint.getArgs());
    Map<String, String[]> params = request.getParameterMap(); // 请求提交的参数
    String classType = joinPoint.getTarget().getClass().getName();
		Class<?> clazz;
		String clazzName = "";
		try {
			clazz = Class.forName(classType);
			clazzName = clazz.getName();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String methodName = clazzName + "." + joinPoint.getSignature().getName();
    // 打印JVM信息。
    long beginTime = beginTimeThreadLocal.get().getTime();// 得到线程绑定的局部变量（开始时间）
    long endTime = System.currentTimeMillis(); // 2、结束时间  

    UcmOperationLog log = new UcmOperationLog();
    log.setTitle(this.title);
    
    if(this.exception.equals("")){
    	log.setCodesetOplogtype("OPLOG_INFO");
    }else{
    	log.setCodesetOplogtype("OPLOG_ERROR");
    }
    log.setRemoteAddr(request.getRemoteAddr());
    log.setRequestUri(request.getRequestURI());
    log.setMethod(request.getMethod());
    log.setClassFunc(methodName);
    log.setParams(JsonUtil.object2Json(params));
    log.setException(exception);
    log.setUserId(0);
    log.setResult(this.result);
    log.setCreateDatetime(beginTimeThreadLocal.get());
    log.setRuntime(endTime - beginTime);
    ucmOperationLogRepository.save(log); 
  }
  
  public HttpServletRequest getHttpServletRequest(){
    RequestAttributes ra = RequestContextHolder.getRequestAttributes();  
    ServletRequestAttributes sra = (ServletRequestAttributes)ra;  
    HttpServletRequest request = sra.getRequest();
    return request;
  }
}
