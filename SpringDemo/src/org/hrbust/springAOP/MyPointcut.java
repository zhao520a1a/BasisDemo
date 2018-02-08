package org.hrbust.springAOP;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 动态切点类
 * Created by golden on 2017/4/23 0023.
 */
public class MyPointcut extends DynamicMethodMatcherPointcut {

    private static List<String> matchedArgs = new ArrayList<>();

    static {
        matchedArgs.add("Golden");
        matchedArgs.add("Mike");
    }


    /*先判断方法名对不对*/
    @Override
    public boolean matches(Method method, Class<?> aClass ) {
        return method.getName().equals("greetTo");
    }
    /*再判断方法参数对不对*/
    @Override
    public boolean matches(Method method, Class<?> aClass, Object[] args) {
        return matchedArgs.contains(args[0]);
    }

    /*看类对不对*/
    public ClassFilter getClassFilter(){
        return new ClassFilter() {
            @Override
            public boolean matches(Class<?> aClass) {
                return aClass.getSimpleName().equals("NaiveWaiter");
            }
        };
    }
}
