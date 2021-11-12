/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class HoaDon {
    private int MaHD ;
    private int MaKH ;
    private String MaNV ;
    private int MaHTTT ;
    private Date NgayTao ;
    private Boolean TrangThai ;

    public int getMaHD() {
        return MaHD;
    }

    public void setMaHD(int MaHD) {
        this.MaHD = MaHD;
    }

    public int getMaKH() {
        return MaKH;
    }

    public void setMaKH(int MaKH) {
        this.MaKH = MaKH;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV( String MaNV) {
        this.MaNV = MaNV;
    }

    public int getMaHTTT() {
        return MaHTTT;
    }

    public void setMaHTTT(int MaHTTT) {
        this.MaHTTT = MaHTTT;
    }

    public Date getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(Date NgayTao) {
        this.NgayTao = NgayTao;
    }

    public Boolean getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(Boolean TrangThai) {
        this.TrangThai = TrangThai;
    }
    
    
    
}
