/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author ASUS
 */
public class MauSac {
    private int MaMau ;
    private String TenMau ;

    public int getMaMau() {
        return MaMau;
    }

    public void setMaMau( int MaMau) {
        this.MaMau = MaMau;
    }

    public String getTenMau() {
        return TenMau;
    }

    public void setTenMau(String TenMau) {
        this.TenMau = TenMau;
    }

    @Override
    public String toString() {
        return this.TenMau ;
    }
    
    
}
