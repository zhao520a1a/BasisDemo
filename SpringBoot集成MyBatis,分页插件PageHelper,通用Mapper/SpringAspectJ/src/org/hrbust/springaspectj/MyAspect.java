package org.hrbust.springaspectj;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Created by Chris on 2017/4/6.
 */
//@Aspect
public class MyAspect {

//    @Pointcut("execution(* greetTo(..))")
    private void anyMethod(){}


//    @Before("execution(* greetTo(..)) && args(name)")
    public void doBefore(String name){
        System.out.println("say hello to " + name);
    }


//    @AfterReturning(value="execution(* serveTo(..))", returning="retVal")
    public void doAfterReturning(int retVal)
    {
        System.out.println("return value is " + retVal);
    }


//    @AfterThrowing(value="execution(* greetTo(..))", throwing="ex")
    public void doThrowing(Exception ex){
        System.out.println("Message in exception is " + ex.getMessage());
    }

//    @Around("anyMethod()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable{


        System.out.println("do something before in around");
        Object o = pjp.proceed();
        System.out.println("do something after in around");
        return o;
    }

//    @After("anyMethod()")
    public void doAfter(){
        System.out.println("do something finally");
    }

}
