package org.hrbust.springapo.myIntroductionAdvisor;

/**
 * 实现增强具体操作的类
 * Created by Chris on 2017/4/6.
 */
public class PerformanceMonitor {

    public static void begin(String method) {
        System.out.println("begin monitor...");
    }

    public static void end() {
        System.out.println("end monitor...");
    }
}