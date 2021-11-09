/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.KhachHang;
import Entity.SanPham;
import Ultil.JDBCHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class KhachHangDAO extends Main< KhachHang, String >{
 final String INSERT_SQL = " insert into KHACHHANG(TenKH, NgaySinh, GioiTinh, SoDienThoai, DiaChi,TrangThai) values( ?, ?, ?, ?,?,?)";
        final String UPDATE_SQL = "update KHACHHANG set TenKH = ?, NgaySinh = ?, GioiTinh = ?, SoDienThoai = ?, DiaChi = ?, TrangThai = ? where MaKH = ?";
        final String DELETE_SQL = "delete from KHACHHANG where MaKH = ?";
        final String SELECT_ALL_SQL = "select * from KHACHHANG";
        final String SELECY_BY_ID_SQL = "select * from KHACHHANG where MaKH = ?";
    @Override
    public void insert(KhachHang entity) {
         JDBCHelper.Update(INSERT_SQL, entity.getTenKH(), entity.getNgaySinh(), entity.getGioiTinh(), entity.getSDT(), entity.getDiaChi(),entity.getTrangThai());
    }

    @Override
    public void update(KhachHang entity) {
         JDBCHelper.Update(UPDATE_SQL, entity.getTenKH(), entity.getNgaySinh(), entity.getGioiTinh(), entity.getSDT(), entity.getDiaChi(), entity.getTrangThai(), entity.getMaKH());
    }

    @Override
    public void delete(String id) {
        JDBCHelper.Update(DELETE_SQL, id);
    }

    @Override
    public List<KhachHang> selectAll() {
        return selectBySQL(SELECT_ALL_SQL);
    }

    @Override
    public KhachHang selectByID(String id) {
        List<KhachHang> list = selectBySQL(SELECY_BY_ID_SQL, Integer.valueOf(id));
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<KhachHang> selectBySQL(String sql, Object... args) {
        List<KhachHang> list = new ArrayList<>();

        try {
            ResultSet rs = JDBCHelper.query(sql, args);

            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setMaKH(rs.getInt("MaKH"));
                kh.setTenKH(rs.getString("TenKH"));
                kh.setNgaySinh(rs.getDate("NgaySinh"));
                kh.setGioiTinh(rs.getBoolean("GioiTinh"));
                kh.setSDT(rs.getString("SoDienThoai"));
                kh.setDiaChi(rs.getString("DiaChi"));
                kh.setTrangThai(rs.getBoolean("TrangThai"));
                
                list.add(kh);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
    
}
