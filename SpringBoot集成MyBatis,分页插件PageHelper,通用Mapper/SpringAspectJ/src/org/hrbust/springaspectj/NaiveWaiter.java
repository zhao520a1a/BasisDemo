package org.hrbust.springaspectj;

/**
 * Created by Chris on 2017/4/6.
 */
public class NaiveWaiter implements Waiter {
    @Override
    public void greetTo(String name) {
        System.out.println("greet to " + name);
    }

    @Override
    public int serveTo(String name) {
        System.out.println("serve to " + name);
        return 1;
    }
}
