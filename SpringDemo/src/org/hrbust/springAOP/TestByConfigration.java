package org.hrbust.springAOP;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by golden on 2017/4/23 0023.
 */
public class TestByConfigration {
    public static void main(String[] args) {
        AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
        Waiter service = (Waiter) ctx.getBean("waiterProxy");

        service.greetTo("Golden");
    }
}
