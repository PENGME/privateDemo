package com.pm.demo.entity;

import java.io.Serializable;
import java.util.Date;

//会员信息
public class Card implements Serializable {
    private int id;
    private String name;
    private Double sal;
    private Date vdate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSal() {
        return sal;
    }

    public void setSal(Double sal) {
        this.sal = sal;
    }

    public Date getVdate() {
        return vdate;
    }

    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }
}
