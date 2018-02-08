package org.hrbust.springAOP;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Chris on 2017/4/6.
 */
public class TestSpringAspectJ {
    public static void main(String[] args){

        /**
         * 基于注解的方式
         */
//        AspectJProxyFactory pf = new AspectJProxyFactory();
//        pf.setTarget(new NaiveWaiter());
//        //下面两种写法都可以
//        //pf.addAspect(MyAspect.class);
//        pf.addAspect(new MyAspect());
//
//        NaiveWaiter waiter = (NaiveWaiter) pf.getProxy();
//        waiter.greetTo("Golden");
//        waiter.serveTo("Golden");

        /**
         * 基于schema配置的方式
         */
        AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");

        Waiter waiter = (Waiter)ctx.getBean("target");
        waiter.greetTo("Golden");
        waiter.serveTo("Golden");

    }
}
