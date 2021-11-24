/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ultil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ASUS
 */
public class XDate {

    static SimpleDateFormat formater = new SimpleDateFormat();
    static String pattern = "yyyy-MM-dd";

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
}
