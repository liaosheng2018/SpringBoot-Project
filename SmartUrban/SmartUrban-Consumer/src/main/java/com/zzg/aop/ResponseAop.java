package com.zzg.aop;

import com.zzg.common.vo.Response;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 统一响应aop
 */

@Aspect
@Component
public class ResponseAop {

    /** 匹配com.zzg.controller 包及其子包下的所有方法 */
    @Pointcut("execution(* com.zzg.controller..*.*(..))")
    public void httpResponseAop(){

    }

    //环切
    @Around("httpResponseAop()")
    public Response handlerController(ProceedingJoinPoint proceedingJoinPoint) {
        Response result = null;
        try {
            //获取方法的执行结果
            Object proceed = proceedingJoinPoint.proceed();
            //如果方法的执行结果是Result，则将该对象直接返回
            if (proceed instanceof Response) {
                result = (Response) proceed;
            } else {
                //否则，就要封装到Result的data中
                result =Response.success(proceed);
            }
        }  catch (Throwable throwable) {
            //如果出现了异常，调用异常处理方法将错误信息封装到Result中并返回
            result = handlerException(throwable);
        }
        return result;
    }

    //异常处理
    private Response handlerException(Throwable throwable) {
        Response result = null;
        //这里需要注意，返回枚举类中的枚举在写的时候应该和异常的名称相对应，以便动态的获取异常代码和异常信息，我这边是统一返回500，msg存报的异常
        //获取异常名称的方法
        String errorName = throwable.toString();
        errorName = errorName.substring(errorName.lastIndexOf(".") + 1);
        result = Response.error(errorName);
        return result;
    }

}
