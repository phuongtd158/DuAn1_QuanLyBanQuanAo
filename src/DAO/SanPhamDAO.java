/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.SanPham;
import Ultil.JDBCHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class SanPhamDAO extends Main< SanPham, String> {

    String Insert = " Insert into SANPHAM ( TenSP ) values ( ? ) ";
    String Insert_1 = "Insert into CHITIETSANPHAM ( MaSP , MaLoai , MaMauSac , MaKichThuoc , MaChatLieu , SoLuong , Gia )"
            + " values ( ? , ? ,? , ? ,? ,? , ? )  ";

    String Update = " Update SANPHAM set TenSp = ? and MaSP = ? "
            + " Update CHITIETSANPHAM set SoLuong = ?  , Gia = ? where MaCTSP = ? ";
    String Update_2 = " Update CHITIETSANPHAM set TrangThai = 0 where MaCTSP = ? ";
    String Update_HienThiSanPham = " UPDATE dbo.CHITIETSANPHAM SET TrangThai = 1 WHERE MaCTSP = ?";
    String SelectAll = "SELECT * FROM dbo.CHITIETSANPHAM JOIN dbo.CHATLIEU ON CHATLIEU.MaChatLieu = CHITIETSANPHAM.MaChatLieu\n"
            + "		JOIN dbo.KICHTHUOC ON KICHTHUOC.MaKichThuoc = CHITIETSANPHAM.MaKichThuoc\n"
            + "		JOIN dbo.MAUSAC ON MAUSAC.MaMauSac = CHITIETSANPHAM.MaMauSac\n"
            + "		JOIN dbo.LOAISP ON LOAISP.MaLoai = CHITIETSANPHAM.MaLoai\n"
            + "		JOIN dbo.SANPHAM ON SANPHAM.MaSP = CHITIETSANPHAM.MaSP ";
    String SelectID = "select * from SANPHAM where TenSp = ? ";

// Insert
    @Override
    public void insert(SanPham entity) {
    }

    public void Insert_SP(String k) {
        JDBCHelper.Update(Insert, k);
    }

    public void Insert_SPCT(SanPham entity, int a, int b, int c, int d) {
        JDBCHelper.Update(Insert_1, entity.getMaSP(), a, b, c,
                d, entity.getSoLuong(), entity.getGia());
    }

    // Update
    @Override
    public void update(SanPham entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void Update_1(SanPham entity) {
        JDBCHelper.Update(Update, entity.getTenSP(), entity.getMaSP(), entity.getSoLuong(), entity.getGia(), entity.getMaCTSP());
    }

    public void Update_2(SanPham entity) {
        JDBCHelper.Update(Update_2, entity.getMaCTSP());
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Các câu select
    @Override
    public List<SanPham> selectAll() {
        return selectBySQL(SelectAll);
    }

    public void hienThiSanPham(int id) {
        JDBCHelper.Update(Update_HienThiSanPham, id);
    }

    public Boolean SelectID_1(String k) {
        ResultSet rs;
        try {
            rs = JDBCHelper.query(SelectID, k);

            if (rs == null) {
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public int SelectByIDSp(String k) {
        return JDBCHelper.Update(SelectID, k);
    }

    @Override
    public SanPham selectByID(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected List<SanPham> selectBySQL(String sql, Object... args) {
        List<SanPham> list = new ArrayList<>();

        try {
            ResultSet rs = JDBCHelper.query(sql, args);

            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMaCTSP(rs.getInt("MaCTSP"));
                sp.setMaSP(rs.getInt("MaSP"));
                sp.setTenSP(rs.getString("TenSp"));
                sp.setTenLoai(rs.getString("TenLoai"));
                sp.setTenMauSac(rs.getString("TenMauSac"));
                sp.setTenKichThuoc(rs.getString("KichThuoc"));
                sp.setTenChatLieu(rs.getString("TenChatLieu"));
                sp.setSoLuong(rs.getInt("SoLuong"));
                sp.setGia(rs.getDouble("Gia"));
                sp.setGiamGia(rs.getDouble("GiamGia"));
                sp.setTrangThai(rs.getBoolean("TrangThai"));
                list.add(sp);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

}
