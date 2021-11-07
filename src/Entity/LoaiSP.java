/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author ASUS
 */
public class LoaiSP {
    private int LoaiSP ;
    private String TenLoaiSP ; 
    

    public int getLoaiSP() {
        return LoaiSP;
    }

    public void setLoaiSP( int LoaiSP) {
        this.LoaiSP = LoaiSP;
    }

    public String getTenLoaiSP() {
        return TenLoaiSP;
    }

    public void setTenLoaiSP(String TenLoaiSP) {
        this.TenLoaiSP = TenLoaiSP;
    }

    @Override
    public String toString() {
        return TenLoaiSP ;
    }
     
}
