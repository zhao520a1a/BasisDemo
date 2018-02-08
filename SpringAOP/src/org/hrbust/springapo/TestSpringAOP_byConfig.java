package org.hrbust.springapo;

import org.hrbust.springapo.targer.ForumServiceInterface;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *基于编程式实现SpringAOP的基本步骤
 •定义增强类，实现特定增强接口 •在增强类中重写特定方法，实现增强操作 •使用时：
 •创建代理工厂（ProxyFactory)
 •调用代理工厂的setTarget()方法设置目标对象，传入目标对象
 作为参数
 •调用代理工厂的addAdvice()方法添加增强，传入增强类实例作
 为参数
 •调用ProxyFactory的getProxy()方法获取代理对象
 * Created by Chris on 2017/3/29.
 */
public class TestSpringAOP_byConfig {
    public static void main(String[] args) throws Throwable {

        /**
         * 基于配置的AOP，调用测试：引介增强
         */
        AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
        ForumServiceInterface service = (ForumServiceInterface) ctx.getBean("service");

        /*当测试引介切面时，加上下面的两行代码*/
//        Monitorable moniterable = (Monitorable) service;
//        moniterable.setMonitorActive(true);

        service.removeTopic("topic1");

    }
}
