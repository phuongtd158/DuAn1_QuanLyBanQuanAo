/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author ASUS
 */
public class HinhThucTT {
    private int MaHTTT ;
    private String TenHTTT ;
    private Boolean TrangThai ;

    public int getMaHTTT() {
        return MaHTTT;
    }

    public void setMaHTTT(int MaHTTT) {
        this.MaHTTT = MaHTTT;
    }

    public String getTenHTTT() {
        return TenHTTT;
    }

    public void setTenHTTT(String TenHTTT) {
        this.TenHTTT = TenHTTT;
    }

    public Boolean getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(Boolean TrangThai) {
        this.TrangThai = TrangThai;
    }

    @Override
    public String toString() {
        return TenHTTT ;
    }
    
    
}
