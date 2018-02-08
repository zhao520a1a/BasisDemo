package org.hrbust.springapo.myStaticMethodMatcherPointcutAdvisor;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;

import java.lang.reflect.Method;

/**
 * 自定义静态方法名切面类
 * Created by Chris on 2017/3/29.
 */
public class MyAdvisor extends StaticMethodMatcherPointcutAdvisor {
    @Override
    public boolean matches(Method method, Class<?> aClass) {
        return method.getName().equals("removeTopic"); //匹配方法
    }

    /*类过滤器*/
    public ClassFilter getClassFilter() {
        return new ClassFilter() {
            public boolean matches(Class<?> cls) {
                return cls.getSimpleName().equals("ForumService"); //匹配类
            }
        };
    }
}
