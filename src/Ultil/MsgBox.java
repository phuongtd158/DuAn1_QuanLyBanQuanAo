/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ultil;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class MsgBox {
    public static void alert(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Hệ thống quản lý bán hàng", JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean comfirm(Component parent, String message) {
        int rs = JOptionPane.showConfirmDialog(parent, message, "Hệ thống quản lý bán hàng",
                 JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return rs == JOptionPane.YES_OPTION;
    }

    public static String prompt(Component parent, String message) {
        String result = JOptionPane.showInputDialog(parent, message, "Hệ thống quản lý bán hàng", JOptionPane.INFORMATION_MESSAGE);
        return result;
    }
    
    public static int confirm_2 (Component parent , String message ,String ...args ) {
        int rs = JOptionPane.showOptionDialog( parent , message , "Hệ thống quản lý bán hàng ",  JOptionPane.DEFAULT_OPTION, 
                JOptionPane.INFORMATION_MESSAGE , null , args , args[0] ) ;
        return rs ;
    }
    
}
