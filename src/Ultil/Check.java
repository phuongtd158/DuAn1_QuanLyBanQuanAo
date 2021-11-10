/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ultil;

import com.toedter.calendar.JDateChooser;
import javax.swing.JTextField;

/**
 *
 * @author Quyết Chiến
 */
public class Check {
    // Check trống text

    public static boolean checkTrongText(JTextField txt) {
        if (txt.getText().trim().length() > 0) {
            return true;
        } else {
            txt.requestFocus();
            MsgBox.alert(txt.getRootPane(), "Vui Lòng Không Để Trống");
            return false;
        }
    }

    // check định dạng email
    public static boolean checkEmail(JTextField txt) {
        String email = txt.getText();
        String format = "^[a-zA-Z][a-zA-Z0-9_\\.]{2,32}@[a-zA-Z0-9]{2,10}(\\.[a-zA-Z0-9]{2,4}){1,2}$";
        if (email.matches(format)) {
            return true;
        } else {
            txt.requestFocus();
            MsgBox.alert(txt.getRootPane(), "Email Không Đúng Định Dạng");
            return false;
        }
    }

    public static boolean checkSDT(JTextField txt) {
        String sdt = txt.getText();
        String format = "0[3,9,8](\\d){8}";
        if (sdt.matches(format)) {
            return true;
        } else {
            txt.requestFocus();
            MsgBox.alert(txt.getRootPane(), "Số Điện Thoại Không Đúng Định Dạng");
            return false;
        }
    }

    public static boolean checkTrongJdate(JDateChooser txt) {

        if (((JTextField) txt.getDateEditor().getUiComponent()).getText().equals("")) {
            MsgBox.alert(txt.getRootPane(), "Vui Lòng Không Để Trống");
            return false;
        } else {
            return true;
        }
    }

    public static boolean checkSoDuong(JTextField txt) {
        try {
            int so = Integer.parseInt(txt.getText());
            if (so < 0) {
                txt.requestFocus();
                MsgBox.alert(txt.getRootPane(), "Vui Lòng Nhập Số Dương");
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            MsgBox.alert(txt.getRootPane(), "Vui Lòng Nhập Số");
            return false;
        }
    }

    public static boolean checkSoDuong_double(JTextField txt) {
        try {
            double so = Double.parseDouble(txt.getText());
            if (so < 0) {
                txt.requestFocus();
                MsgBox.alert(txt.getRootPane(), "Vui Lòng Nhập Số Dương");
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            MsgBox.alert(txt.getRootPane(), "Vui Lòng Nhập Số");
            return false;
        }
    }
}
