package org.hrbust.springapo.advice;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * Created by Chris on 2017/3/29.
 */
public class MyAfterAdvice implements AfterReturningAdvice {

    @Override
    public void afterReturning(Object o, //目标方法返回值
                               Method method, // 目标方法
                               Object[] objects, // 目标方法参数
                               Object o1 // 目标对象
                              ) throws Throwable {

        System.out.println("performance monitor end");
    }
}
