package org.hrbust.springIoC;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by golden on 2017/4/23 0023.
 */
@Component("boss")
public class Boss {
    private String name;
    @Autowired
    private Car car;

    public Boss() {
    }

    public Boss(Car car) {
        this.car = car;
    }

    public Boss(String name, Car car) {
        this.name = name;
        this.car = car;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
