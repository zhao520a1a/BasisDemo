package org.hrbust.springapo.myIntroductionAdvisor;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

/**
 * 定义引介增强
 * Created by Chris on 2017/4/6.
 */
public class MyIntroductionInterceptor extends DelegatingIntroductionInterceptor
                                       implements Monitorable{

    public static ThreadLocal<Boolean> monitorStatus = new ThreadLocal<Boolean>();

    /*重写接口方法*/
    @Override
    public void setMonitorActive(boolean active) {
        monitorStatus.set(active);
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable{
        Object o = null;

        if(monitorStatus.get() != null && monitorStatus.get()){
            PerformanceMonitor.begin(methodInvocation.getClass().getName() + "." + methodInvocation.getMethod().getName());
            o = super.invoke(methodInvocation);
            PerformanceMonitor.end();
        }else{
            o = super.invoke(methodInvocation);
        }

        return o;
    }
}
