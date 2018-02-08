package org.hrbust.springAOP;

/**
 * Created by golden on 2017/4/23 0023.
 */
public class NaiveWaiter implements Waiter {
    @Override
    public String greetTo(String name) {
        System.out.println("greetTo()方法执行中……");
        return name;
    }

    @Override
    public void serveTo(String name) {
        System.out.println("serveTo()方法执行中……");
    }
}
