package org.hrbust.springAOP;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 * Created by golden on 2017/4/23 0023.
 */
public class TestByProgramming {
    public static void main(String[] args) {
        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(new NaiveWaiter());

//        /*直接设置增强*/
//        pf.addAdvice(new MyBeforeAdvice());
//        pf.addAdvice(new MyAfterReturningAdvice());

        /*静态方法名匹配切面*/
//        MyStaticAdvisor advisor = new MyStaticAdvisor();
//        advisor.setAdvice(new MyBeforeAdvice());
        /*动态切面*/
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setPointcut(new MyPointcut());
        advisor.setAdvice(new MyBeforeAdvice());

        pf.addAdvisor(advisor);

        NaiveWaiter naiveWaiter = (NaiveWaiter) pf.getProxy();
        naiveWaiter.greetTo("Golden");
        naiveWaiter.serveTo("Golden");
    }
}
