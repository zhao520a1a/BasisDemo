package org.hrbust.springTest;

import org.hrbust.springIoC.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by golden on 2017/4/23 0023.
 */
public class TestSpring {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
//        Boss boss = (Boss) applicationContext.getBean("boss");
//        String carBrand = boss.getCar().getBrand();
//        System.out.println(carBrand);

        Person person = (Person) applicationContext.getBean("person");

        System.out.println(person.toString());

    }
}
