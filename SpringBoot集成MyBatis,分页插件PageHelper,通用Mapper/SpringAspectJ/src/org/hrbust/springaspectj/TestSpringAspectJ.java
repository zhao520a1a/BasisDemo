package org.hrbust.springaspectj;

import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Chris on 2017/4/6.
 */
public class TestSpringAspectJ {
    public static void main(String[] args){

        /**
         * 基于注解
         */
//        AspectJProxyFactory pf = new AspectJProxyFactory();
//        pf.setTarget(new NaiveWaiter());
//        //pf.addAspect(MyAspect.class);
//        pf.addAspect(new MyAspect());//两种写法都可以
//
//        NaiveWaiter waiter = (NaiveWaiter) pf.getProxy();
//        waiter.greetTo("Tom");
//        waiter.serveTo("Tom");

        /**
         * 基于schema
         */
        AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");

        Waiter waiter = (Waiter)ctx.getBean("waiter");
        waiter.greetTo("Tom");
        waiter.serveTo("Tom");

    }
}
