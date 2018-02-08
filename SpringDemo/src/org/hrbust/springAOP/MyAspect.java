package org.hrbust.springAOP;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * Created by Chris on 2017/4/6.
 */
@Aspect
public class MyAspect {

    @Pointcut("execution(* greetTo(..))")
    private void anyMethod() {
    }

    @Before("execution(* greetTo(..)) && args(name)")
    public void doBefore(String name) {
        System.out.println("前置增强：方法参数-" + name);
    }

    @AfterReturning(value = "execution(* greetTo(..))", returning = "retVal")
    public void doAfterReturning(String retVal) {
        System.out.println("后置增强:返回值-" + retVal);
    }


    @AfterThrowing(value = "execution(* greetTo(..))", throwing = "ex")
    public void doThrowing(Exception ex) {
        System.out.println("异常增强，异常信息： " + ex.getMessage());
    }

    @Around("anyMethod()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("环绕增强：前置处理");
        Object o = pjp.proceed();
        System.out.println("环绕增强：后置处理");
        return o;
    }

    @After("anyMethod()")
    public void doAfter() {
        System.out.println("最终增强");
    }

}
