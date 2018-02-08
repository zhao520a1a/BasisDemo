package org.hrbust.springapo.advice;

import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

/**
 * Created by Chris on 2017/3/29.
 */
public class MyThrowsAdvice implements ThrowsAdvice {
    public void afterThrowing(Method m, // 目标方法
                              Object[] args,// 目标方法参数
                              Object target, // 目标对象
                              Exception ex // 目标方法抛出的异常，必须与目标方法抛出的异常类型相同，或是其父类
                             ) throws Throwable{

        System.out.println("do something when throws");

        // 此处抛出异常会将目标方法异常覆盖
        //throw new Exception("thrown from throws advice");
    }
}
