/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ultil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ASUS
 */
public class XDate {

    static SimpleDateFormat formater = new SimpleDateFormat();
    static String pattern = "yyyy-MM-dd";
    static String pt = "MM";
    static String dd = "dd";
    static String tt = "hh:mm aa";

    public static Date toDate(String date) {
        try {
            formater.applyPattern(pattern);
            return formater.parse(date);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public static String toString(Date date) {
        formater.applyPattern(pattern);
        return formater.format(date);
    }

    public static String toMonth(Date date) {
        formater.applyPattern(pt);
        return formater.format(date);
    }

    public static String toDay(Date date) {
        formater.applyPattern(dd);
        return formater.format(date);
    }

    public static String toTime(Date time) {
        formater.applyPattern(tt);
        return formater.format(time);
    }
    public static Date toT(String time) throws ParseException {
        formater.applyPattern(tt);
        return formater.parse(time);
    }
}
