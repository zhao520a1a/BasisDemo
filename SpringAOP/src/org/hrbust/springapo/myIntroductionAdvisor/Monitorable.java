package org.hrbust.springapo.myIntroductionAdvisor;

/**
 * Created by golden on 2017/4/11 0011.
 */
/**
 * 定义一个用于标志目标类是否支持性能监控的接口：
 * Created by Chris on 2017/4/6.
 */
public interface  Monitorable {
    /**
     * @param isActive
     * true:性能监控功能激活
     * false: 性能监控功能关闭
     */
   void setMonitorActive(boolean isActive);
}
