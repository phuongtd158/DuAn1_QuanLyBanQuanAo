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
    public static NhanVien user = null ;
    public static void Clear (){
        Auth.user = null ;
    }
    
    public static boolean isLogin() {
        return Auth.user != null;
    }

    public static boolean isManager() {
        return Auth.isLogin() && user.getVaiTro() ;
    }
}
