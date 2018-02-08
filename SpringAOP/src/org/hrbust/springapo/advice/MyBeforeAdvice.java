package org.hrbust.springapo.advice;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * Created by Chris on 2017/3/29.
 */
public class MyBeforeAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, // 目标方法
                       Object[] objects, // 目标方法参数
                       Object o // 目标对象
                      ) throws Throwable {

        System.out.println("MyBeforeAdvice monitor begin...");

        // 这里抛出异常会阻止目标方法继续执行
        //throw new Exception("thrown from before advice");
    }
}
