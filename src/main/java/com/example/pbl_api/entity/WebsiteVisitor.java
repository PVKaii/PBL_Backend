package com.example.pbl_api.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "website_visitor")
public class WebsiteVisitor {
    @Id
    @Column(name = "week")
    private int week;

    @Column(name = "counter")
    private long counter;

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public long getCounter() {
        return counter;
    }

    public void setCounter(long counter) {
        this.counter = counter;
    }
}
