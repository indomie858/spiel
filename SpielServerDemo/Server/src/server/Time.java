/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

/**
 *
 * @author serde
 */
import java.time.LocalDateTime;
import java.io.*;

public class Time {

    private int hour;
    private int minute;
    private int time;
    private String ampm;

    public Time() {
        hour = 0;
        minute = 0;
        calculateTime();
    }

    private void calculateTime() {

        hour = LocalDateTime.now().getHour();  //modified
        minute = LocalDateTime.now().getHour(); //modified

        if (hour >= 12) {
            hour %= 12;
            ampm = "PM";
        } else {
            ampm = "AM";
        }
    }

    public String getTime() {
        return hour + ":" + minute + " " + ampm;
    }
}
