package org.hrbust.springapo.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 环绕增强
 * Created by Chris on 2017/3/29.
 */
public class MyInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        // 获取目标方法参数
        Object[] args = methodInvocation.getArguments();

        // 环绕增强的前半部分
        System.out.println("do something before in around advice");

        // 调用目标方法
        Object o = methodInvocation.proceed();

        // 环绕增强的后半部分
        System.out.println("do something after in around advice");

        return o;
    }
}
