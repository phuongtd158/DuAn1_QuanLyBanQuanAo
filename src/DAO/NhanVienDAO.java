/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.NhanVien;
import Entity.SanPham;
import Ultil.JDBCHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class NhanVienDAO extends Main<NhanVien, String>{

    @Override
    public void insert(NhanVien entity) {
        
    }

    @Override
    public void update(NhanVien entity) {
        
    }

    @Override
    public void delete(String id) {
        
    }

    @Override
    public List<NhanVien> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public NhanVien selectByID(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected List<NhanVien> selectBySQL(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();

        try {
            ResultSet rs = JDBCHelper.query(sql, args);

            while (rs.next()) {
                NhanVien nv = new NhanVien() ;
                nv.setMaNV(rs.getString("MaNV"));
                nv.setTenNV(rs.getString("TenNV"));
                nv.setDiaChi(rs.getString("DiaChi"));
                nv.setGioiTinh(rs.getBoolean("GioiTinh"));
                nv.setEmail(rs.getString("Email"));
                nv.setSDT(rs.getString("SoDienThoai"));
                nv.setNgaySinh(rs.getDate("NgaySinh"));
                nv.setVaiTro(rs.getBoolean("VaiTro"));
                nv.setMatKhau(rs.getString("MatKhau"));
                nv.setTrangThai(rs.getBoolean("TrangThai"));
                list.add(nv);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
     
}
