/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ultil;

import Entity.NhanVien;

/**
 *
 * @author ASUS
 */
public class Auth {
    public static NhanVien user = null;// duy trì đăng nhập vào hệ thống
    public static void clear(){ // khi đăng xuất thì xóa user
        Auth.user = null;
    }
    public static boolean isLogin(){ // kiểm tra xem đăng nhập chưa
        return Auth.user != null;
    }
    public static boolean isManager(){
        return Auth.isLogin()&& user.getVaiTro();
    }
}
