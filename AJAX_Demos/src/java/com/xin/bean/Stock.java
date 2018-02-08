package com.xin.bean;

/**
 * Created by golden on 2016/11/29 0029.
 * 表示股票信息的javaBean
 */
public class Stock {
    /**
     * 股票代码
     */
    private String id;


    /**
     * 股票名称
     */
    private String name;
    /**
     * 昨天的收盘价
     */
    private double yesterday;
    /**
     * 今天的开盘价
     */
    private double today;
    /**
     * 当前价
     */
    private double now;

    public Stock(String id, String name, double yesterday, double today) {
        this.id = id;
        this.name = name;
        this.yesterday = yesterday;
        this.today = today;
        this.now = today;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getYesterday() {
        return yesterday;
    }

    public void setYesterday(double yesterday) {
        this.yesterday = yesterday;
    }

    public double getToday() {
        return today;
    }

    public void setToday(double today) {
        this.today = today;
    }

    public double getNow() {
        return now;
    }

    public void setNow(double now) {
        this.now = now;
    }

    @Override
    public String toString() {
        return this.name + ":" + now ;
    }
}
