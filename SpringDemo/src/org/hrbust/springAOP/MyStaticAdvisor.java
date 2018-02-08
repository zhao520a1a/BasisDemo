package org.hrbust.springAOP;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;

import java.lang.reflect.Method;

/**
 * 静态方法名匹配切面
 * Created by golden on 2017/4/23 0023.
 */
public class MyStaticAdvisor extends StaticMethodMatcherPointcutAdvisor {
    @Override
    public boolean matches(Method method, Class<?> aClass) {
        return method.getName().equals("greetTo");
    }

    public ClassFilter getClassFilter(){
        return new ClassFilter() {
            @Override
            public boolean matches(Class<?> aClass) {
                return aClass.getSimpleName().equals("NaiveWaiter");
            }
        };
    }
}
