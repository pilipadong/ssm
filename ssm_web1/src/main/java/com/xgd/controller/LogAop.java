package com.xgd.controller;

import com.xgd.pojo.SysLog;
import com.xgd.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {

    private Date visitTime;
    private Class claszz;//访问类
    private Method method;//访问的方法

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private SysLogService sysLogService;


    //前置通知
    @Before("execution(* com.xgd.controller.*.*(..))")
    public void doBefor(JoinPoint joinPoint) throws NoSuchMethodException {
        visitTime=new Date();
        claszz= joinPoint.getTarget().getClass();//具体访问类
        //获取执行的方法名称
        String name = joinPoint.getSignature().getName();
        //获取访问的方法参数
        Object[] args = joinPoint.getArgs();
        if (args==null || args.length==0){
            //获取访问的方法参数
            method=claszz.getMethod(name);//只能获取到无参的构造方法
        }else{
            Class[] classArgs=new Class[args.length];

            for (int i = 0; i < args.length; i++) {
                 classArgs[i]=args[i].getClass();
            }
            //封装参数
            claszz.getMethod(name,classArgs);
        }




    }

    //后置通知
    @After("execution(* com.xgd.controller.*.*(..))")
    public void doAfter(){
    //访问时长
    long time=new Date().getTime()-visitTime.getTime();
    //获取操作的url   通过java反射的方式来获取
    String url="";
    if (claszz!=null && method!=null && claszz !=LogAop.class){
          //获取类上的requestMapping
        RequestMapping classAnnotation = (RequestMapping) claszz.getAnnotation(RequestMapping.class);
        if (classAnnotation!=null){
            //获取到类上的requestMapping上的value值
            String[] classvalue = classAnnotation.value();
            //获取方法上的requestMapping注解
            RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
            //取出里面的value值
            String[] methodvalue = methodAnnotation.value();
            url=classvalue[0]+methodvalue[0];
            //获取请求的ip地址
            String ip = request.getRemoteAddr();
            //获取当前操作的用户
            SecurityContext context=SecurityContextHolder.getContext();
            //获取到当前操作的用户
            User principal = (User) context.getAuthentication().getPrincipal();
            //获取用户名
            String username = principal.getUsername();

            SysLog sysLog=new SysLog();
            sysLog.setIp(ip);
            sysLog.setExecutionTime(time);
            //访问的方法
            sysLog.setMethod("[类名] "+claszz.getName()+"[方法名]"+method.getName());
            sysLog.setUrl(url);
            sysLog.setUsername(username);
            sysLog.setVisitTime(visitTime);
            sysLogService.save(sysLog);
        }
      }

    }
}
