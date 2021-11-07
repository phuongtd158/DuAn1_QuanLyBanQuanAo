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

/**
 *
 * @author ASUS
 */
public class SanPhamDAO extends Main< SanPham, String> {

    final String INSERT = "Insert ";

    @Override
    public void insert(SanPham entity) {

    }

    @Override
    public void update(SanPham entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SanPham> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SanPham selectByID(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected List<SanPham> selectBySQL(String sql, Object... args) {
        List<SanPham> list = new ArrayList<>();

        try {
            ResultSet rs = JDBCHelper.query(sql, args);

            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMaSP(rs.getString("MaSP"));
                sp.setTenSP(rs.getString("TenSp"));
                sp.setMauSac(rs.getString("TenMauSac"));
                sp.setKichThuoc(rs.getString("KichThuoc"));
                sp.setChatLieu(rs.getString("TenChatLieu"));
                sp.setSoLuong(rs.getInt("SoLuong"));
                sp.setGia(rs.getDouble("Gia"));
                sp.setGiamGia(rs.getDouble("GiamGia"));
                sp.setTrangThai(rs.getBoolean("TrangThai"));
                list.add(sp);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

}
