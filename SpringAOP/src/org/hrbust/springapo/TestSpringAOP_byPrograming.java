package org.hrbust.springapo;

import org.hrbust.springapo.advice.MyBeforeAdvice;
import org.hrbust.springapo.myStaticMethodMatcherPointcutAdvisor.MyAdvisor;
import org.hrbust.springapo.targer.ForumService;
import org.hrbust.springapo.targer.ForumServiceInterface;
import org.springframework.aop.framework.ProxyFactory;

/**
 * 基于编程式实现SpringAOP的基本步骤
 •定义增强类，实现特定增强接口 •在增强类中重写特定方法，实现增强操作 •使用时：
 •创建代理工厂（ProxyFactory)
 •调用代理工厂的setTarget()方法设置目标对象，传入目标对象
 作为参数
 •调用代理工厂的addAdvice()方法添加增强，传入增强类实例作
 为参数
 •调用ProxyFactory的getProxy()方法获取代理对象
 * Created by Chris on 2017/3/29.
 */
public class TestSpringAOP_byPrograming {
    public static void main(String[] args) throws Throwable {

        // 初始化代理工厂
        ProxyFactory pf = new ProxyFactory();
        // 指定目标对象
        pf.setTarget(new ForumService());

        /**
         * 不使用切面，仅使用增强
         */
        //// 给代理工厂添加增强
        //pf.addAdvice(new MyBeforeAdvice());
        //pf.addAdvice(new MyAfterAdvice());
        //pf.addAdvice(new MyThrowsAdvice());
        //pf.addAdvice(new MyInterceptor());


        /**
         * 使用引介切面
         */
//        pf.addAdvice(new MyIntroductionInterceptor());
//        pf.setProxyTargetClass(true); // 引介增强必须通过CGLib实现
//
//        ForumService service = (ForumService) pf.getProxy();
//
//        Monitorable monitor = (Monitorable)service;
//        monitor.setMonitorActive(true);
//
//        service.removeTopic("topic1");


        /**
         * 使用自定义的静态方法名匹配切面
         */
        // 初始化静态方法名切面类
        MyAdvisor advisor = new MyAdvisor();
        // 给切面添加增强
        advisor.setAdvice(new MyBeforeAdvice());
        // 给代理工厂添加切面
        pf.addAdvisor(advisor);
        // 得到代理对象，此时增强已被织入到连接点
        ForumServiceInterface service = (ForumServiceInterface) pf.getProxy();
        service.removeTopic("topic1");

        /**
         * 使用Spring自带的正则表达式切面
         * .*\.remove.*表示所有类中以remove开头的方法
         */
//        RegexpMethodPointcutAdvisor advisor = new RegexpMethodPointcutAdvisor(".*\\.remove.*",new MyBeforeAdvice());
//        pf.addAdvisor(advisor);
//        // 得到代理对象，此时增强已被织入到Z连接点
//        ForumServiceInterface service = (ForumServiceInterface) pf.getProxy();
//        service.removeTopic("topic1");


        /**
         * 使用动态切面
         */
//        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
//        advisor.setAdvice(new MyBeforeAdvice());  //添加增强
//        advisor.setPointcut(new MyDynamicPointcut());  //添加动态切点
//        pf.addAdvisor(advisor);
//        ForumServiceInterface service = (ForumServiceInterface) pf.getProxy();
//        service.removeTopic("topic1");
//        service.removeTopic("topic2");
//        service.removeTopic("topic3");

    }
}
