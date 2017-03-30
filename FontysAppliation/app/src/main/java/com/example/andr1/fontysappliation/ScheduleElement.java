package com.example.andr1.fontysappliation;

import java.util.Date;

/**
 * Created by az on 23.3.2017 г..
 */

public class ScheduleElement {
    public Date starrt;
    public String room;
    public String subject;
    public String start;
    public String end;
    public ScheduleElement(){}
    public ScheduleElement(String room, String subject, String start, String end){
        this.room=room;
        this.subject= subject;
        this.start = start;
        this.end = end;
    }
}
