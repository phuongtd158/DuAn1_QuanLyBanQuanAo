/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author ASUS
 */
public class ChatLieu {
    private int MaCL ;
    private String TenChatLieu ;

    public int getMaCL() {
        return MaCL;
    }

    public void setMaCL(int MaCL) {
        this.MaCL = MaCL;
    }

    public String getTenChatLieu() {
        return TenChatLieu;
    }

    public void setTenChatLieu(String TenChatLieu) {
        this.TenChatLieu = TenChatLieu;
    }

    @Override
    public String toString() {
        return TenChatLieu;
    }
    
    
}
