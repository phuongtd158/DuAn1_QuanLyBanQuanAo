/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author ASUS
 */
public class KichThuoc {
    private int MaKT ;
    private String TenKT ; 
    private Boolean TrangThai ;

    public int getMaKT() {
        return MaKT;
    }

    public void setMaKT( int MaKT) {
        this.MaKT = MaKT;
    }

    public String getTenKT() {
        return TenKT;
    }

    public void setTenKT(String TenKT) {
        this.TenKT = TenKT;
    }

    public Boolean getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(Boolean TrangThai) {
        this.TrangThai = TrangThai;
    }
    
    @Override
    public String toString() {
        return TenKT ;
    }
    
    
}
