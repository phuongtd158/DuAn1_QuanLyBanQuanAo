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
    private String MaKT ;
    private String TenKT ; 

    public String getMaKT() {
        return MaKT;
    }

    public void setMaKT(String MaKT) {
        this.MaKT = MaKT;
    }

    public String getTenKT() {
        return TenKT;
    }

    public void setTenKT(String TenKT) {
        this.TenKT = TenKT;
    }

    @Override
    public String toString() {
        return TenKT ;
    }
    
    
}
