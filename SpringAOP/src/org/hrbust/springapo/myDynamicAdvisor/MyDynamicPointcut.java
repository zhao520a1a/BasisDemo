package org.hrbust.springapo.myDynamicAdvisor;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


/**
 * 自定义动态切点：在运行期才确定切点
 * Created by Chris on 2017/4/5.
 */
public class MyDynamicPointcut extends DynamicMethodMatcherPointcut {
    public static List<String> matchedArgs = new ArrayList<>();
    static{
        matchedArgs.add("topic1");
        matchedArgs.add("topic2");
    }

    /*匹配类*/
    @Override
    public ClassFilter getClassFilter(){
        return new ClassFilter(){
            @Override
            public boolean matches(Class cls){
                return cls.getSimpleName().equals("ForumService");
            }
        };
    }

    /*参数同mathedArgs中的元素匹配*/
    @Override
    public boolean matches(Method method, Class cls, Object[] args){
        String topic = (String)args[0];
        return matchedArgs.contains(topic);
    }

    /*匹配方法*/
    @Override
    public boolean matches(Method method, Class cls){
        return method.getName().equals("removeTopic");
    }

}
