/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.HoaDon;
import Ultil.JDBCHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author ASUS
 */
public class HoaDonDAO extends Main< HoaDon, String> {

    private String selectAll = "select * from HOADON";
    private String Insert = "Insert into HOADON ( MaKH , MaNV , MaHTTT , TrangThai , GhiChu , TienShip ) values ( ? , ? , ? , ? , ? , ?) ";
    private String Insert_1 = "Insert into HOADON (  MaNV , TrangThai , GhiChu , TienShip ) values (  ? , ? , ? , ?) ";
    private String selectByID = "select * from HOADON where MaHD like ? ";
    String Update = "Update HOADON set GhiChu = ? where MaHD like ? ";
    String Update_1 = "Update HOADON set TrangThai = ? where MaHD like ? ";
    String Update_2 = "Update HOADON set MaHTTT = ? where MaHD like ? " ;
    String Update_3 = "Update HOADON set MaKH = ? where MaHD like ? " ;
    String Update_4 = "Update HOADON set MaKH = ? , MaHTTT = ? , TienShip = ?  where MaHD like ? " ;
    private String selectAll_2 = "SELECT * FROM dbo.HOADON JOIN dbo.NHANVIEN ON NHANVIEN.MaNV = HOADON.MaNV JOIN dbo.KHACHHANG ON KHACHHANG.MaKH = HOADON.MaKH\n"
            + "            WHERE MaHD LIKE ? AND TenNV LIKE ? AND TenKH LIKE ? AND NgayKhoiTao LIKE ? AND HOADON.TrangThai LIKE ?";

    public void update2(String a, String b) {
        JDBCHelper.Update(Update_1, a, b);
    }

    public void update1(String a, String b) {
        JDBCHelper.Update(Update, a, b);
    }
    
    public void update3( int a , int b ){
        JDBCHelper.Update(Update_3, a, b);
    }
    
    public void update_HTTT ( int a , String b ){
        JDBCHelper.Update(Update_2 , a , b );
    }

    // Vài câu insetrt
    
    @Override
    public void insert(HoaDon entity) {
        JDBCHelper.Update(Insert, entity.getMaKH(), entity.getMaNV(), entity.getMaHTTT(), entity.getTrangThai(),
                entity.getGhiChu(), entity.getTienShip());
    }

    public void insert_1(HoaDon entity) {
        JDBCHelper.Update(Insert_1,  entity.getMaNV() , entity.getTrangThai(),
                entity.getGhiChu(), entity.getTienShip());
    }
    
    // Các câu update
    @Override
    public void update(HoaDon entity) {
        JDBCHelper.Update( Update_4 , entity.getMaKH() , entity.getMaHTTT() , entity.getTienShip() , entity.getMaHD() ) ;
    }
//
//    public void update1(String k) {
//        JDBCHelper.Update(Update, k);
//    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<HoaDon> selectAll() {
        return selectBySQL(selectAll);
    }

    public List<HoaDon> selectAll_2(String... k) {
        return selectBySQL(selectAll_2, "%" + k[0] + "%", "%" + k[1] + "%", "%" + k[2] + "%", "%" + k[3] + "%", "%" + k[4] + "%");
    }

    @Override
    public HoaDon selectByID(String id) {
        List<HoaDon> list = selectBySQL(selectByID, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<HoaDon> selectBySQL(String sql, Object... args) {
        List<HoaDon> list = new ArrayList<>();

        try {
            ResultSet rs = JDBCHelper.query(sql, args);

            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHD(rs.getInt("MaHD"));
                hd.setMaKH(rs.getInt("MaKH"));
                hd.setMaHTTT(rs.getInt("MaHTTT"));
                hd.setMaNV(rs.getString("MaNV"));
                hd.setNgayTao(rs.getDate("NgayKhoiTao"));
                hd.setGhiChu(rs.getString("GhiChu"));
                hd.setTienShip(rs.getDouble("TienShip"));
                hd.setTrangThai(rs.getString("TrangThai"));
                list.add(hd);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public List<Integer> getYear() {
        String sql = "SELECT DISTINCT YEAR(NgayKhoiTao) AS Nam FROM dbo.HOADON \n"
                + "ORDER BY YEAR(NgayKhoiTao) DESC";
        List<Integer> list = new ArrayList<>();
        ResultSet rs;
        try {
            rs = JDBCHelper.query(sql);
            while (rs.next()) {
                list.add(rs.getInt("Nam"));
            }
            rs.getStatement().getConnection().close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

}
